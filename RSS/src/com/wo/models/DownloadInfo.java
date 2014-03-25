package com.wo.models;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：25 Mar 2014 11:00:04
 * @类说明
 */
public class DownloadInfo {
	private String imgUrl;
	private String name;
	private String desc;
	private Boolean isAd;
	public DownloadInfo(String imgUrl, String name, String desc, Boolean isAd) {
		super();
		this.imgUrl = imgUrl;
		this.name = name;
		this.desc = desc;
		this.isAd = isAd;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Boolean getIsAd() {
		return isAd;
	}


}
