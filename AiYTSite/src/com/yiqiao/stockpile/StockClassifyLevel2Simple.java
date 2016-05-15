package com.yiqiao.stockpile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

import com.yiqiao.activity.BaseActivity;
import com.yiqiao.adapter.GoodsChildAdapter;
import com.yiqiao.adapter.GoodsChildAdapterSimple;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsChild;
import com.yiqiao.util.MyToast;

public class StockClassifyLevel2Simple extends BaseActivity{

	private static final int SHOW_INFO_LAYOUT = 1 , ADAPTER_CHANG = 2;
	private ImageView backImageView;
	private Context mContext;
	public static List<HashMap<String, String>> stockDatas = new ArrayList<HashMap<String,String>>();
	private List<GoodsChild> datas= new ArrayList<GoodsChild>();
	private GoodsChildAdapterSimple goodsChildAdapter;
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
		setContentView(R.layout.stock_classify_level2_simple);
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
				
				if(data.getStringExtra("data_from_changeStockActivity").equals("changeOK")) {
					
//					for (int i=0; i<stockDatas.size(); i++) {
//						Map<String, String> map = stockDatas.get(i);
//						System.out.println(map);
//					}
					//更新adapter中的数据
					setStockDataOnlistView(datas);
				}
				//向服务器上传修改后数据
				
				//UI更新
				mHandler.sendEmptyMessage(ADAPTER_CHANG);
			}
			break;
		default:
		}
	}
	
	private void initView() {
		//切换显示进度框和详细信息
		progressBaRelativeLayout = (RelativeLayout) findViewById(R.id.stock_classify_level2_simple_progressBar_layout);
		progressBaRelativeLayout.setVisibility(View.VISIBLE);
		showInfoLayout = (LinearLayout) findViewById(R.id.stock_classify_level2_simple_show_layout);
		showInfoLayout.setVisibility(View.INVISIBLE);
		
		//商品名动态修改
		goodsName = (TextView) findViewById(R.id.stock_classify_level2_simple_goodsName);
		goodsName.setText(goodsNamefromLevel1);
		
		//具体商品信息
		listView = (ListView) findViewById(R.id.stock_classify_level2_simple_listView);
		goodsChildAdapter = new GoodsChildAdapterSimple(mContext, datas);
		listView.setAdapter(goodsChildAdapter);
		
		//定义修改库存按钮功能
		Button changeStockButton = (Button) findViewById(R.id.stock_classify_level2_simple_chang_stock);
		changeStockButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, StockClassifyLevel2SimpleChangeStock.class);
				startActivityForResult(intent, 1);
				getStockDataFromListView(datas);
			}
		});
		
		//定义返回按键
		backImageView = (ImageView) findViewById(R.id.stock_classify_level2_simple_back);
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
		for (int i=0; i<12; i++) {
			GoodsChild goodsChild = new GoodsChild();
			goodsChild.setClassify("肉类");
			goodsChild.setColor("金黄色");
			goodsChild.setPrice(28.8);
			goodsChild.setSaleAll(365);
			goodsChild.setSaleMonth(255);
			goodsChild.setStock(123+i*10);
			goodsChild.setImagePath(File.separator+"image"+File.separator+"muji.jpg");
			goodsChild.setType(i+"斤/只");
			
			datas.add(goodsChild);
		}
		
	}
	
	//获取listview中的数据  规格 和 库存
	private void getStockDataFromListView(List<GoodsChild> datas) {
		stockDatas.clear();
		for (int i=0; i<datas.size(); i++) {
			HashMap<String, String> aMap = new HashMap<String, String>();
			aMap.put("position", String.valueOf(i));
			aMap.put("type", datas.get(i).getType());
			aMap.put("stock", String.valueOf(datas.get(i).getStock()));
			
			stockDatas.add(aMap);
		}
		
	}
	
	//将修改过的库存更新到adapter的数据里 
	private void setStockDataOnlistView(List<GoodsChild> datas) {
		HashMap<String, String> aMap;
		for (int i=0; i<datas.size(); i++) {
			aMap = stockDatas.get(i);
			datas.get(i).setStock(Integer.valueOf(aMap.get("stock")));
		}
	}
	
	private void back() {
		super.onBackPressed();
		
	}
	
}
