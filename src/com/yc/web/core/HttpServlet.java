package com.yc.web.core;

import com.yc.tomcat.core.ConstantInfo;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月3日 上午11:46:31
*/
public class HttpServlet implements Servlet{

	@Override
	public void init() {
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) {
		switch ( request.getMethod() ) {
		case ConstantInfo.REQUEST_METHOD_GET: doGet(request, response); break;
		case ConstantInfo.REQUEST_METHOD_POST: doPost(request, response); break;
		}
	}

	@Override
	public void doGet(ServletRequest request, ServletResponse response) {
	}

	@Override
	public void doPost(ServletRequest request, ServletResponse response) {
	}
 

}
