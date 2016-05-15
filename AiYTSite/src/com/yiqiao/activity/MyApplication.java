package com.yiqiao.activity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
	private static Context mContext;

	@Override
	public void onCreate() {
		
		mContext = getApplicationContext();
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(mContext);
		ImageLoader.getInstance().init(configuration);
		
	}
	
	public static Context getContext() {
		return mContext;
	}
	
}
