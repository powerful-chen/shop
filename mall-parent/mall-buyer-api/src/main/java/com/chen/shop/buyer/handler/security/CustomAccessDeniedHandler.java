package com.chen.shop.buyer.handler.security;

import com.chen.shop.common.utils.ResponseUtil;
import com.chen.shop.common.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CustomAccessDeniedHandler
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/16 12:07
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    //认证失败的返回
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.output(httpServletResponse, Result.noPermission());
    }
}
