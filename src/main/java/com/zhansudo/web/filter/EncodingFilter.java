package com.zhansudo.web.filter;

import com.zhansudo.utils.MyRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 处理请求乱码
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        MyRequest myRequest = new MyRequest(httpServletRequest);
        // 处理响应乱码
        servletResponse.setContentType("text/html; charset=UTF-8");
        filterChain.doFilter(myRequest, servletResponse);
    }

}
