package com.arnasoft.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.arnasoft.context.BaseContext;
import com.arnasoft.exception.IsTokenException;
import com.arnasoft.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        if (token == null) throw new IsTokenException("Token不存在");

        //2、校验令牌
        try {
            long id = StpUtil.getLoginIdAsLong();
            log.info("令牌:{}", token);
            log.info("用户ID:{}", id);
            // 将用户id存储到ThreadLocal
            BaseContext.setCurrentId(id);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            throw new IsTokenException(ex.getMessage());
        }
    }
}
