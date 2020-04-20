package com.example.boot.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;

import com.example.boot.common.constant.Constant;
import com.example.boot.core.error.CommonErrorMsg;
import com.example.boot.core.result.ResultMsg;
import com.example.boot.core.util.SessionUtil;
import com.example.boot.core.util.WebUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 过滤器
 * @author panzhi
 *
 */
@Slf4j
public class LoginFilter implements Filter {

    /**
     * 白名单
     */
    String[] excludeUrls = {
            "(?:/images/|/css/|/js/|/template/|/static/|/web/|/constant/).+$",
            "/user/login",
            "/user/createImage"
    };
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException, SecurityException {
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String serviceName = request.getServerName();
        //添加到白名单的URL放行
        for (String url : excludeUrls) {
            if (requestUri.matches(contextPath + url) || (serviceName.matches(url))) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        if(RequestMethod.OPTIONS.name().equals(request.getMethod())){
            filterChain.doFilter(request, response);
            return;
        }
        //检查session是否还存在
//        if(!SessionUtil.checkSessionKV(request, Constant.SessionInfo.USER_INFO)) {
//        	log.info("用户没有登录，非法请求！！！");
//        	ResultMsg<Object> resultMsg = new ResultMsg<>(CommonErrorMsg.ILLEGE_ERROR.getCode(),CommonErrorMsg.ILLEGE_ERROR.getMessage());
//        	WebUtil.buildPrintWriter(response, resultMsg);
//		    return;
//        }
        WebUtil.bindRequestAndResponse(request);
        filterChain.doFilter(request, response);
        return;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    	
    }

}

