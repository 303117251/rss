
package com.wo.base;

import android.os.Handler;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：19 Mar 2014 16:00:38
 * @类说明 用于ListActivity的监听下滑条事件 下滑加载图片
 */
public class CustomScrollListener implements OnScrollListener {
	private int visibleLastIndex = 0; // 最后的可视项索引+1
	private int totalItemCount=0;
	ImgAndTxtArrayAdapter adapter;

	public CustomScrollListener(ImgAndTxtArrayAdapter adapter) {
		this.adapter = adapter;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		//第一个可视控件位置+能看到的控件个数=已浏览的控件个数
		visibleLastIndex = firstVisibleItem + visibleItemCount ;
		this.totalItemCount=  totalItemCount;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& visibleLastIndex == (totalItemCount)) {
			if (adapter.updateFlag()) {
				adapter.notifyDataSetChanged();
			}
		}else if(scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
		}
		else if(scrollState==OnScrollListener.SCROLL_STATE_FLING) {
		}else if(scrollState==OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
		}
	}
}
