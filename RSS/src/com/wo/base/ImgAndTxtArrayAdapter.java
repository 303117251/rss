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
import com.wo.models.ViewCache;
import com.wo.rss.R;
import com.wo.service.AsynImgLoader;
import com.wo.service.AsynImgLoader.ImageCallback;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：18 Mar 2014 10:11:54 
 * @类说明 继承ArrayAdpator用于加载和更新img和text，加载数根据flag来确定（flag会根据scroll listener来确定）
 */
public class ImgAndTxtArrayAdapter extends ArrayAdapter<Brief> {
	private  ListView listView;
	private AsynImgLoader asynImgLoader;
	private List<Brief> brief;
	private int flag =1;
	public ImgAndTxtArrayAdapter(Activity activity, List<Brief> brief,
			ListView listView) {
		super(activity, 0, brief);
		this.listView = listView;
		this.brief = brief;
		asynImgLoader = new AsynImgLoader();
	}
	//根据adapter里面的count（来自于arraylist传的数据）数目来确定调用次数
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		View rowView = convertView;
		ViewCache viewCache;
		if (rowView == null) {
			//用LayoutInflater的方法将定义好的item.xml文件提取成View实例用来显示。
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.main_list, null);
			viewCache = new ViewCache(rowView);
			rowView.setTag(viewCache);
		} else {
			viewCache = (ViewCache) rowView.getTag();
		}
		Brief brief = getItem(position);
		// Load the image and set it on the ImageView  
        String imageUrl = brief.getImg();  
        ImageView imageView = viewCache.getImageView();  
        imageView.setTag(imageUrl);  
        //callback
        Drawable cachedImage = asynImgLoader.loadDrawable(imageUrl, new ImageCallback() {  
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {  
            	//回调更新UI
                ImageView imageViewByTag = (ImageView) listView.findViewWithTag(imageUrl);  
                if (imageViewByTag != null) {  
                    imageViewByTag.setImageDrawable(imageDrawable);  
                }  
            }  
        });  
        if (cachedImage == null) {  
            imageView.setImageResource(R.drawable.none_img);  
        }else{  
            imageView.setImageDrawable(cachedImage);  
        }  
        // Set the text on the TextView  
        TextView textView = viewCache.getTextView();  
        textView.setText(brief.getContent()); 
		return rowView;
	}
	
	public int getCount() {
		if (brief ==null) {
			return 0;
		}
		else if(brief.size()>10) {
			return 10*flag;
			
		}else {
			return (flag-1)*10+brief.size();
		}
	}
	public boolean updateFlag() {
		if(brief.size()>getCount()) {
			flag++;
			return true;
		}else
			return false;
	}
}
