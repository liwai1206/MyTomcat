package com.yc.tomcat.core;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月2日 下午8:34:05
*/
public interface ConstantInfo {
	public static String BASE_PATH = ReadConfig.getInstance().getProperty("path") ;
	public static String DEFAULT_RESOURCE = ReadConfig.getInstance().getProperty("default") ;
	public static String REQUEST_METHOD_GET = "GET";
	public static String REQUEST_METHOD_POST = "POST";
	
}
