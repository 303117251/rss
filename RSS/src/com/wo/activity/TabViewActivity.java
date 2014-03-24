package com.wo.activity;   


import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.wo.rss.R;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：22 Mar 2014 13:49:03
 * @类说明  TabHost用来展示并切换各个activity
 * 
 */
public class TabViewActivity extends TabActivity {
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      setContentView(R.layout.tab_activity);
	        Intent intent1 = new Intent(this,RSSActivity.class);
	        Intent intent2 = new Intent(this,WaterfallActivity.class);
	        Intent intent3 = new Intent(this,Gallery3DActivity.class);
	        TabHost tabHost = getTabHost();
	        tabHost.addTab(tabHost.newTabSpec("tab1")
	                .setIndicator("RSS",getResources().getDrawable(android.R.drawable.ic_menu_camera))
	                .setContent(intent1));
	        tabHost.addTab(tabHost.newTabSpec("tab2")
	                .setIndicator("Waterfall",getResources().getDrawable(android.R.drawable.ic_menu_camera))
	                .setContent(intent2));
	        tabHost.addTab(tabHost.newTabSpec("tab3")
	                .setIndicator("Coverflow",getResources().getDrawable(android.R.drawable.ic_menu_camera))
	                .setContent(intent3));

	    }
}
 