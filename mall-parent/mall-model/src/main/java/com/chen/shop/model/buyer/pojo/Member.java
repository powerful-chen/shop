package com.chen.shop.model.buyer.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Member
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/15 14:51
 */
//会员
@Data
@NoArgsConstructor
public class Member {

    private Long id;

    private String username;

    private String password;

    private String nickName;
    //1 男 0 女
    private Integer sex;

    private Date birthday;
    //会员地址ID
    private String regionId;
    //会员地址
    private String region;

    private String mobile;
    //积分
    private Long point;

    private String face;
    //会员状态
    private Boolean disabled;

    private Boolean haveStore;

    private String storeId;

    private Integer clientEnum;

    private Long lastLoginDate;
    //会员等级
    private String gradeId;
    //经验
    private Long experience;

    private Long registerTime;

    public Member(String username, String password, String mobile) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.nickName = mobile;
        this.disabled = true;
        this.haveStore = false;
        this.sex = 0;
        this.point = 0L;
        this.lastLoginDate = System.currentTimeMillis();
    }

    public Member(String username, String password, String mobile, String nickName, String face) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.nickName = nickName;
        this.disabled = true;
        this.haveStore = false;
        this.face = face;
        this.sex = 0;
        this.point = 0L;
        this.lastLoginDate = System.currentTimeMillis();
    }

    public Member(String username, String password, String face, String nickName, Integer sex) {
        this.username = username;
        this.password = password;
        this.mobile = "";
        this.nickName = nickName;
        this.disabled = true;
        this.haveStore = false;
        this.face = face;
        this.sex = sex;
        this.point = 0L;
        this.lastLoginDate = System.currentTimeMillis();
    }
}
