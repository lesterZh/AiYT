package com.yiqiao.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yiqiao.aiytsite.R;
import com.yiqiao.stockpile.StockUploadActivity;
import com.yiqiao.ui.CustomProgress;
import com.yiqiao.ui.ToastFormatActivity;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyToast;


public class LoginEditActivity extends BaseActivity {
	private EditText loginUser; // 帐号编辑框
	private EditText loginPassword; // 密码编辑框
	private Button loginButton, registButton;
	private ProgressBar pb;
	private TextView forgetPassWordTv;
	private CheckBox rememberBox;
	private Context mContext;
	private CustomProgress progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		// 去除标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去除状态栏
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		
		loginInit();
		passwordProcess();
		registProcess();
		
	}

	
	private void registProcess() {
		registButton = (Button) findViewById(R.id.login_register_btn);
		registButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(LoginEditActivity.this, "注册新用户", 1000).show();
				Intent registerPhoneIntent = new Intent(LoginEditActivity.this, RegisterPhoneActivity.class);
				startActivity(registerPhoneIntent);
			}
		});
	}
	private void passwordProcess() {
		forgetPassWordTv = (TextView) findViewById(com.yiqiao.aiytsite.R.id.login_forget_password);
		forgetPassWordTv.setClickable(true);
		forgetPassWordTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(LoginEditActivity.this, "Forget Password", 1000).show();
				System.out.println(this+" "+LoginEditActivity.this);
			}
		});
		
		rememberBox = (CheckBox) findViewById(R.id.remember_password);
		rememberBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked) {
					Toast.makeText(LoginEditActivity.this, "选中记住密码", 1000).show();
				} else {
					Toast.makeText(LoginEditActivity.this, "取消记住密码", 1000).show();
				}
			}
		});
	}
	private void loginInit() {
		// TODO Auto-generated method stub
		loginUser = (EditText) findViewById(com.yiqiao.aiytsite.R.id.login_edit_username);
		loginPassword = (EditText) findViewById(com.yiqiao.aiytsite.R.id.login_edit_password);
		
		loginUser.setText("zht");
		loginPassword.setText("123");
		
		loginButton = (Button) findViewById(com.yiqiao.aiytsite.R.id.login_btn);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == com.yiqiao.aiytsite.R.id.login_btn) {
					// 判断是否登录成功！
					if ("zht".equals(loginUser.getText().toString())
							&& "123".equals(loginPassword.getText().toString())) // 判断
																					// 帐号和密码
					{
						/*
						 * pb.setVisibility(View.VISIBLE); new Handler().postDelayed(new
						 * Runnable() {
						 * 
						 * @Override public void run() { Intent intent = new
						 * Intent(LoginEditActivity.this,TitleMainActivity.class);
						 * startActivity(intent); } }, 3000); Intent intent = new
						 * Intent(); intent.setClass(LoginEditActivity.this,
						 * LoginWaitActivity.class); startActivity(intent);
						 */

//						Intent intent = new Intent();
//						intent.setClass(LoginEditActivity.this, LoginWaitActivity.class);
//						startActivity(intent);
						
						progressDialog = CustomProgress.show(mContext, "登录中……", false, null);
						
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								
								Intent intent = new Intent(LoginEditActivity.this,
										TitleMainActivity.class);
								startActivity(intent);
								LoginEditActivity.this.finish();
								progressDialog.dismiss();
								MyToast.toast(mContext, "登录成功", 1);
								
								MyConstants.currentUserName = loginUser.getText().toString();
								MyConstants.currentUserPassword = loginPassword.getText().toString();
								
//								ToastFormatActivity.makeText(LoginEditActivity.this,
//										R.drawable.toast_icon, "登录成功", Toast.LENGTH_SHORT)
//										.show();
							}
						}, 1000);

					} else if ("".equals(loginUser.getText().toString())
							|| "".equals(loginPassword.getText().toString())) // 判断
																				// 帐号和密码
					{
						new AlertDialog.Builder(LoginEditActivity.this)
								.setIcon(
										getResources().getDrawable(
												com.yiqiao.aiytsite.R.drawable.login_error_icon))
								.setTitle("登录错误").setMessage("帐号或者密码不能为空，\n请输入后再登录！")
								.setPositiveButton(com.yiqiao.aiytsite.R.string.login_alert_dialog_ok, new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										
									}
								})
								.create().show();
					} else {

						new AlertDialog.Builder(LoginEditActivity.this)
								.setIcon(
										getResources().getDrawable(
												com.yiqiao.aiytsite.R.drawable.login_error_icon))
								.setTitle("登录失败").setMessage("帐号或者密码不正确，\n请检查后重新输入！")
								.setPositiveButton(com.yiqiao.aiytsite.R.string.login_alert_dialog_ok, new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										
									}
								})
								.create().show();
					}
				}
			}
		});
		
	}


}
