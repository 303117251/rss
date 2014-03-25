package com.wo.base;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.wo.models.DownloadInfo;
import com.wo.rss.R;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：25 Mar 2014 10:56:24
 * @类说明 用于download activity的adapter
 */
public class DownloadAdapter extends ArrayAdapter<DownloadInfo> {
	private List<DownloadInfo> infos;
	private BitmapUtils utils;
	public DownloadAdapter(Context context, List<DownloadInfo> infos) {
		super(context, 0, infos);
		this.infos = infos;
		utils = new BitmapUtils(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity =  (Activity) getContext();
		DownloadInfo info = infos.get(position);
		if (convertView == null) {
			// 用LayoutInflater的方法将定义好的item.xml文件提取成View实例用来显示。
			LayoutInflater inflater = activity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.download_list, null);
		}
		if(info.getIsAd()) {
			ImageView imgView = (ImageView) convertView.findViewById(R.id.download_limg);
			imgView.setVisibility(View.VISIBLE);
			utils.display(imgView, info.getImgUrl());
			convertView.findViewById(R.id.download_simg).setVisibility(View.GONE);
		}else {
			
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 40);  // , 1是可选写的
			lp.setMargins(0, 0, 0, 25); 
			TextView txtView=(TextView) convertView.findViewById(R.id.download_name);
			txtView.setLayoutParams(lp);
			ImageView imgView = (ImageView) convertView.findViewById(R.id.download_simg);
			utils.display(imgView, info.getImgUrl());
		}
		return convertView;
	}
}
