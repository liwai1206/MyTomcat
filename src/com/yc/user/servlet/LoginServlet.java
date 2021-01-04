package com.yc.user.servlet;

import com.yc.web.core.HttpServlet;
import com.yc.web.core.ServletRequest;
import com.yc.web.core.ServletResponse;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月3日 上午11:46:09
*/
public class LoginServlet extends HttpServlet{
	@Override
	public void doGet(ServletRequest request, ServletResponse response) {
		doPost(request, response);
	}
	
	@Override
	public void doPost(ServletRequest request, ServletResponse response) {
		String name = request.getParameter("name");
		String pwd  = request.getParameter("pwd");
		
		System.out.println( name + "--> " + pwd );
		
		response.sendRedirect("login.html");
	}
}
