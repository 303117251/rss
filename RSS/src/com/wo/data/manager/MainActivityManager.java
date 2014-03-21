package com.wo.data.manager;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.wo.models.Brief;
import com.wo.service.Downloader;
import com.wo.service.XMLParser;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：17 Mar 2014 21:31:13 类说明 给Main Activity UI提供数据
 */

public class MainActivityManager {
	public List<Brief> getData() throws SAXException, IOException,
			ParserConfigurationException {
		XMLParser xml = new XMLParser();
		return xml.parseFile(Downloader.loadFileFromUrl(Downloader.URLSTRING));
	}

}
