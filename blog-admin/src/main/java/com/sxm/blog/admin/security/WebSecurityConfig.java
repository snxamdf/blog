package com.sxm.blog.admin.security;import com.sxm.blog.admin.security.filter.ValidateCodeFilter;import com.sxm.blog.admin.security.handler.LoginFailureHandler;import com.sxm.blog.admin.security.handler.LoginSuccessHandler;import com.sxm.blog.admin.security.handler.LogoutSuccessHandler;import com.sxm.blog.admin.service.impl.UserDetailServiceImpl;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.annotation.Configurable;import org.springframework.context.annotation.Bean;import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.annotation.web.builders.WebSecurity;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;/** * prePostEnabled :决定Spring Security的前注解是否可用 [@PreAuthorize,@PostAuthorize,..] * secureEnabled : 决定是否Spring Security的保障注解 [@Secured] 是否可用 * jsr250Enabled ：决定 JSR-250 annotations 注解[@RolesAllowed..] 是否可用. */@Configurable@EnableWebSecurity//开启 Spring Security 方法级安全注解 @EnableGlobalMethodSecurity@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)public class WebSecurityConfig extends WebSecurityConfigurerAdapter {    @Autowired    private CustomAccessDeniedHandler customAccessDeniedHandler;    @Autowired    private LoginFailureHandler failureHandler;    @Autowired    private LoginSuccessHandler successHandler;    @Autowired    private LogoutSuccessHandler loginoutHandler;    @Autowired    private UserDetailServiceImpl userDetailService;    /**     * 静态资源设置     */    @Override    public void configure(WebSecurity webSecurity) {        //不拦截静态资源,所有用户均可访问的资源        webSecurity.ignoring().antMatchers(                "/",                "/css/**",                "/js/**",                "/images/**",                "/layui/**"        );    }    /**     * http请求设置     */    @Override    public void configure(HttpSecurity http) throws Exception {        //验证码filter        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();        validateCodeFilter.setAuthenticationFailureHandler(this.failureHandler);        http.csrf().disable(); //禁用csrf功能 将防护跨站请求伪造的功能置为不可用        http.headers().frameOptions().disable();//解决 in a frame because it set 'X-Frame-Options' to 'DENY' 问题        //将图形验证码放到用户名和密码之前校验        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);        //http.anonymous().disable();        //请求做授权配置        http.authorizeRequests()                //不需要身份认证的连接                .antMatchers("/login/**", "/api/**")//不拦截登录相关方法                .permitAll()                //.antMatchers("/user").hasRole("ADMIN")  // user接口只有ADMIN角色的可以访问                .anyRequest()//除不拦截请求所有连接                .authenticated()//都需要身份认证                //.anyRequest()                //.access("@rbacPermission.hasPermission(request, authentication)")//根据账号权限访问                //登录控制                .and()                .formLogin()                .loginPage("/")//登录请求页                .loginPage("/login")//登录请求页                .loginProcessingUrl("/api/login")  //登录POST请求路径                .successHandler(this.successHandler).failureHandler(this.failureHandler)//设置登录成功和登录失败handler                .usernameParameter("username") //登录用户名参数                .passwordParameter("password") //登录密码参数                //.defaultSuccessUrl("/api/main")//默认登录成功页面                .and()                .exceptionHandling()                .accessDeniedHandler(customAccessDeniedHandler) //无权限处理器                //登出控制                .and()                .logout()                .logoutUrl("/api/logout")                .logoutSuccessHandler(loginoutHandler);                //.logoutSuccessUrl("/login?logout");  //退出登录成功URL    }    /**     * 自定义获取用户信息接口     */    @Override    public void configure(AuthenticationManagerBuilder auth) throws Exception {        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());    }    /**     * 密码加密算法     */    @Bean    public BCryptPasswordEncoder passwordEncoder() {        return new BCryptPasswordEncoder();    }}