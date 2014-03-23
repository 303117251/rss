package com.wo.models;   
/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：23 Mar 2014 18:17:35
 * @类说明 用于存储waterfall图片的model
 */
public class WaterfallImg {
	private String url;
	private String content;
	private int height;
	
	public WaterfallImg(String url, String content, int height) {
		super();
		this.url = url;
		this.content = content;
		this.height = height;
	}
	public String getContent() {
		return content;
	}
	public String getUrl() {
		return url;
	}
	public int getHeight() {
		return height;
	}
	
}
 