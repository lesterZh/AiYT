package com.yiqiao.person.info;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yiqiao.aiytsite.R;

public class PersonInfoMessageCenterActivity extends FinalActivity {
	@ViewInject(id=R.id.person_info_message_center_back_btn) private Button backButton;
	private Context mContext;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_info_message_center);
		mContext = this;
		
		backButton.setOnClickListener(new MyOnClickListener());
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.person_info_message_center_back_btn:
				back();
				break;

			default:
				break;
			}
		}
		
	}
	//·µ»Ø¼ü¹¦ÄÜ
	private void back() {
		super.onBackPressed();
	}

}
