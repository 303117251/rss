package com.wo.data.manager;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import com.wo.activity.WaterfallActivity;
import com.wo.models.WaterfallImg;
import com.wo.service.JSONService;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：23 Mar 2014 18:22:41
 * @类说明 用于将json对象里的url和高度加载到容器（WaterfallImg）里并返回
 */
public class WaterfallActivityManager {
	WaterfallActivity activity;
	ArrayList<WaterfallImg> imgList  = new ArrayList<WaterfallImg>();
	
	/**
	 * 通过page数来获取json对象
	 * @param
	 * @return
	 */
	public void getWaterfallImgList(WaterfallActivity activity, int page) {
		this.activity = activity;
		new JSONService().getJson(activity.getApplicationContext(),
				"http://www.duitang.com/album/1733789/masn/p/" + page + "/24",
				this);
	}

	/**
	 * 用于得到json对象 完成回调context的addImg方法
	 * 
	 * @param
	 * @return
	 */
	public void sucessCallBack(JSONObject response) throws JSONException {
		JSONArray jsonArray = response.getJSONObject("data").getJSONArray(
				"blogs");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			WaterfallImg img = new WaterfallImg(object.getString("isrc"),
					object.getString("msg"), new Integer(
							object.getString("iht")));
			imgList.add(img);
		}
		activity.addImage(imgList);
		System.out.println(imgList.size());
	}
}
