package com.yc.tomcat.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* 单例的读取配置文件的类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月2日 下午8:16:22
*/
@SuppressWarnings("all")
public class ReadConfig extends Properties{
	private static ReadConfig instance = new ReadConfig() ;
	
	private ReadConfig() {
		try {
			// 解析读取配置文件
			InputStream is = StartTomcat.class.getClassLoader().getResourceAsStream("web.properties");
			Properties ps = new Properties() ;  
			load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ReadConfig getInstance() {
		return instance ;
	}
}
