package com.wo.service;

/**
 * @author 作者 mengyangwang E-mail:wangmengyang101@gmail.com
 * @version 创建时间：23 Mar 2014 22:36:30
 * @类说明 给waterfallacitivty提供一些额外服务
 */
public class WaterfallService {
	int[] heights = new int[] { 0, 0, 0 };

	/**
	 * 添加图片高度
	 */
	public void addHeight(int number, int height) {
		heights[number] = heights[number] + height;
	}

	/**
	 * 得到三个队列最短的一个
	 */
	public int getMin() {
		int a = heights[0];
		int b = heights[1];
		int c = heights[2];
		if (a <= b) {
			if (a <= c) {
				return 0;
			} else {
				return 2;
			}
		} else {
			if (b <= c) {
				return 1;
			} else {
				return 2;
			}
		}

	}
}
