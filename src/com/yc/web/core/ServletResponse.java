package com.yc.web.core;

import java.io.IOException;
import java.io.PrintWriter;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月2日 下午8:26:55
*/
public interface ServletResponse {
	/**
	 * 重定向方法
	 * @param url
	 */
	public void sendRedirect( String url ) ;
	
	public PrintWriter getWriter()  throws IOException ;
}
