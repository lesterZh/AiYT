package com.yiqiao.order.logistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiqiao.adapter.OrderTuiHuoZhongScanAdapter;
import com.yiqiao.adapter.OrderYiFaHuoScanAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderGoodsInfo;
import com.yiqiao.model.OrderInvoiceInfo;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.util.MyConstants;

public class OrderTuiHuoZhongScanActivity extends FinalActivity{
	@ViewInject(id=R.id.order_yi_fa_huo_scan_progress_bar_layout)private RelativeLayout progressBarLayout;
	@ViewInject(id=R.id.order_yi_fa_huo_scan_show_info_layout)private LinearLayout showInfoLayout;
	@ViewInject(id=R.id.order_yi_fa_huo_scan_title_back)private ImageView backImageView; 
	@ViewInject(id=R.id.order_yi_fa_huo_scan_listview)private ListView listView;
	@ViewInject(id=R.id.order_yi_fa_huo_scan_title)private TextView titleTextView;
	
	
	private static final int SHOW_INFO_LAYOUT = 1;
	private static final int SHOW_PROGRESS_BAR_LAYOUT = 2;
	private static final int REFRESH_LISTVIEW = 3;
	private Context mContext;
	private List<OrderTotalInfo> orderTotalInfosList = new ArrayList<OrderTotalInfo>();
	private OrderTuiHuoZhongScanAdapter adapter;//数据填充适配器
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_INFO_LAYOUT:
				progressBarLayout.setVisibility(View.GONE);
				showInfoLayout.setVisibility(View.VISIBLE);
				
				break;
				
			case SHOW_PROGRESS_BAR_LAYOUT:
				progressBarLayout.setVisibility(View.VISIBLE);
				showInfoLayout.setVisibility(View.INVISIBLE);
				
				break;
				
			case REFRESH_LISTVIEW:
				adapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
			
	    }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_yi_fa_huo_scan_layout);
		titleTextView.setText("退货中订单");
		mContext = this;
		
		initView();
	}
	
	
	private void initView() {
		//显示进度条
		progressBarLayout.setVisibility(View.VISIBLE);
		showInfoLayout.setVisibility(View.INVISIBLE);
		backImageView.setOnClickListener(new MyOnClickListener());
		
		adapter = new OrderTuiHuoZhongScanAdapter(mContext, orderTotalInfosList);
		listView.setAdapter(adapter);
		
		qureyDataFromServer();
		mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
	}
	
	//从服务器获取数据
	private void qureyDataFromServer() {
		
		//模拟消耗时间
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(SHOW_INFO_LAYOUT);
			}
		}, 1000);
		
		for (int i=0; i<10; i++) {
			
			OrderTotalInfo orderTotalInfo = new OrderTotalInfo();
			//订单信息
			orderTotalInfo.setOrderSeq("59008028004"+i);
			orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_TUIHUO_ZHONG);
			orderTotalInfo.setRealPayMoney(28.8+i*10);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			
			orderTotalInfo.setOrderTime(time);
			
			//商品信息添加
			List<OrderGoodsInfo> orderGoodsInfosList = new ArrayList<OrderGoodsInfo>();
			for (int j = 0; j < 3; j++) {
				OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
				
				orderGoodsInfo.setGoodsName("老母鸡"+j);
				orderGoodsInfo.setType(j+5+"斤/只");
				orderGoodsInfo.setAttribute("属性"+j);
				orderGoodsInfo.setNums(100+j*8);
				orderGoodsInfo.setPrice(15.6+j*10);
				orderGoodsInfosList.add(orderGoodsInfo);
			}
			orderTotalInfo.setGoodsInfosList(orderGoodsInfosList);
			
			
			OrderInvoiceInfo orderInvoiceInfo = new OrderInvoiceInfo();
			orderInvoiceInfo.setConsumerName("张晓天");
			orderInvoiceInfo.setTel("15566889966");
			orderInvoiceInfo.setAddress("四川省成都市二环路，加州湾5栋1单元2301");
			
			orderInvoiceInfo.setLogisticsCompany("顺丰快递");
			orderInvoiceInfo.setLogisticsSerial("LP00036925158481");
			orderInvoiceInfo.setDeliverTime("2015-6-18 13:56");
			orderTotalInfo.setOrderInvoiceInfo(orderInvoiceInfo);
			
			orderTotalInfosList.add(orderTotalInfo);
		}
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_yi_fa_huo_scan_title_back:
				back();
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
