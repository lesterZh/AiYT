package com.yiqiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.yiqiao.aiytsite.R;
import com.yiqiao.util.MyToast;

public class RegisterSucceed extends BaseActivity{
	private Context mContext;
	private Button goHomeButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_register_succeed);
		
		mContext = this;
		goHomeButton = (Button) findViewById(R.id.btn_register_succeed);
		
		goHomeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				MyToast.toast(mContext, "go home", 1);
				Intent intent = new Intent(mContext, TitleMainActivity.class);
				startActivity(intent);
			}
		});
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, TitleMainActivity.class);
				startActivity(intent);
			}
		}, 2000);
	}
}
