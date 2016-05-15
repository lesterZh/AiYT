package com.yiqiao.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiao.aiytsite.*;
import com.yiqiao.maintitle.HomePageActivity;
import com.yiqiao.maintitle.InfoCenterActivity;
import com.yiqiao.maintitle.OrderAndLogisticsActivity;
import com.yiqiao.maintitle.StockpileManagementActivity;

public class TitleMainActivity extends FragmentActivity {
	public static ActionBar mainTitleActionBar = null;
	public static TitleMainActivity instance = null;
	private ViewPager mTabPager;
	private ImageView mTabHomePage,mTabStManage,mTabOrderLogs,mTabInfoCenter;
	private LinearLayout mLayoutHomeBar,mLayoutStockpile,mLayoutOrder,mLayoutInfoCenter;
	private int currIndex =0;
	private View mLayout;
	private LayoutInflater mLnflater;
	private	TextView mTvHomePage,mTvStManage,mTvOrderLogs,mTvInfoCenter;
	Intent intentHomepage,intentStManage,intentOrderLogs,intentInfoCenter;
	Context context =null;
	@SuppressWarnings("deprecation")
	LocalActivityManager manager =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title_main);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		instance = this;
		context = TitleMainActivity.this;
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		
		mainTitleActionBar = getActionBar();
		
		variableInit();
		viewInit();
	}

	private void variableInit() {
		// TODO Auto-generated method stub
        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
        
        mTvHomePage= (TextView)findViewById(R.id.tv_home_page);
        mTvStManage= (TextView)findViewById(R.id.tv_stockpile_management);
        mTvOrderLogs= (TextView)findViewById(R.id.tv_order_logistics);
        mTvInfoCenter= (TextView)findViewById(R.id.tv_info_center);
        
        mTabHomePage = (ImageView) findViewById(R.id.img_home_page);
        mTabStManage = (ImageView) findViewById(R.id.img_stockpile_management);
        mTabOrderLogs = (ImageView) findViewById(R.id.img_order_logistics);
        mTabInfoCenter = (ImageView) findViewById(R.id.img_info_center);
        
        mTabHomePage.setOnClickListener(new MyOnClickListener(0));
        mTabStManage.setOnClickListener(new MyOnClickListener(1));
        mTabOrderLogs.setOnClickListener(new MyOnClickListener(2));
        mTabInfoCenter.setOnClickListener(new MyOnClickListener(3));
        
        mLayoutHomeBar = (LinearLayout) findViewById(R.id.navigate_bar_home);
        mLayoutStockpile = (LinearLayout) findViewById(R.id.navigate_bar_stockpile_management);
        mLayoutOrder = (LinearLayout) findViewById(R.id.navigate_bar_order_logistics);
        mLayoutInfoCenter = (LinearLayout) findViewById(R.id.navigate_bar_info_center);
       
        mLayoutHomeBar.setOnClickListener(new MyOnClickListener(0));
        mLayoutStockpile.setOnClickListener(new MyOnClickListener(1));
        mLayoutOrder.setOnClickListener(new MyOnClickListener(2));
        mLayoutInfoCenter.setOnClickListener(new MyOnClickListener(3));
        
        
        
        if(currIndex==0){
			mTvHomePage.setSelected(true);
		}
	}
	
	private class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};
	
	private void viewInit() {
		
		final ArrayList<View> lists = new ArrayList<View>();
		 
		 intentHomepage=new Intent(context,HomePageActivity.class);
		 intentStManage = new Intent(context,StockpileManagementActivity.class);
		 intentOrderLogs = new Intent(context,OrderAndLogisticsActivity.class);
		 intentInfoCenter = new Intent(context,InfoCenterActivity.class);
		
		 lists.add(getView("HomePageActivity",intentHomepage));
		 lists.add(getView("StockpileManagementActivity",intentStManage));
		 lists.add(getView("OrderAndLogisticsActivity",intentOrderLogs));
		 lists.add(getView("InfoCenterActivity",intentInfoCenter));
		 
		//Ìî³äViewPagerµÄÊý¾ÝÊÊÅäÆ÷
	        PagerAdapter mPagerAdapter = new PagerAdapter() {
				@Override
				public boolean isViewFromObject(View arg0, Object arg1) {
					return arg0 == arg1;
				}
				@Override
				public int getCount() {
					return lists.size();
				}
				@Override
				public void destroyItem(View container, int position, Object object) {
					((ViewPager)container).removeView(lists.get(position));
				}
				@Override
				public Object instantiateItem(View container, int position) {
					((ViewPager)container).addView(lists.get(position));
					return lists.get(position);
				}
			};
			

		mTabPager.setAdapter(mPagerAdapter);
	}

	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				mTabHomePage.setImageDrawable(getResources().getDrawable(R.drawable.nav_home_hover));
				mTvHomePage.setSelected(true);
				if (currIndex == 1) {
					mTabStManage.setImageDrawable(getResources().getDrawable(R.drawable.nav_stockpile));
					mTvStManage.setSelected(false);
				} else if (currIndex == 2) {
					mTabOrderLogs.setImageDrawable(getResources().getDrawable(R.drawable.nav_stockpile));
					mTvOrderLogs.setSelected(false);
				}
				else if (currIndex == 3) {
					mTabInfoCenter.setImageDrawable(getResources().getDrawable(R.drawable.nav_info_center));
					mTvInfoCenter.setSelected(false);
				}
				break;
			case 1:
				mTabStManage.setImageDrawable(getResources().getDrawable(R.drawable.nav_stockpile_hover));
				mTvStManage.setSelected(true);
				if (currIndex == 0) {
					mTabHomePage.setImageDrawable(getResources().getDrawable(R.drawable.nav_home));
					mTvHomePage.setSelected(false);
				} else if (currIndex == 2) {
					mTabOrderLogs.setImageDrawable(getResources().getDrawable(R.drawable.nav_stockpile));
					mTvOrderLogs.setSelected(false);
				}
				else if (currIndex == 3) {
					mTabInfoCenter.setImageDrawable(getResources().getDrawable(R.drawable.nav_info_center));
					mTvInfoCenter.setSelected(false);
				}
				break;
			case 2:
				mTvOrderLogs.setSelected(true);
				mTabOrderLogs.setImageDrawable(getResources().getDrawable(R.drawable.nav_stockpile_hover));
				if (currIndex == 0) {
					mTabHomePage.setImageDrawable(getResources().getDrawable(R.drawable.nav_home));
					mTvHomePage.setSelected(false);
				} else if (currIndex == 1) {
					mTabStManage.setImageDrawable(getResources().getDrawable(R.drawable.nav_stockpile));
					mTvStManage.setSelected(false);
				}
				else if (currIndex == 3) {
					mTabInfoCenter.setImageDrawable(getResources().getDrawable(R.drawable.nav_info_center));
					mTvInfoCenter.setSelected(false);
				}
				break;
			case 3:
				mTabInfoCenter.setImageDrawable(getResources().getDrawable(R.drawable.nav_info_center_hover));
				mTvInfoCenter.setSelected(true);
				if (currIndex == 0) {
					mTabHomePage.setImageDrawable(getResources().getDrawable(R.drawable.nav_home));
					mTvHomePage.setSelected(false);
				} else if (currIndex == 1) {
					mTabStManage.setImageDrawable(getResources().getDrawable(R.drawable.nav_stockpile));
					mTvStManage.setSelected(false);
				}
				else if (currIndex == 2) {
					mTabOrderLogs.setImageDrawable(getResources().getDrawable(R.drawable.nav_stockpile));
					mTvOrderLogs.setSelected(false);
				}
				break;
			}
			currIndex = arg0;
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

		private View getView(String id, Intent intent) {
	        return manager.startActivity(id, intent).getDecorView();
	    }
}
