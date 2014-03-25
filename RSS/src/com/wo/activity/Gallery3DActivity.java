package com.wo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wo.base.CustomGallery;
import com.wo.base.ImageAdapter;
import com.wo.rss.R;

public class Gallery3DActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_activity);

		Integer[] images = { R.drawable.img0001, R.drawable.img0030,
				R.drawable.img0100, R.drawable.img0130, R.drawable.img0200,
				R.drawable.img0230, R.drawable.img0300, R.drawable.img0330,
				R.drawable.img0354 };

		ImageAdapter adapter = new ImageAdapter(this, images);
		adapter.createReflectedImages();

		CustomGallery galleryFlow = (CustomGallery) findViewById(R.id.gallery);
		galleryFlow.setAdapter(adapter);

	}
}