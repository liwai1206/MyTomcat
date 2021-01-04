package com.yc.tomcat.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*	启动类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月2日 下午7:33:52
*/
public class StartTomcat { 

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		try { 
			ReadConfig ps = ReadConfig.getInstance();
			
			int port = Integer.parseInt( ps.getProperty("port")) ;
			
			// 启用一个ServerSocket
			ServerSocket ssk = new ServerSocket( port ) ; 
			System.out.println( "Tomcat启动成功，占用端口" + port + "...");
			
			// 解析项目下WEB-INF文件夹下的web.xml文件
			new ParseUrlPattern() ;
			
			// 读取web.xml配置文件
			new ParseWebXml() ;
			
			// 启动一个线程或使用线程池处理用户发过来的请求 --> socket
			// 创建一个线程池
			ExecutorService es = Executors.newFixedThreadPool(20) ;
			Socket sk;
			while ( true ) {
				// 等待客户端连接
				sk = ssk.accept();
				
				// 当有客户端成功连接后，开启一个线程,使用线程池
				
				es.submit( new ServerService(sk) );
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	

}
