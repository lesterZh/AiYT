package com.yiqiao.stockpile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yiqiao.activity.BaseActivity;
import com.yiqiao.adapter.GoodsChildAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsChild;

public class StockClassifyLevel2 extends BaseActivity{
	
	private static final int SHOW_INFO_LAYOUT = 1 , ADAPTER_CHANG = 2;
	private ImageView backImageView;
	private Context mContext;
	private List<GoodsChild> datas= new ArrayList<GoodsChild>();
	private GoodsChildAdapter goodsChildAdapter;
	private ListView listView;
	private TextView goodsName;
	private RelativeLayout progressBaRelativeLayout;
	private LinearLayout showInfoLayout;
	private String  goodsNamefromLevel1;
	private int listViewSelectedPosition;
	
	String[] strings = {"hello","nihao","good"};
	
	private Handler mHandler = new Handler() {
		
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_INFO_LAYOUT:
				
				goodsChildAdapter.notifyDataSetChanged();
				progressBaRelativeLayout.setVisibility(View.GONE);
				showInfoLayout.setVisibility(View.VISIBLE);
				
				break;
				
			case ADAPTER_CHANG:
				
				goodsChildAdapter.notifyDataSetChanged();
				
				break;

			default:
				break;
			}
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_classify_level2);
		mContext = this;
		goodsNamefromLevel1 = getIntent().getStringExtra("goodsNameFromLevel1");
		
		
		initView();
	}
	
	//接收调用修改信息的活动返回结果
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				
				int ps = data.getIntExtra("data_return_positon", 0);
				GoodsChild gChild = (GoodsChild) data.getSerializableExtra("data_return_goodsChild");
				
				//向服务器上传修改后数据
				
				//UI更新
				datas.set(ps, gChild);
				mHandler.sendEmptyMessage(ADAPTER_CHANG);
			}
			break;
		default:
		}
	}
	
	private void initView() {
		//切换显示进度框和详细信息
		progressBaRelativeLayout = (RelativeLayout) findViewById(R.id.stock_classify_level2_progressBar_layout);
		progressBaRelativeLayout.setVisibility(View.VISIBLE);
		showInfoLayout = (LinearLayout) findViewById(R.id.stock_classify_level2_show_layout);
		showInfoLayout.setVisibility(View.INVISIBLE);
		
		//商品名动态修改
		goodsName = (TextView) findViewById(R.id.stock_classify_level2_goodsName);
		goodsName.setText(goodsNamefromLevel1);
		
		//具体商品信息
		listView = (ListView) findViewById(R.id.stock_classify_level2_listView);
		goodsChildAdapter = new GoodsChildAdapter(mContext, datas);
		listView.setAdapter(goodsChildAdapter);
		ItemOnLongClick1();
		
		//定义添加新品种按钮功能
		Button addNewGoodsButton = (Button) findViewById(R.id.stock_classify_level2_addNew);
		addNewGoodsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, AddNewGoods.class);
				startActivity(intent);
			}
		});
		
		//定义返回按键
		backImageView = (ImageView) findViewById(R.id.stock_classify_level2_back);
		backImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				back();
			}
		});
		
		
		
		//获取数据
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//如果不休眠 界面跳转会延迟
				SystemClock.sleep(100);
				initDatasFromServer(null);
				mHandler.sendEmptyMessage(SHOW_INFO_LAYOUT);
			}
		}).start();
		
	}
	
	
	private void  initDatasFromServer(String address) {
		//从服务器请求数据
		for (int i=0; i<5; i++) {
			GoodsChild goodsChild = new GoodsChild();
			goodsChild.setClassify("肉类");
			goodsChild.setColor("金黄色");
			goodsChild.setPrice(28.8);
			goodsChild.setSaleAll(365);
			goodsChild.setSaleMonth(255);
			goodsChild.setStock(123);
			goodsChild.setImagePath(File.separator+"image"+File.separator+"muji.jpg");
			
			datas.add(goodsChild);
		}
		
	}
	
	private void back() {
		super.onBackPressed();
		
	}
	
	
	//listview长按弹出菜单
	private void ItemOnLongClick1() {  
		//注：setOnCreateContextMenuListener是与下面onContextItemSelected配套使用的  

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				listViewSelectedPosition = position;
				
				return false;
			}
		});
		listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.add(0,0,0,"添加新品种");  
				menu.add(0,1,0,"删除该品种");  
				menu.add(0,2,0,"取消");  
			}  
		});  
	}  

	// 长按菜单响应函数  
	public boolean onContextItemSelected(MenuItem item) {  

		switch(item.getItemId()) {  
		
		case 0:  
			// 添加操作  
			Toast.makeText(mContext,  
					"添加",  
					Toast.LENGTH_SHORT).show();  
		break;  

		case 1:  
			// 删除操作  
			datas.remove(listViewSelectedPosition);
			mHandler.sendEmptyMessage(ADAPTER_CHANG);
			//通知服务器更新
			
			break;  

		case 2:  
			// 取消操作  
			break;  
//
		default:  
			break;  
		}  
//
		return super.onContextItemSelected(item);  
//
	}  
}
