package com.yiqiao.person.info;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yiqiao.aiytsite.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class PersonInfoAddressManageActivity extends FinalActivity{
	@ViewInject(id=R.id.person_info_address_back_btn) private Button backButton;
	@ViewInject(id=R.id.person_info_address_old_textView)private TextView oldAddress;
	@ViewInject(id=R.id.person_info_address_new_edittext)private EditText newAddress;
	@ViewInject(id=R.id.person_info_address_submit_btn)private Button submitButton;
	
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_info_address_manage);
		mContext = this;
		
		backButton.setOnClickListener(new MyOnClickListener());
		submitButton.setOnClickListener(new MyOnClickListener());
	}
	
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.person_info_address_back_btn:
				back();
				break;
			case R.id.person_info_address_submit_btn://提交更改新地址
				if (newAddress.getText().toString().equals("")) {
					new AlertDialog.Builder(mContext)
					.setTitle("提示")
					.setMessage("请输入有效地址！")
					.setPositiveButton("确定", null)
					.create().show();
				} else {
					String addr = newAddress.getText().toString();
					oldAddress.setText(addr);
					
					//上传服务器更新
				}
				break;

			default:
				break;
			}
		}
		
	}
	//返回键功能
	private void back() {
		super.onBackPressed();
	}
}
