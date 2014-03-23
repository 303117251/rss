package com.wo.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.wo.base.LazyScrollView;
import com.wo.data.manager.WaterfallActivityManager;
import com.wo.models.WaterfallImg;
import com.wo.rss.R;
import com.wo.service.WaterfallService;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：22 Mar 2014 21:11:57
 * @类说明 加载瀑布流图片的acitivity
 */
public class WaterfallActivity extends Activity implements
		LazyScrollView.OnScrollListener {
	private LazyScrollView lazyScrollView;
	private LinearLayout waterfall_container;
	private ArrayList<LinearLayout> linearLayouts;// 列布局
	private LinearLayout progressbar;// 进度条
	private TextView loadtext;// 底部加载view
	private int current_page = 0;// 页码
	private int count = 24;// 每页显示的个数
	private int column = 3;// 显示列数
	private int item_width;// 每一个item的宽度
	private WaterfallActivityManager waterfallMa;
	private BitmapUtils utils;
	private WaterfallService ws;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waterfall_activity);
		initView();
	}

	public void initView() {
		lazyScrollView = (LazyScrollView) findViewById(R.id.waterfall_scroll);
		lazyScrollView.getView();
		lazyScrollView.setOnScrollListener(this);
		waterfall_container = (LinearLayout) findViewById(R.id.waterfall_container);
		item_width = getWindowManager().getDefaultDisplay().getWidth() / column;
		linearLayouts = new ArrayList<LinearLayout>();
		utils = new BitmapUtils(this);
		waterfallMa = new WaterfallActivityManager();
		ws = new WaterfallService();
		// 添加三列到waterfall_container
		for (int i = 0; i < column; i++) {
			LinearLayout layout = new LinearLayout(this);
			LinearLayout.LayoutParams itemParam = new LinearLayout.LayoutParams(
					item_width, LayoutParams.WRAP_CONTENT);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.setLayoutParams(itemParam);
			layout.setTag(i);
			linearLayouts.add(layout);
			waterfall_container.addView(layout, i);
		}
		waterfallMa.getWaterfallImgList(this, current_page);
	}

	/***
	 * 加载更多图片
	 * 
	 * @param current_page
	 * @param count
	 */
	public void addImage(ArrayList<WaterfallImg> imgList) {
		for (int i = current_page * count; i < imgList.size(); i++) {
			ImageView imageView = new ImageView(this);
			WaterfallImg img = imgList.get(i);
			int height = img.getHeight();
			utils.configDefaultBitmapMaxSize(item_width, (height * item_width)
					/ item_width);
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
			imageView.setLayoutParams(layoutParams);
			int min = ws.getMin();
			ws.addHeight(min, height);
			linearLayouts.get(min).addView(imageView);
			utils.display(imageView, img.getUrl());
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});

		}
	}

	@Override
	public void onBottom() {
		waterfallMa.getWaterfallImgList(this, ++current_page);
	}

	@Override
	public void onTop() {

	}

	@Override
	public void onScroll() {

	}

}