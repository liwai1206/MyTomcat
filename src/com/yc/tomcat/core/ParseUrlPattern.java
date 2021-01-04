package com.yc.tomcat.core;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月3日 下午8:22:26
*/
public class ParseUrlPattern {
	private String basePath = ConstantInfo.BASE_PATH ;
	private static Map<String, String> urlPattern  = new HashMap<>() ;
	
	public ParseUrlPattern() {
		parse() ;
		
//		urlPattern.forEach( (key,val) -> {
//			System.out.println( key + ":" + val );
//		});
	}

	
	/**
	 * 获取项目下面的web.xml文件
	 */
	private void parse() {
		
		// 获取basePath文件夹下的目录
		File[] files = new File(basePath).listFiles() ;
		
		if ( files == null || files.length <= 0 ) {
			// 如果当前文件夹下面没有项目
			return ;
		}
		
		// 当前项目名
		String projectName = null ;
		// WEB-INF文件夹下面的web.xml文件
		File webFile = null ;
		
		for (File file : files) {
			if ( !file.isDirectory() ) {
				// 如果此文件不是一个文件夹
				continue ;
			}
			
			// 获取当前文件夹名称
			projectName = file.getName() ;
			
			webFile = new File( file,"WEB-INF/web.xml") ;
			
			if ( !webFile.exists() || !webFile.isFile() ) {
				// 如果这个文件不存在或者不是一个文件
				continue ;
			}
			
			// 否则，这个WEB-INF下面的web.xml文件，则解析web.xml文件
			parseXml( projectName , webFile ) ;
			
		}
		 
	}

	
	/**
	 * 对web.xml文件进行解析
	 * @param projectName 项目名
	 * @param webFile web.xml文件
	 */
	private void parseXml(String projectName, File webFile) {
		
		SAXReader reader = new SAXReader() ;
		Document doc = null ;
		
		try {
			doc = reader.read(webFile) ;
			List<Node> servletList = doc.selectNodes("//servlet");
			
			if ( servletList.isEmpty() ) {
				// 如果集合为空，即文件中没有对servlet节点的定义
				return ;
			}
			
			for (Node node : servletList) {
				// 将对servlet映射访问的配置加载到list集合中，以访问路径为键，以servlet资源类为值
				urlPattern.put( "/" +  projectName + node.selectSingleNode("url-pattern").getText().trim(), node.selectSingleNode("servlet-class").getText().trim() ) ;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * 根据url访问路径获取对应的资源类
	 * @param url
	 * @return
	 */
	public static String getClass( String url) {
		return urlPattern.getOrDefault(url, null) ;
	}
	
	
}
