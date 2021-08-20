package com.chen.shop.buyer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.shop.buyer.mapper.CategoryMapper;
import com.chen.shop.buyer.service.CategoryService;
import com.chen.shop.model.buyer.pojo.Category;
import com.chen.shop.model.buyer.vo.CategoryVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/11 11:54
 */

@DubboService(version = "1.0.0", interfaceClass = CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    public CategoryVO copy(Category category) {
        CategoryVO target = new CategoryVO();
        BeanUtils.copyProperties(category, target);
        return target;
    }

    public List<CategoryVO> copyList(List<Category> categoryList) {
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVOList.add(copy(category));
        }
        return categoryVOList;
    }

    //方式二
    public List<CategoryVO> categoryTree(Long parentId) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus, 0);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        List<CategoryVO> categoryVOS = copyList(categories);
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (CategoryVO categoryVO : categoryVOS) {
            if (categoryVO.getParentId().equals(parentId)) {
                categoryVOList.add(categoryVO);
                addAllChildren(categoryVO, categoryVOS);
            }
        }
        return categoryVOList;
    }

    private void addAllChildren(CategoryVO categoryVO, List<CategoryVO> categoryVOS) {
        ArrayList<CategoryVO> categoryVOList = new ArrayList<>();
        for (CategoryVO vo : categoryVOS) {
            if (vo.getParentId().equals(categoryVO.getId())) {
                categoryVOList.add(vo);
                addAllChildren(vo, categoryVOS);
            }
        }
        categoryVO.setChildren(categoryVOList);
    }

    @Override
    public List<CategoryVO> findCategoryTree(Long parentId) {

        /**
         * 1、根据parentId 获取对应的分类列表
         * 2、获取到的分类列表只有一级
         * 3、根据sql执行，发现问题，循环递归的获取所有分类的子分类列表
         * 4、如果这么做的话，数据库连接就会多次 连接性能会降低
         * 5、解决方法 把所有的分类查出来，一次查询效率高
         * 6、代码去组合层级关系，代码的运行速度快
         */

        ////方式一：使用嵌套遍历获取所有分类的子类列表（不推荐）
        //LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.eq(Category::getParentId, parentId);
        //queryWrapper.eq(Category::getStatus, 0);
        //List<Category> categories = categoryMapper.selectList(queryWrapper);
        //List<CategoryVO> categoryVOS = copyList(categories);
        //for (CategoryVO categoryVO : categoryVOS) {
        //    LambdaQueryWrapper<Category> queryWrapper1 = new LambdaQueryWrapper<>();
        //    queryWrapper1.eq(Category::getParentId, categoryVO.getId());
        //    queryWrapper1.eq(Category::getStatus, 0);
        //    List<Category> categories1 = categoryMapper.selectList(queryWrapper1);
        //    List<CategoryVO> categoryVOS1 = copyList(categories1);
        //    for (CategoryVO vo : categoryVOS1) {
        //        LambdaQueryWrapper<Category> queryWrapper2 = new LambdaQueryWrapper<>();
        //        queryWrapper2.eq(Category::getParentId, vo.getId());
        //        queryWrapper2.eq(Category::getStatus, 0);
        //        List<Category> categories2 = categoryMapper.selectList(queryWrapper2);
        //        vo.setChildren(copyList(categories2));
        //    }
        //    categoryVO.setChildren(categoryVOS1);
        //}
        //return categoryVOS;

        return categoryTree(parentId);

    }

    @Override
    public List<String> getCategoryNameByIds(List<String> idList) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Category::getId, idList);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        List<String> strings = categories.stream().map(Category::getName).collect(Collectors.toList());
        return strings;
    }
}
