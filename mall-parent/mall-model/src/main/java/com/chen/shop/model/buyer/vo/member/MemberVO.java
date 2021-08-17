package com.chen.shop.model.buyer.vo.member;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName MemberVO
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 17:12
 */
@Data
@NoArgsConstructor
public class MemberVO implements Serializable {

    private String id;

    private String username;

    private String nickName;
    //1 男 0 女
    private Integer sex;

    private Date birthday;

    private String regionId;
    //会员地址
    private String region;

    private String mobile;

    private Long point;

    private String face;

    private Boolean haveStore;

    private String storeId;

    private String grandId;

    private Long experience;

}
