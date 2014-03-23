package com.wo.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.wo.base.CustomScrollListener;
import com.wo.base.ImgAndTxtArrayAdapter;
import com.wo.base.UpdateDataHandler;
import com.wo.data.manager.RSSActivityManager;
import com.wo.models.Brief;
import com.wo.rss.R;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：17 Mar 2014 12:00:38
 * @类说明 ListActivity用于显示主页上的rss简要（每一条是一个listview 由图片和text构成）
 */

public class RSSActivity extends Activity  {
	ListView list = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.list_activity);
		list = (ListView) findViewById(R.id.listView);
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		List<Brief> dataArray = new ArrayList<Brief>();
		final ImgAndTxtArrayAdapter adapter = new ImgAndTxtArrayAdapter(this,
				dataArray, list);
		final UpdateDataHandler handler = new UpdateDataHandler(adapter);
		new Thread() {
			@Override
			public void run() {
				RSSActivityManager manager = new RSSActivityManager();
				Message message = null;
				try {
					message = handler.obtainMessage(0, manager.getData());
				} catch (SAXException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (ParserConfigurationException ex) {
					ex.printStackTrace();
				}
				handler.sendMessage(message);
			}
		}.start();
		list.setAdapter(adapter);
		list.setOnScrollListener(new CustomScrollListener(adapter));
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				Uri content_url = Uri.parse(adapter.getItem(position).getLink());
				intent.setData(content_url);
				startActivity(intent);
			}
		});
	}

}
