package com.yc.tomcat.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
* 解析web.xml的类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2021年1月3日 上午9:09:50
*/
public class ParseWebXml {
	private static Map<String, String> map = new HashMap<>() ;

	public ParseWebXml() {
		 init() ;
	}

	// 初始化方法，解析web.xml文件
	private void init() {
		SAXReader reader = new SAXReader() ;
		Document doc = null ;
		
		try {
			doc = reader.read( this.getClass().getClassLoader().getResourceAsStream("web.xml") );
			//  //mime-mapping：表示所有的mime-mapping 
			List<Node> mimes = doc.selectNodes("//mime-mapping");
			
			for (Node el : mimes) {
				map.put( el.selectSingleNode("extension").getText().trim(), el.selectSingleNode("mime-type").getText().trim() ) ;
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
	}
	
	public Map<String, String> getMap(){
		return map ;
	}
	
	/**
	 * 根据文件后缀返回相应的mime类型
	 * @param key
	 * @return
	 */
	public static String getContentType( String key ) {
		return map.getOrDefault(key, null) ;
	}
	
	
}
