package com.yc.tomcat.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;

import com.yc.utils.StringUtil;
import com.yc.web.core.HttpServletRequest;
import com.yc.web.core.HttpServletResponse;
import com.yc.web.core.Servlet;
import com.yc.web.core.ServletRequest;
import com.yc.web.core.ServletResponse;

/**
* 负责处理请求信息的类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月2日 下午7:33:36
*/
public class ServerService implements Runnable {
	private Socket sk = null ;
	private InputStream is = null ;
	private OutputStream os = null ;

	public ServerService(Socket sk) {
		this.sk = sk ; 
	}

	@Override
	public void run() {
		try {
			this.is = sk.getInputStream() ;
			this.os = sk.getOutputStream() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 解析请求头信息
		ServletRequest request = new HttpServletRequest( is );
		
		// 从请求头中获取请求地址 /tickets/index.html
		String url = request.getUrl();
		// 去掉最前边的斜杠 tickets/index.html
		String temp = url.substring(1) ;
		// 获取项目名称
		String projectName = temp.contains("/")? temp.substring( 0 , temp.indexOf("/")) : temp ;
		
		try {
			url = URLDecoder.decode(url,"UTF-8") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ServletResponse response = new HttpServletResponse( os , projectName ) ;
		
		// 是不是动态访问
		String clazz = ParseUrlPattern.getClass(url) ;
		if ( StringUtil.checkNull( clazz )) {
			// 如果没有这个类，说明是静态资源，直接重定位即可
			response.sendRedirect(url); 
			return ;
		}
		
		URLClassLoader loader = null ;
		URL classPath = null ;
		
		try {
			// 获取资源的路径		协议		IP地址	路径
			classPath = new URL("file" , null , ConstantInfo.BASE_PATH + "\\" + projectName + "\\bin") ;
			
			// 创建类加载器
			loader = new URLClassLoader( new URL[] {classPath} ) ;
			
			// 到指定路径下加载指定的类
			Class<?> cls = loader.loadClass(clazz);
			
			// 获取类的对象
			Servlet servlet = (Servlet) cls.newInstance() ; 
			// 调用service方法
			servlet.service(request, response);
			
		} catch (Exception e) {
			send500( e ) ;
			e.printStackTrace(); 
		}    
	}

	private void send500(Exception e) {
		
		try {
			String responseHeader = "HTTP/1.1 500 Error\r\n\r\n" + e.getMessage() ;
			os.write( responseHeader.getBytes() ); 
			os.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if ( os != null ) {
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} 	
				
	} 

}
