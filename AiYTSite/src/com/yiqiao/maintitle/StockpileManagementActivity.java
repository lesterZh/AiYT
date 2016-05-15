package com.yiqiao.maintitle;

import com.yiqiao.stockpile.StockControlActivity;


import com.yiqiao.stockpile.StockUploadActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.yiqiao.activity.MyApplication;
import com.yiqiao.aiytsite.R;

@SuppressWarnings({ "deprecation", "deprecation", "deprecation" })
public class StockpileManagementActivity extends TabActivity  {
	
	private static final String TAB_STOCK_SCAN = "STOCK_SCAN_ACTIVITY";
	private static final String TAB_STOCK_UPLOAD = "STOCK_UPLOAD_ACTIVITY";
	private RelativeLayout scanLayout,uploadLayout;
	private TextView scanTv, uploadTv;
	private TabHost stockTabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_stockpile_page);
		
		initStockile();
	}
	
	private void initStockile() {
		
		scanTv = (TextView) findViewById(R.id.tv_stock_scan);
		uploadTv = (TextView) findViewById(R.id.tv_stock_upload);
		
		scanLayout = (RelativeLayout) findViewById(R.id.tab_stock_scan);
		uploadLayout = (RelativeLayout) findViewById(R.id.tab_stock_upload);
		
		MyListener listener = new MyListener();
		scanLayout.setOnClickListener(listener);
		uploadLayout.setOnClickListener(listener);
		
		
		stockTabHost = getTabHost();
		
		Intent scanIntent = new Intent(this, StockControlActivity.class);
		Intent uploadIntent = new Intent(this, StockUploadActivity.class);
		
		stockTabHost.addTab(stockTabHost.newTabSpec(TAB_STOCK_SCAN)
				.setIndicator(TAB_STOCK_SCAN).setContent(scanIntent));
		stockTabHost.addTab(stockTabHost.newTabSpec(TAB_STOCK_UPLOAD)
				.setIndicator(TAB_STOCK_UPLOAD).setContent(uploadIntent));
		
		stockTabHost.setCurrentTabByTag(TAB_STOCK_SCAN);
		scanTv.setSelected(true);
	}
	
	class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.tab_stock_scan:
				stockTabHost.setCurrentTabByTag(TAB_STOCK_SCAN);
				scanTv.setSelected(true);
				uploadTv.setSelected(false);
				
				break;
				
			case R.id.tab_stock_upload:
				stockTabHost.setCurrentTabByTag(TAB_STOCK_UPLOAD);
				scanTv.setSelected(false);
				uploadTv.setSelected(true);
				
				break;

			default:
				break;
			}
		}
		
	}
}


