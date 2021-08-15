package com.chen.shop.buyer.service.impl.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.shop.buyer.mapper.VerificationSourceMapper;
import com.chen.shop.buyer.service.VerificationSourceService;
import com.chen.shop.model.buyer.enums.VerificationSourceEnum;
import com.chen.shop.model.buyer.pojo.VerificationSource;
import com.chen.shop.model.buyer.vo.common.VerificationSourceVO;
import com.chen.shop.model.buyer.vo.common.slider.VerificationVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName VerificationSourceServiceImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/15 9:09
 */
@DubboService(version = "1.0.0", interfaceClass = VerificationSourceService.class)
public class VerificationSourceServiceImpl implements VerificationSourceService {

    @Resource
    private VerificationSourceMapper verificationSourceMapper;

    @Override
    public VerificationVO findVerificationSource() {
        LambdaQueryWrapper<VerificationSource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VerificationSource::getDeleteFlag, false);
        List<VerificationSource> verificationSources = verificationSourceMapper.selectList(queryWrapper);
        List<VerificationSourceVO> verificationResources = new ArrayList<>();
        List<VerificationSourceVO> verificationSliders = new ArrayList<>();
        for (VerificationSource verificationSource : verificationSources) {
            if (verificationSource.getType().equals(VerificationSourceEnum.RESOURCE.name())) {
                verificationResources.add(copy(verificationSource));
            }
            if (verificationSource.getType().equals(VerificationSourceEnum.SLIDER.name())) {
                verificationSliders.add(copy(verificationSource));
            }
        }
        VerificationVO verificationVO = new VerificationVO();
        verificationVO.setVerificationSources(verificationResources);
        verificationVO.setVerificationSlider(verificationSliders);
        return verificationVO;
    }

    private VerificationSourceVO copy(VerificationSource verificationSource) {
        VerificationSourceVO verificationSourceVO = new VerificationSourceVO();
        if (verificationSource == null) {
            return null;
        }
        BeanUtils.copyProperties(verificationSource, verificationSourceVO);
        return verificationSourceVO;
    }

    private List<VerificationSourceVO> copyList(List<VerificationSource> verificationSources) {
        List<VerificationSourceVO> verificationSourceVOS = new ArrayList<>();
        for (VerificationSource verificationSource : verificationSources) {
            verificationSourceVOS.add(copy(verificationSource));
        }
        return verificationSourceVOS;
    }


}
