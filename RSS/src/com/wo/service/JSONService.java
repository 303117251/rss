package com.wo.service;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wo.data.manager.WaterfallActivityManager;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：23 Mar 2014 17:22:20
 * @类说明 用goolge volley 将url生成json对象 
 */
public class JSONService {

	/**
	 * 请求json对象 成功调用waterfall datamanager 的sucesscallback 失败调用faillcallback
	 * 
	 * @param context ，url， watrefallactivitymanager
	 * @return
	 */
	public void getJson(Context context, String url,
			final WaterfallActivityManager manager) {
		// 请求json
		RequestQueue mQueue = Volley.newRequestQueue(context);
		JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.GET, url,
				null, new Response.Listener<JSONObject>() {
					public void onResponse(JSONObject response) {
						try {
							manager.sucessCallBack(response);
						} catch (JSONException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
					}

				});
		mQueue.add(jsonRequest);
		mQueue.start();
	}
}
