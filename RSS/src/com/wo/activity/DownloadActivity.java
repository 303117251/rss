package com.wo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.wo.base.DownloadAdapter;
import com.wo.models.DownloadInfo;
import com.wo.rss.R;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：25 Mar 2014 10:48:46
 * @类说明 下载页面类
 */
public class DownloadActivity extends Activity {
	ListView list;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_activity);
		list = (ListView) findViewById(R.id.download_list);
		init();
	}
	public void init() {
		List<DownloadInfo> infos = new ArrayList<DownloadInfo>();
		
		infos.add(new DownloadInfo("http://image.sjpic.91rb.com/image.91rb.com/Wallpaper/2010/9/15/31606e1985804e3f9106bb6fe9e059b2-25.jpg","asd","asd",false));
		infos.add(new DownloadInfo("http://image.sjpic.91rb.com/image.91rb.com/Wallpaper/2010/9/15/31606e1985804e3f9106bb6fe9e059b2-25.jpg","asd","asd",true));
		infos.add(new DownloadInfo("http://image.sjpic.91rb.com/image.91rb.com/Wallpaper/2010/9/15/31606e1985804e3f9106bb6fe9e059b2-25.jpg","asd","asd",false));
		 DownloadAdapter adapter = new DownloadAdapter(this,
				infos);
		list.setAdapter(adapter);
	}
}
