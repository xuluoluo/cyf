package com.caiyunfei.cyf.interceptor;

import com.caiyunfei.cyf.annotation.AuthToken;
import com.caiyunfei.cyf.common.ComCodeMsg;
import com.caiyunfei.cyf.common.ComDataResult;
import com.caiyunfei.cyf.common.ComRT;
import com.caiyunfei.cyf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * description: AuthorizationInterceptor
 * date: 2019/9/29 9:53
 * author: 徐家斌
 * version: 1.0
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RedisUtil redisUtil;

    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "token";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    //存放登录用户模型Key的Request Key
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ComDataResult usDataResult = new ComDataResult();
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            String token = request.getHeader(httpHeaderName);
            Object userId = "";
            if (token != null && token.length() != 0) {
                userId = redisUtil.get(token);
                if (userId != null ) {
                    request.setAttribute(REQUEST_CURRENT_KEY, userId);
                    return true;
                }else{
                    response.setHeader("Content-Type", "text/html;charset=UTF-8");
                    usDataResult.setResult(false);
                    usDataResult.setResultMsg(ComCodeMsg.TOKEN_EXPIRED_MSG);
                    response.getWriter().println(ComRT.success(usDataResult));
                    return false;
                }
            }else{
                response.setHeader("Content-Type", "text/html;charset=UTF-8");
                usDataResult.setResult(false);
                usDataResult.setResultMsg("无token");
                response.getWriter().println(ComRT.success(usDataResult));
                return false;
            }
        }

        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
