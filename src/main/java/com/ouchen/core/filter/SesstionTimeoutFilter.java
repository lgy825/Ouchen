package com.ouchen.core.filter;

import org.apache.shiro.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:SesstionTimeoutFilter
 *
 * @author yangbin
 * @create 2018-03-27 19:06
 */
public class SesstionTimeoutFilter implements Filter {
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String basePath = request.getContextPath();
		request.setAttribute("basePath", basePath);
		if (!SecurityUtils.getSubject().isAuthenticated() &&
				request.getRequestURI().indexOf("/index/tologin") >= 0) {
			//判断session里是否有用户信息
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				//如果是ajax请求响应头会有，x-requested-with
				response.setHeader("session-status", "timeout");//在响应头设置session状态
				return;
			}
		}
		filterChain.doFilter(request, servletResponse);
	}

	@Override
	public void destroy() {

		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

		// TODO Auto-generated method stub

	}
}
