package com.chen.shop.sso.api;

import com.chen.shop.model.buyer.vo.member.MemberVO;
import com.chen.shop.sso.service.MemberService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName SSOApiImpl
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 20:43
 */
@DubboService(version = "1.0.0", interfaceClass = SSOApi.class)
public class SSOApiImpl implements SSOApi {

    @Autowired
    private MemberService memberService;

    @Override
    public MemberVO findMemberById(Long id) {
        return memberService.findMemberById(id);
    }
}
