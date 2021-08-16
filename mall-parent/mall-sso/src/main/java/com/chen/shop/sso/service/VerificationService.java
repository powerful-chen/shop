package com.chen.shop.sso.service;

import com.chen.shop.model.buyer.enums.VerificationEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName VerificationService
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/15 15:30
 */
@Service
public class VerificationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean check(String uuid, VerificationEnums verificationEnums) {
        Boolean hasKey = redisTemplate.hasKey("VERIFICATION_IMAGE_RESULT_" + verificationEnums.name() + uuid);
        return hasKey != null && hasKey;
    }
}
