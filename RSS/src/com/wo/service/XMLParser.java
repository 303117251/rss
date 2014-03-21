package com.wo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wo.models.Brief;


/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：17 Mar 2014 16:00:38
 * @类说明 用于解析百度xml格式的rss
 */
public class XMLParser {
	public ArrayList<Brief> parseFile(InputStream is) throws SAXException, IOException, ParserConfigurationException {
		ArrayList<Brief> articles = new ArrayList<Brief>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // 取得DocumentBuilderFactory实例
		DocumentBuilder builder = factory.newDocumentBuilder(); // 从factory获取DocumentBuilder实例
		Document doc = builder.parse(is); // 解析输入流 得到Document实例
		Element rootElement = doc.getDocumentElement();
		NodeList items = rootElement.getElementsByTagName("item");
		for (int i = 0; i < items.getLength(); i++) {
			Brief brief = new Brief();
			Node item = items.item(i);
			NodeList properties = item.getChildNodes();
			for (int j = 0; j < properties.getLength(); j++) {
				Node property = properties.item(j);
				String nodeName = property.getNodeName();
				if (nodeName.equals("link")) {
					brief.setLink(property.getFirstChild().getNodeValue());
				} else if (nodeName.equals("description")) {
					String imgSource= imgRefine(property.getFirstChild().getNodeValue());
					if(imgSource!=null) {
						brief.setImg(imgSource);
						brief.setContent(desRefine(property.getFirstChild().getNodeValue()));
					}else{
						brief.setContent(property.getFirstChild().getNodeValue());
					}
				} 
			}
			articles.add(brief);
		}
		return articles;
	}
	
	//提炼img url
	private  String imgRefine(String content) {
		Pattern p = Pattern.compile("src=\".*\">");
		Matcher matcher = p.matcher(content);
		StringBuffer sb = new StringBuffer();
		String result = null;
		while (matcher.find()) {
			sb.append(matcher.group());
		}
		if (sb.length()!=0) {
			result = sb.substring(5, sb.length() - 2);
		}
		return result;
	}
	
	//提炼描述
	private static String desRefine(String content) {
		Pattern p = Pattern.compile("<br>.*");
		Matcher matcher = p.matcher(content);
		StringBuffer sb = new StringBuffer();
		String result = null;
		while (matcher.find()) {
			sb.append(matcher.group());
		}
		if (sb.length()!=0) {
			result = sb.substring(4);
		}
		return result;
	}
	
}
