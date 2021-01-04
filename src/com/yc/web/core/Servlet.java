package com.yc.web.core;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月2日 下午8:24:43
*/
public interface Servlet {
	public void init() ;
	
	public void service( ServletRequest request , ServletResponse response );
	
	public void doGet(ServletRequest request , ServletResponse response);
	
	public void doPost(ServletRequest request , ServletResponse response);
}
