package com.yc.web.core;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月2日 下午8:26:49
*/
public interface ServletRequest {
	/**
	 * 解析请求的方法
	 */
	public void parse();
	
	/**
	 * 获取请求参数的方法
	 * @param key
	 * @return
	 */
	public String getParameter( String key ) ;
	
	/**
	 * 获取请求方式的方法
	 * @return
	 */
	public String getMethod();
	
	/**
	 * 获取请求地址的方法
	 * @return
	 */
	public String getUrl() ;
}
