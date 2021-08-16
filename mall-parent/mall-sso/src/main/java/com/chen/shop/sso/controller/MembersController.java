package com.chen.shop.sso.controller;

import com.chen.shop.common.security.Token;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.enums.VerificationEnums;
import com.chen.shop.sso.service.MemberService;
import com.chen.shop.sso.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MembersController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/15 15:24
 */
@RestController
@RequestMapping("/members")
public class MembersController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private VerificationService verificationService;

    @PostMapping("/userLogin")
    public Result<Token> userLogin(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestHeader String uuid) {
        /**
         *1、登录之前进行了 滑块验证，登录的时候需要校验一下滑块验证的结果
         * 2、调用service进行登录
         */
        if (verificationService.check(uuid, VerificationEnums.LOGIN)) {
            return this.memberService.usernameLogin(username, password);
        } else {
            return Result.fail(-999, "请重新验证");
        }
    }
}
