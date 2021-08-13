package com.chen.shop.model.buyer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Article
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/13 15:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    private Long id;

    private Long categoryId;

    private String content;

    private Integer sort;

    private String title;

    private String type;

    private Boolean openStatus;

    private Boolean deleteFlag;

    private String createBy;

    private Date createTime;

    private Date updateTime;
}
