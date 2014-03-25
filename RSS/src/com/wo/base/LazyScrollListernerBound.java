package com.wo.base;

//来自：http://blog.csdn.net/listening_music/article/details/7192629
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;

/**
 * <p>
 * Title: LazyScrollView.java
 * </p>
 * <p>
 * Description: 用于检测是否到底部
 * </p>
 * 
 * @date 22 Mar 2014
 * @version 1.0
 */
public class LazyScrollListernerBound {

	ScrollView scrollView;
	Handler handler;
	private OnScrollListener onScrollListener;

	public LazyScrollListernerBound(OnScrollListener onScrollListener,
			ScrollView view) {
		this.scrollView = view;
		this.onScrollListener = onScrollListener;
		init();
	}

	private void init() {
		scrollView.setOnTouchListener(onTouchListener);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// process incoming messages here
				super.handleMessage(msg);
				View v = (View) msg.obj;
				switch (msg.what) {
				case 1:
					if (scrollView.getChildAt(0).getMeasuredHeight() <= v
							.getScrollY() + v.getHeight()) {
						if (onScrollListener != null) {
							onScrollListener.onBottom();
						}

					} else if (v.getScrollY() == 0) {
						if (onScrollListener != null) {
							onScrollListener.onTop();
						}
					} else {
						if (onScrollListener != null) {
							onScrollListener.onScroll();
						}
					}
					break;
				default:
					break;
				}
			}
		};

	}

	OnTouchListener onTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_UP:
				if (scrollView != null && onScrollListener != null) {
					Message message = handler.obtainMessage(1, v);
					handler.sendMessageDelayed(message, 200);
				}
				break;

			default:
				break;
			}
			return false;
		}

	};

	public interface OnScrollListener {
		void onBottom();

		void onTop();

		void onScroll();
	}
}