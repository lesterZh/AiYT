package com.yiqiao.stockpile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.yiqiao.adapter.GoodsTopAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsTop;
import com.yiqiao.model.Img;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyToast;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StockClassifyLevel1 extends Activity{
	
	private static final int SHOW_INFO_LAYOUT = 1;
	private ImageView backImageView;
	private Context mContext;
	private List<GoodsTop> datas= new ArrayList<GoodsTop>();
	private GoodsTopAdapter goodsTopAdapter;
	private ListView listView;
	private TextView title;
	private RelativeLayout progressBarLayout, showInfoLayout;
	private int fromWhere;
	
	String[] strings = {"hello","nihao","good"};
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_INFO_LAYOUT:
				
				progressBarLayout.setVisibility(View.GONE);
				showInfoLayout.setVisibility(View.VISIBLE);
				goodsTopAdapter.notifyDataSetChanged();
				
				break;

			default:
				break;
			}
	    }
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_classify_level1);
		mContext = this;
		fromWhere = getIntent().getIntExtra("from_where", 0);
		System.out.println(fromWhere);
		
		initView();
	}
	
	private void initView() {
		progressBarLayout = (RelativeLayout) findViewById(R.id.stock_classify_level1_progressBar_layout);
		showInfoLayout = (RelativeLayout) findViewById(R.id.stock_classify_level1_showInfo_layout);
		progressBarLayout.setVisibility(View.VISIBLE);
		
		title = (TextView) findViewById(R.id.stock_classify_level1_title);
		//定义返回按键
		backImageView = (ImageView) findViewById(R.id.stock_classify_level1_back);
		backImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				back();
			}
		});
		
		switch (fromWhere) {
		case MyConstants.FROM_ROU:
			title.setText("肉类");
			//向服务器请求数据
			
			break;
			
		case MyConstants.FROM_QIN:
			title.setText("禽类");
			//向服务器请求数据
			
			break;
			
		case MyConstants.FROM_DAN:
			title.setText("蛋类");
			//向服务器请求数据
			
			break;
			
		case MyConstants.FROM_SHUCAI:
			title.setText("蔬菜");
			//向服务器请求数据
			
			break;
			
		case MyConstants.FROM_SHUIGUO:
			title.setText("水果");
			//向服务器请求数据
			
			break;
			
		case MyConstants.FROM_LIANGYOU:
			title.setText("粮油");
			//向服务器请求数据
			
			break;
			
		case MyConstants.FROM_QITA:
			title.setText("其它");
			//向服务器请求数据
			
			break;

		default:
			break;
		}
		
		
		listView = (ListView) findViewById(R.id.stock_classify_level1_listview);
		goodsTopAdapter = new GoodsTopAdapter(datas, mContext);
		
		listView.setAdapter(goodsTopAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				GoodsTop goods = datas.get(position);
//				MyToast.toast(mContext, goods.getName(), 1);
				Intent toLevel2Intent = new Intent(mContext, StockClassifyLevel2.class);
				toLevel2Intent.putExtra("goodsNameFromLevel1", goods.getName());
				startActivity(toLevel2Intent);
			}
		});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SystemClock.sleep(100);
				initDatas();
				mHandler.sendEmptyMessage(SHOW_INFO_LAYOUT);//显示内容切换
			}
		}).start();
		
	}
	
	private void initDatas() {
		for (int i=0; i<20; i++) {
			GoodsTop goodsTop = new GoodsTop();
			goodsTop.setName("母鸡"+i);
			goodsTop.setSalesVolume(500+i);
			
			Img img = new Img();
			img.setImage(File.separator+"image"+File.separator+"muji.jpg");
//			
			goodsTop.setImg(img);
			datas.add(goodsTop);
			
		}

		for(int i=0; i<datas.size(); i++) {
			System.out.println(i+datas.get(i).getName());
		}
	}
	
	
	private void back() {
		super.onBackPressed();
	}
}
