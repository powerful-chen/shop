package com.chen.shop.sso.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.shop.sso.mapper.MemberMapper;
import com.chen.shop.common.cache.CachePrefix;
import com.chen.shop.common.context.ThreadContextHolder;
import com.chen.shop.common.security.AuthUser;
import com.chen.shop.common.security.Token;
import com.chen.shop.common.security.UserEnums;
import com.chen.shop.common.utils.token.TokenUtils;
import com.chen.shop.common.vo.Result;
import com.chen.shop.model.buyer.enums.ClientType;
import com.chen.shop.model.buyer.pojo.Member;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MemberService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/15 15:29
 */
@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public Result<Token> usernameLogin(String username, String password) {
        /**
         * 1、根据用户名查询Member信息
         * 2、如果为null，就是用户不存在
         * 3、密码进行匹配，如果不匹配 密码不正确
         * 4、token 生成token
         * 5、jwt生成token，token放入redis当中，accessToken 过期时间短 refreshToken过期时间长
         */
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getUsername, username).eq(Member::getDisabled, false);
        Member member = memberMapper.selectOne(queryWrapper);
        if (member == null) {
            return Result.fail(-999, "用户不存在");
        }
        //用的security的密码类
        if (!new BCryptPasswordEncoder().matches(password, member.getPassword())) {
            return Result.fail(-999, "密码错误");
        }
        //获取登录平台
        String clientType = ThreadContextHolder.getHttpRequest().getHeader("clientType");
        if (StringUtils.isBlank(clientType)) {
            clientType = "0";
        }
        ClientType type = ClientType.codeOf(Integer.parseInt(clientType));
        if (type == null) {
            type = ClientType.UNKNOWN;
        }
        member.setClientEnum(type.getCode());
        //一般登录的时候，会记录用户最后一次的登录时间
        //MQ 考虑使用mq，把信息发到mq中，由mq的消费者去更新
        member.setLastLoginDate(System.currentTimeMillis());
        this.memberMapper.updateById(member);

        //生成token
        Token token = genToken(member);

        return Result.success(token);
    }

    private Token genToken(Member member) {
        Token token = new Token();
        //accessToken refreshToken
        AuthUser authUser = new AuthUser(member.getUsername(), String.valueOf(member.getId()), member.getNickName(), UserEnums.MEMBER);
        String accessToken = TokenUtils.createToken(member.getUsername(), authUser, 7 * 24 * 60L);
        //放入redis当中
        redisTemplate.opsForValue().set(CachePrefix.ACCESS_TOKEN.name() + UserEnums.MEMBER.name() + accessToken, "true", 7, TimeUnit.DAYS);
        String refreshToken = TokenUtils.createToken(member.getUsername(), authUser, 15 * 24 * 60L);
        redisTemplate.opsForValue().set(CachePrefix.REFRESH_TOKEN.name() + UserEnums.MEMBER.name() + refreshToken, "true", 15, TimeUnit.DAYS);
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        return token;
    }
}
