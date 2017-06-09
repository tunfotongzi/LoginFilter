package com.imooc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="loginFilter", urlPatterns="/*")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		// 先将ServletRequest转为它带有HTTP操作方法的子类
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//获取登录信息
		HttpSession session = request.getSession();
		
		//记录未登录前的所有可能的地址
//		String noLoginPaths = config.getInitParameter("noLoginPaths");
		
		//因为该过滤器是针对所有页面设置，所以可以用于设置字符集，而不需要每个Servlet中都去设置
		request.setCharacterEncoding("UTF-8");
		
		//如果页面是login.jsp那么直接放行并return，不再继续向下执行代码
		if(request.getRequestURI().indexOf("login.jsp")!=-1 
				|| request.getRequestURI().indexOf("LoginServlet")!=-1){
			arg2.doFilter(arg0, arg1);
			return;
		}
		
		
		// 如果用户已经登录就继续请求web资源，否则跳转登录页面
		if(session.getAttribute("username")!=null){
			arg2.doFilter(arg0, arg1);
		}else{
			response.sendRedirect("login.jsp");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
