package com.yiqiao.stockpile;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yiqiao.aiytsite.R;
import com.yiqiao.maintitle.StockpileManagementActivity;
import com.yiqiao.ui.CustomProgress;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyToast;

public class StockControlActivity extends Activity {
	private LinearLayout classifyRou, classifyQin, classifyDan, classifyShuCai; 
	private LinearLayout classifyShuiGuo, classifyLiangYou, classifyJiuCha;
	private Context mContext;
	private ProgressDialog mProgressDialog;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_control);
		mContext = this;
		
		initView();
		
	}
	
	private void initView() {
		classifyRou = (LinearLayout) findViewById(R.id.stock_classify_rou);
		classifyQin = (LinearLayout) findViewById(R.id.stock_classify_qin);
		classifyDan = (LinearLayout) findViewById(R.id.stock_classify_dan);
		classifyShuCai = (LinearLayout) findViewById(R.id.stock_classify_shucai);
		classifyShuiGuo = (LinearLayout) findViewById(R.id.stock_classify_shuiguo);
		classifyLiangYou = (LinearLayout) findViewById(R.id.stock_classify_liangyou);
		classifyJiuCha = (LinearLayout) findViewById(R.id.stock_classify_jiucha);
		
		MyClassifyListener classifyListener = new MyClassifyListener();
		classifyRou.setOnClickListener(classifyListener);
		classifyQin.setOnClickListener(classifyListener);
		classifyDan.setOnClickListener(classifyListener);
		classifyShuCai.setOnClickListener(classifyListener);
		classifyShuiGuo.setOnClickListener(classifyListener);
		classifyLiangYou.setOnClickListener(classifyListener);
		classifyJiuCha.setOnClickListener(classifyListener);
		
	}
	
	class MyClassifyListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.stock_classify_rou:
//				MyToast.toast(mContext, "rou", 1);
				
				Intent intentRou = new Intent(mContext, StockClassifyLevel1Simple.class);
				intentRou.putExtra("from_where", MyConstants.FROM_ROU);
				mContext.startActivity(intentRou);
				break;
				
			case R.id.stock_classify_qin:
//				MyToast.toast(mContext, "qin", 1);
				Intent intentQin = new Intent(mContext, StockClassifyLevel1Simple.class);
				intentQin.putExtra("from_where", MyConstants.FROM_QIN);
				mContext.startActivity(intentQin);
				break;
			case R.id.stock_classify_dan:
//				MyToast.toast(mContext, "dan", 1);
				Intent intentDan = new Intent(mContext, StockClassifyLevel1Simple.class);
				intentDan.putExtra("from_where", MyConstants.FROM_DAN);
				mContext.startActivity(intentDan);
				break;
	
			case R.id.stock_classify_shucai:
//				MyToast.toast(mContext, "shucai", 1);
				Intent intent4 = new Intent(mContext, StockClassifyLevel1Simple.class);
				intent4.putExtra("from_where", MyConstants.FROM_SHUCAI);
				mContext.startActivity(intent4);
				break;
	
			case R.id.stock_classify_shuiguo:
//				MyToast.toast(mContext, "shuiguo", 1);
				Intent intent5 = new Intent(mContext, StockClassifyLevel1Simple.class);
				intent5.putExtra("from_where", MyConstants.FROM_SHUIGUO);
				mContext.startActivity(intent5);
				break;
	
			case R.id.stock_classify_liangyou:
//				MyToast.toast(mContext, "liangyou", 1);
				Intent intent6 = new Intent(mContext, StockClassifyLevel1Simple.class);
				intent6.putExtra("from_where", MyConstants.FROM_LIANGYOU);
				mContext.startActivity(intent6);
				break;
	
			case R.id.stock_classify_jiucha:
//				MyToast.toast(mContext, "jiucha", 1);
				Intent intent7 = new Intent(mContext, StockClassifyLevel1Simple.class);
				intent7.putExtra("from_where", MyConstants.FROM_QITA);
				mContext.startActivity(intent7);
				break;
	
			default:
				break;
			}
		}
	}
}