package com.yiqiao.person.info;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yiqiao.aiytsite.R;
import com.yiqiao.util.MyConstants;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class PersonInfoModifyPassowrd extends FinalActivity{
	@ViewInject(id=R.id.person_info_modify_password_back_btn) private Button backButton;
	@ViewInject(id=R.id.person_info_modify_password_submit_btn) private Button submitButton;
	@ViewInject(id=R.id.person_info_modify_password_edit_pw_old)private EditText oldEditText;
	@ViewInject(id=R.id.person_info_modify_password_edit_pw_new)private EditText newEditText;
	@ViewInject(id=R.id.person_info_modify_password_edit_pw_ack)private EditText newAgainEditText;
	
	private Context mContext;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_info_modify_password);
		mContext = this;
		
		backButton.setOnClickListener(new MyOnClickListener());
		submitButton.setOnClickListener(new MyOnClickListener());
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.person_info_modify_password_back_btn:
				back();
				break;
			case R.id.person_info_modify_password_submit_btn:
				String oldPassword = oldEditText.getText().toString();
				String newPassword = newEditText.getText().toString();
				String newAgainPassword = newAgainEditText.getText().toString();
				
				if (!oldPassword.equals(MyConstants.currentUserPassword)) {
					new AlertDialog.Builder(mContext)
					.setTitle("提示")
					.setMessage("原密码输入错误！")
					.setPositiveButton("确定", null)
					.create().show();
				} else if (!newPassword.equals(newAgainPassword)) {
					new AlertDialog.Builder(mContext)
					.setTitle("提示")
					.setMessage("2次密码输入不一致！")
					.setPositiveButton("确定", null)
					.create().show();
				} else if (newPassword.length() < 8) {
					new AlertDialog.Builder(mContext)
					.setTitle("提示")
					.setMessage("密码长度不能小于8位！")
					.setPositiveButton("确定", null)
					.create().show();
				} else {
					//上传到服务器
					
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
