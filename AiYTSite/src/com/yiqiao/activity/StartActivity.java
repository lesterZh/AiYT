package com.yiqiao.activity;

import com.yiqiao.aiytsite.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class StartActivity extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//去除标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//去除状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.start_layout);
//		setContentView(R.layout.order_detail_info);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StartActivity.this, LoginEditActivity.class);
				startActivity(intent);
				StartActivity.this.finish();
			}
		}, 1500);
	}
	
}
