package com.wo.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.wo.rss.R;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：20 Mar 2014 15:56:31
 * @类说明 用于浏览详细内容 webview
 */

public class WebViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_activity);
		Intent intent = getIntent();
		String result = intent.getStringExtra("shttpURL");
		Intent intentWeb = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri.parse("http://www.cnblogs.com");
		intent.setData(content_url);
		startActivity(intent);

	}
}