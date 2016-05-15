package com.yiqiao.maintitle;
import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import com.yiqiao.activity.LoginEditActivity;
import com.yiqiao.activity.MyApplication;
import com.yiqiao.activity.TitleMainActivity;
import com.yiqiao.aiytsite.*;
import com.yiqiao.person.info.PersonInfoAddressManageActivity;
import com.yiqiao.person.info.PersonInfoAdviseActivity;
import com.yiqiao.person.info.PersonInfoMessageCenterActivity;
import com.yiqiao.person.info.PersonInfoModifyPassowrd;
import com.yiqiao.person.info.PersonInfoUserDetailActivity;
import com.yiqiao.stockpile.StockUploadActivity;
import com.yiqiao.ui.CustomProgress;
import com.yiqiao.util.MyToast;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class InfoCenterActivity extends FinalActivity {
	private ImageButton imgBt;
	@ViewInject(id=R.id.view_info_center_page_user_info_detail) private RelativeLayout userInfoArrowButton;
	@ViewInject(id=R.id.view_info_center_page_address_mamager_detail) private RelativeLayout addressArrowButton;
	@ViewInject(id=R.id.view_info_center_page_change_password_detail) private RelativeLayout passwordArrowButton;
	@ViewInject(id=R.id.view_info_center_page_message_center_detail) private RelativeLayout messageArrowButton;
	@ViewInject(id=R.id.view_info_center_page_help_center_detail) private RelativeLayout helpArrowButton;
	@ViewInject(id=R.id.view_info_center_page_switch_account_detail) private RelativeLayout accountArrowButton;
	@ViewInject(id=R.id.view_info_center_page_advise_commit_detail) private RelativeLayout adviseArrowButton;
	
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_info_center_page);
		mContext = this;
		
		MyOnClickListener myOnClickListener = new MyOnClickListener();
		userInfoArrowButton.setOnClickListener(myOnClickListener);
		addressArrowButton.setOnClickListener(myOnClickListener);
		passwordArrowButton.setOnClickListener(myOnClickListener);
		messageArrowButton.setOnClickListener(myOnClickListener);
		helpArrowButton.setOnClickListener(myOnClickListener);
		accountArrowButton.setOnClickListener(myOnClickListener);
		adviseArrowButton.setOnClickListener(myOnClickListener);
		
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.view_info_center_page_user_info_detail:
//				MyToast.toast(mContext, "user", 0);
				Intent intent = new Intent(mContext, PersonInfoUserDetailActivity.class);
				mContext.startActivity(intent);
				break;
			case R.id.view_info_center_page_address_mamager_detail:
				Intent intent2 = new Intent(mContext, PersonInfoAddressManageActivity.class);
				mContext.startActivity(intent2);
				break;
			case R.id.view_info_center_page_change_password_detail:
				Intent intent3 = new Intent(mContext, PersonInfoModifyPassowrd.class);
				mContext.startActivity(intent3);
				break;
			case R.id.view_info_center_page_message_center_detail:
				Intent intent4 = new Intent(mContext, PersonInfoMessageCenterActivity.class);
				mContext.startActivity(intent4);
				break;
			case R.id.view_info_center_page_help_center_detail:
				
				break;
			case R.id.view_info_center_page_switch_account_detail:
				new AlertDialog.Builder(InfoCenterActivity.this)
				.setTitle("«–ªª’À∫≈").setMessage(" «∑Ò«–ªª’À∫≈£ø")
				.setNegativeButton("»∑∂®", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(mContext, LoginEditActivity.class);
						mContext.startActivity(intent);
						InfoCenterActivity.this.finish();
					}
				})
				.setPositiveButton("»°œ˚", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
				.create().show();
				break;
				
			case R.id.view_info_center_page_advise_commit_detail:
				Intent intent7 = new Intent(mContext, PersonInfoAdviseActivity.class);
				mContext.startActivity(intent7);
				break;

			default:
				break;
			}
		}
		
	}
}