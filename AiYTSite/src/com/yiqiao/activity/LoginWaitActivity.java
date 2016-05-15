package com.yiqiao.activity;

import com.yiqiao.aiytsite.R;
import com.yiqiao.ui.ToastFormatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;


public class LoginWaitActivity extends BaseActivity{
	private ProgressBar logWatPB;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_waiting);
		logWatPB=(ProgressBar) findViewById(R.id.progressBar1);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				
				Intent intent = new Intent(LoginWaitActivity.this,
						TitleMainActivity.class);
				startActivity(intent);
				LoginWaitActivity.this.finish();
				ToastFormatActivity.makeText(LoginWaitActivity.this,
						R.drawable.toast_icon, "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT)
						.show();
			}
		}, 200);
	}
}
