package com.sxm.blog.admin.security.filter;import com.sxm.blog.admin.exception.ValidateCodeException;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.web.authentication.AuthenticationFailureHandler;import org.springframework.stereotype.Component;import org.springframework.util.StringUtils;import org.springframework.web.filter.OncePerRequestFilter;import javax.servlet.FilterChain;import javax.servlet.ServletException;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.IOException;/** * @author 杨红岩 * @description 验证码校验 * @date 2020/5/8 */@Componentpublic class ValidateCodeFilter extends OncePerRequestFilter {    private AuthenticationFailureHandler authenticationFailureHandler;    @Override    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {        String reqURI = httpServletRequest.getRequestURI();        if ("/api/login".equals(reqURI)                && "post".equalsIgnoreCase(httpServletRequest.getMethod())) {            // 1. 进行验证码的校验            Object validateCodeCache = httpServletRequest.getSession().getAttribute("validateCode");            String validateCode = httpServletRequest.getParameter("validateCode");            if ("123123".equals(validateCodeCache)) {                ValidateCodeException validateCodeException = new ValidateCodeException("验证码不正确");                // 2. 如果校验不通过，调用SpringSecurity的校验失败处理器                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, validateCodeException);                return;            }        }        // 3. 校验通过，就放行        filterChain.doFilter(httpServletRequest, httpServletResponse);    }    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {        this.authenticationFailureHandler = authenticationFailureHandler;    }}