package com.wo.models;

import com.wo.rss.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：18 Mar 2014 10:42:33 
 * @类说明:用于存放图片和text的Cache
 */
public class ViewCache {
	private View baseView;
	private TextView textView;
	private ImageView imageView;

	public ViewCache(View baseView) {
		this.baseView = baseView;
	}

	public TextView getTextView() {
		if (textView == null) {
			textView = (TextView) baseView.findViewById(R.id.info);
		}
		return textView;
	}

	public ImageView getImageView() {
		if (imageView == null) {
			imageView = (ImageView) baseView.findViewById(R.id.img);
		}
		return imageView;
	}
}
