package com.yc.web.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月2日 下午8:34:14
*/
public class HttpServletRequest implements ServletRequest {
	private String method ;
	private String url ;
	private BufferedReader reader ;
	private Map<String , String> parameter = new HashMap<String,String>() ;
	private String protocalVersion ;
	private InputStream is ;

	public HttpServletRequest(InputStream is) {
		this.is = is ;
		parse();
	}

	@Override
	public void parse() {
		try {
			reader = new BufferedReader( new InputStreamReader(is) ) ;
			List<String> headers = new ArrayList<>() ;
			String line = null ;
			while ( ( line = reader.readLine() ) != null && !"".equals(line)) {
				headers.add(line) ;
			}
			
//			headers.forEach( System.out :: println );
			// 解析起始行
			parseFristLine( headers.get(0) ) ;
			
			// 解析参数
			parseParameter( headers ) ;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	
	/**
	 * 解析参数
	 * @param headers
	 */
	private void parseParameter(List<String> headers) {
		// 如果是GET请求，那么参数只会在起始行中有
		
		// 如果是POST请求，那么要获取头部字段中的Content-Type和Content-Length
		
	}

	
	/**
	 * 解析请求首行
	 * @param str
	 */
	private void parseFristLine(String str) {
		
		if ( StringUtil.checkNull( str )) {
			return ;
		}
		
		// 将请求首行按照空格分隔
		String[] arrs = str.split(" ");
		this.method = arrs[0] ;
		this.protocalVersion = arrs[2] ;
		
		if ( arrs[1].equals("?")) {
			// 说明有参数
			this.url = arrs[1].substring(0 , arrs[1].indexOf("?")) ; 
		}else {
			this.url = arrs[1] ;
		} 
	}

	@Override
	public String getParameter(String key) {
		return this.parameter.get(key);
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	public String getProtocalVersion() {
		return this.protocalVersion;
	}
	
	

}
