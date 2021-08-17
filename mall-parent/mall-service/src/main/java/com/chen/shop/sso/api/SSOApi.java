package com.chen.shop.sso.api;

import com.chen.shop.model.buyer.vo.member.MemberVO;

public interface SSOApi {

    MemberVO findMemberById(Long id);

}
