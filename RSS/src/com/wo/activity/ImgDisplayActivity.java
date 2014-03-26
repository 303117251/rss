package com.wo.activity;   

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.lidroid.xutils.BitmapUtils;
import com.wo.rss.R;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：24 Mar 2014 16:01:49
 * @类说明  用于展示从waterfall点击的图片
 */
public class ImgDisplayActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.img_display_activity);
        Intent intent = getIntent();
		String result = intent.getStringExtra("imgURL");
		ImageView imgView = (ImageView)findViewById(R.id.img_activity_img);
		BitmapUtils utils = new BitmapUtils(this);
		utils.display(imgView, result);
		
		imgView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		} );

	}
}
 