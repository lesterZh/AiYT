package com.yiqiao.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yiqiao.aiytsite.R;
import com.yiqiao.util.MyToast;

public class RegisterPhoneActivity extends BaseActivity{
	private EditText registerPhoneNum,registerVerificationCode;
	private Button getVerifCode,continueInputInfo;
	private Context mContext;
	private boolean verificationIsOK = true; //是否完成手机验证，默认为false
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_register_phone_verify);
		
		mContext = this;
		
		registerPhoneNum = (EditText) findViewById(R.id.et_register_input_phoneNumber);
		registerVerificationCode = (EditText) findViewById(R.id.et_register_inputVerificationCode);
		getVerifCode = (Button) findViewById(R.id.btn_register_getVerificationCode);
		continueInputInfo = (Button) findViewById(R.id.btn_register_continueInputInfo);
		
		getVerifCode.setOnClickListener(new MyListener());
		continueInputInfo.setOnClickListener(new MyListener());
		
	}
	
	private boolean isPhone(String inputText) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }
	
	class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_register_getVerificationCode:
				String phoneNum = registerPhoneNum.getText().toString();
				if (isPhone(phoneNum)) {
					new AlertDialog.Builder(mContext)
					.setIcon(
							getResources().getDrawable(
									com.yiqiao.aiytsite.R.drawable.login_error_icon))
					.setTitle("提示").setMessage("请输入正确的手机号码！")
					.setPositiveButton(com.yiqiao.aiytsite.R.string.login_alert_dialog_ok, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					})
					.create().show();
				} else if (phoneNum.length() == 11) {
					MyToast.toast(mContext, "验证码已发送，请注意接收",2);
				}
				break;

			case R.id.btn_register_continueInputInfo:
				
				String verificationCode = registerVerificationCode.getText().toString();
				//此处加入验证码判断逻辑
				
				if (verificationIsOK) {
					
					Intent registerIntent = new Intent(mContext, RegisterActivity.class);
					startActivity(registerIntent);
					
				} else {
					new AlertDialog.Builder(mContext)
					.setIcon(
							getResources().getDrawable(
									com.yiqiao.aiytsite.R.drawable.login_error_icon))
					.setTitle("提示").setMessage("请进行手机号码验证！")
					.setPositiveButton(com.yiqiao.aiytsite.R.string.login_alert_dialog_ok, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					})
					.create().show();
				}
				
				
				break;
			default:
				break;
			}
		}
		
	}
}
