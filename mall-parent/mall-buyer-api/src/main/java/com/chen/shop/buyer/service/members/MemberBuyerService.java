package com.chen.shop.buyer.service.members;

import com.chen.shop.buyer.handler.security.UserContext;
import com.chen.shop.common.security.AuthUser;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.vo.member.MemberVO;
import com.chen.shop.sso.api.SSOApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @ClassName MemberBuyerService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 17:23
 */
@Service
public class MemberBuyerService {

    @DubboReference(version = "1.0.0")
    private SSOApi ssoApi;

    public Result<MemberVO> getUserInfo() {
        AuthUser currentUser = UserContext.getCurrentUser();
        if (currentUser != null) {
            String id = currentUser.getId();
            MemberVO memberById = ssoApi.findMemberById(Long.parseLong(id));
            return Result.success(memberById);
        }
        return Result.fail(-999, "登录已过期");
    }
}
