package com.yiqiao.order.logistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.content.Intent;
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

import com.yiqiao.adapter.OrderQuXiaoShenQingScanAdapter;
import com.yiqiao.adapter.OrderTuiHuoShenQingScanAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderGoodsInfo;
import com.yiqiao.model.OrderInvoiceInfo;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.util.MyConstants;

//使用的很多东西和 取消申请活动相同
public class OrderTuiHuoShengQingScanActivity extends FinalActivity{
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_progress_bar_layout)private RelativeLayout progressBarLayout;
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_show_info_layout)private LinearLayout showInfoLayout;
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_title_back)private ImageView backImageView; 
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_listview)private ListView listView;
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_title) private TextView titleTextView;
	
	private static final int SHOW_INFO_LAYOUT = 1;
	private static final int SHOW_PROGRESS_BAR_LAYOUT = 2;
	private static final int REFRESH_LISTVIEW = 3;
	private Context mContext;
	private List<OrderTotalInfo> orderTotalInfosList = new ArrayList<OrderTotalInfo>();
	private OrderTuiHuoShenQingScanAdapter adapter;//数据填充适配器
	
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
		setContentView(R.layout.order_quxiao_shenqing_scan_layout);
		titleTextView.setText("退货申请");
		mContext = this;
		
		initView();
	}
	
	@Override
	protected void  onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1)//同意退货
		{
			if (resultCode == RESULT_OK) {
				int position = data.getIntExtra("selected_positon", -1);
				String cancleReason = data.getStringExtra("cancle_reason");
				System.out.println(cancleReason);
				
				//退货后操作 更改状态  更新Listview
				OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(position);
				orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_TUIHUO_ZHONG);//更改状态为退货中订单
				//上传服务器更新
				
				//更新UI 删除已取消订单
				orderTotalInfosList.remove(position);
				mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
			}
		} else if (requestCode == 2) { //不同意退货
			if (resultCode == RESULT_OK) {
				int position = data.getIntExtra("selected_positon", -1);
				String cancleReason = data.getStringExtra("cancle_reason");
				System.out.println(cancleReason);
				
				//不同意退货后操作 更改状态  更新Listview
				OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(position);
				orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_WANCHENG);//更改状态为已完成订单
				//上传服务器更新
				
				//更新UI 
				orderTotalInfosList.remove(position);
				mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
				
			}
		}
	}
	private void initView() {
		//显示进度条
		progressBarLayout.setVisibility(View.VISIBLE);
		showInfoLayout.setVisibility(View.INVISIBLE);
		backImageView.setOnClickListener(new MyOnClickListener());
		
		adapter = new OrderTuiHuoShenQingScanAdapter(mContext, orderTotalInfosList);
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
			orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_TUIHUO_SHENAQINF);
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
			orderTotalInfo.setOrderInvoiceInfo(orderInvoiceInfo);
			
			orderTotalInfosList.add(orderTotalInfo);
		}
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_quxiao_shenqing_scan_title_back:
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
