package com.wo.service;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：18 Mar 2014 10:13:59 
 * @类说明 用于异步加载图片
 */
public class AsynImgLoader {
	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsynImgLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	// 如果cache里有加载Drawable
	public Drawable loadDrawable(final String imageUrl,
			final ImageCallback imageCallback) {
		// 如果cache里有有从cache里获取 没有就加进去 并放到cache里
		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			if (drawable != null) {
				return drawable;
			}
		}
		 final Handler handler = new Handler() {
			//回调加载图片
			public void handleMessage(Message msg) {
				switch(msg.what) 
				{
				case 0:
				imageCallback.imageLoaded((Drawable) msg.obj, imageUrl);
				default:break;
				};
			}
		};
		new Thread() {
			@Override
			public void run() {
				Drawable drawable = Downloader.loadImageFromUrl(imageUrl);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}.start();
		return null;
	}

	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}
}
