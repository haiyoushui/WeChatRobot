package com.eagle.WeChatRobot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 用于处理AJAX跨界的问题
 * @author Administrator
 *
 */
@Component
public class SimpleCORSFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "http://10.44.111.123:3001");	//当为*时，表示匹配所有地址
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
		response.setHeader("Access-Control-Allow-Headers",  "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
