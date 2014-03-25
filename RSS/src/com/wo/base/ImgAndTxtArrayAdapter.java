package com.wo.base;

import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wo.models.Brief;
import com.wo.rss.R;
import com.wo.service.AsynImgLoader;
import com.wo.service.AsynImgLoader.ImageCallback;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：18 Mar 2014 10:11:54
 * @类说明 继承ArrayAdpator用于加载text和更新img（lock来决定是否更新） 加载数个数根据flag来确定（flag会根据scroll
 *      listener来确定）
 */
public class ImgAndTxtArrayAdapter extends ArrayAdapter<Brief> {
	private ListView listView;
	private AsynImgLoader asynImgLoader;
	private List<Brief> brief;
	private int flag = 1;

	public ImgAndTxtArrayAdapter(Activity activity, List<Brief> brief,
			ListView listView) {
		super(activity, 0, brief);
		this.listView = listView;
		this.brief = brief;
		asynImgLoader = new AsynImgLoader();
	}
	/**
	 * 根据count数目来决定调用次数，来更新listview控件
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		if (convertView == null) {
			// 用LayoutInflater的方法将定义好的item.xml文件提取成View实例用来显示。
			LayoutInflater inflater = activity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.rss_list, null);
		}
		Brief brief = getItem(position);
		convertView.setTag(position);
		TextView contentView = (TextView) convertView.findViewById(R.id.info);
		contentView.setText(brief.getContent());
		String imageUrl = brief.getImg();
		ImageView imageView = (ImageView) convertView.findViewById(R.id.img);
		if (imageUrl != null) {
			imageView.setTag(imageUrl);
			// callback
			Drawable img = asynImgLoader.loadDrawable(imageUrl,
					new ImageCallback() {
						public void imageLoaded(Drawable img, String imageUrl) {
							// 通过img url找到 相应img控件 并更新图片
							ImageView imageViewByTag = (ImageView) listView
									.findViewWithTag(imageUrl);
							if (imageViewByTag != null) {
								imageViewByTag.setImageDrawable(img);
							}
						}
					});
			if (img != null) {
				imageView.setImageDrawable(img);
			}
		} else {
			imageView.setImageResource(R.drawable.none_img);
		}
		return convertView;
	}

	/**
	 * 获取当前存储的brief数目，由flag来决定
	 */
	public int getCount() {
		if (brief == null) {
			return 0;
		} else if (brief.size() > 10) {
			return 10 * flag;

		} else {
			return (flag - 1) * 10 + brief.size();
		}
	}
	/**
	 * 更新flag
	 * @param
	 * @return
	 */
	public boolean updateFlag() {
		if (brief.size() > getCount()) {
			flag++;
			return true;
		} else
			return false;
	}

}
