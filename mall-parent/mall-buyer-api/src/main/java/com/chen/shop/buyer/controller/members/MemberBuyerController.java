package com.chen.shop.buyer.controller.members;

import com.chen.shop.buyer.service.members.MemberBuyerService;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.vo.member.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberBuyerController
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 17:19
 */
@RestController
@RequestMapping("/members")
public class MemberBuyerController {

    @Autowired
    private MemberBuyerService memberBuyerService;

    @GetMapping
    public Result<MemberVO> getUserInfo() {
        return memberBuyerService.getUserInfo();
    }

}
