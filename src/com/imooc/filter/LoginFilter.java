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
		
		// �Ƚ�ServletRequestתΪ������HTTP��������������
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//��ȡ��¼��Ϣ
		HttpSession session = request.getSession();
		
		//��¼δ��¼ǰ�����п��ܵĵ�ַ
//		String noLoginPaths = config.getInitParameter("noLoginPaths");
		
		//��Ϊ�ù��������������ҳ�����ã����Կ������������ַ�����������Ҫÿ��Servlet�ж�ȥ����
		request.setCharacterEncoding("UTF-8");
		
		//���ҳ����login.jsp��ôֱ�ӷ��в�return�����ټ�������ִ�д���
		if(request.getRequestURI().indexOf("login.jsp")!=-1 
				|| request.getRequestURI().indexOf("LoginServlet")!=-1){
			arg2.doFilter(arg0, arg1);
			return;
		}
		
		
		// ����û��Ѿ���¼�ͼ�������web��Դ��������ת��¼ҳ��
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
