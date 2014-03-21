package com.wo.base;

import java.util.ArrayList;

import android.os.Handler;
import android.os.Message;

import com.wo.models.Brief;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：18 Mar 2014 16:55:42
 * @类说明 Handler 用于接受MainActivityManager提供的数据 并更新adapter
 */
public class UpdateDataHandler extends Handler {
	ImgAndTxtArrayAdapter adapter;

	public UpdateDataHandler(ImgAndTxtArrayAdapter adapter) {
		this.adapter = adapter;
	}

	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			adapter.addAll((ArrayList<Brief>)msg.obj);
		}
	}
}
