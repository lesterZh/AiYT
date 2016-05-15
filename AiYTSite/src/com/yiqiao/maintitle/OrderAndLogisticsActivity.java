package com.yiqiao.maintitle;
import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.yiqiao.aiytsite.R;
import com.yiqiao.order.logistics.OrderQuXiaoShengQingScanActivity;
import com.yiqiao.order.logistics.OrderTuiHuoShengQingScanActivity;
import com.yiqiao.order.logistics.OrderTuiHuoZhongScanActivity;
import com.yiqiao.order.logistics.OrderWeiFaHuoScanActivity;
import com.yiqiao.order.logistics.OrderYiFaHuoScanActivity;
import com.yiqiao.order.logistics.OrderYiQuXiaoScanActivity;
import com.yiqiao.order.logistics.OrderYiTuiHuoScanActivity;
import com.yiqiao.order.logistics.OrderYiWanChengScanActivity;
import com.yiqiao.util.MyToast;

public class OrderAndLogisticsActivity extends FinalActivity {
	
	@ViewInject(id=R.id.order_main_page_weifahuo_btn) private ImageButton goodsNotSendButton;
	@ViewInject(id=R.id.order_main_page_yifahuo_btn)private ImageButton goodsSendButton;
	@ViewInject(id=R.id.order_main_page_yiwancheng_btn) private ImageButton completeButton;
	@ViewInject(id=R.id.order_main_page_yiquxiao_btn) private ImageButton cancleCompletButton;
	@ViewInject(id=R.id.order_main_page_quxiaoshenqing_btn) private ImageButton cancleApplyButton;
	@ViewInject(id=R.id.order_main_page_tuihuoshenqing_btn) private ImageButton returnApplyButton;
	@ViewInject(id=R.id.order_main_page_yituihuo_btn) private ImageButton returnCompleteButton;
	@ViewInject(id=R.id.order_main_page_tuihuozhong_btn) private ImageButton returnDuringButton;
	
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_order_and_logistics_page);
		mContext = this;
		
		MyOnClickListener myOnClickListener = new MyOnClickListener();
		
		goodsNotSendButton.setOnClickListener(myOnClickListener);
		goodsSendButton.setOnClickListener(myOnClickListener);
		completeButton.setOnClickListener(myOnClickListener);
		cancleCompletButton.setOnClickListener(myOnClickListener);
		cancleApplyButton.setOnClickListener(myOnClickListener);
		returnApplyButton.setOnClickListener(myOnClickListener);
		returnCompleteButton.setOnClickListener(myOnClickListener);
		returnDuringButton.setOnClickListener(myOnClickListener);
		
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_main_page_weifahuo_btn:
//				MyToast.toast(mContext, "未发货", 0);
				Intent intent = new Intent(mContext, OrderWeiFaHuoScanActivity.class);
				startActivity(intent);
				break;
			case R.id.order_main_page_yifahuo_btn:
//				MyToast.toast(mContext, "已发货", 0);
				Intent intent2 = new Intent(mContext, OrderYiFaHuoScanActivity.class);
				startActivity(intent2);
				break;
			case R.id.order_main_page_yiwancheng_btn:
				Intent intent3 = new Intent(mContext, OrderYiWanChengScanActivity.class);
				startActivity(intent3);
				break;
			case R.id.order_main_page_yiquxiao_btn:
				Intent intent4 = new Intent(mContext, OrderYiQuXiaoScanActivity.class);
				startActivity(intent4);
				break;
			case R.id.order_main_page_quxiaoshenqing_btn:
				Intent intent5 = new Intent(mContext, OrderQuXiaoShengQingScanActivity.class);
				startActivity(intent5);
				break;
			case R.id.order_main_page_tuihuoshenqing_btn:
				Intent intent6 = new Intent(mContext, OrderTuiHuoShengQingScanActivity.class);
				startActivity(intent6);
				break;
			case R.id.order_main_page_tuihuozhong_btn:
				Intent intent8 = new Intent(mContext, OrderTuiHuoZhongScanActivity.class);
				startActivity(intent8);
				break;
			case R.id.order_main_page_yituihuo_btn:
				Intent intent7 = new Intent(mContext, OrderYiTuiHuoScanActivity.class);
				startActivity(intent7);
				break;

			default:
				break;
			}
		}
	}
	
}












/*
public class OrderAndLogisticsActivity extends TabActivity{
	private static final String TAB_ORDER_SEND="ORDER_SEND_ACTIVITY";
	private static final String TAB_ORDER_STATEMENT="ORDER_STATEMENT_ACTIVITY";
	private static final String TAB_ORDER_AFTER_MARKET="ORDER_AFTER_MARKET_ACTIVITY";
	private static final String TAB_ORDER_STATISTIC="ORDER_STATISTIC_ACTIVITY";
	private TextView tvOrderSend=null;
	private TextView tvOrderStatement=null;
	private TextView tvOrderAfterMarket=null;
	private TextView tvOrderStatistic=null;
	private TabHost orderTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_order_and_logistics_page);
		
		initOrder();
	}
	private void initOrder() {
		// TODO Auto-generated method stub
		tvOrderSend=(TextView)findViewById(R.id.tv_order_sending);
		tvOrderStatement=(TextView)findViewById(R.id.tv_order_statement);
		tvOrderAfterMarket=(TextView)findViewById(R.id.tv_order_aftermarket);
		tvOrderStatistic=(TextView)findViewById(R.id.tv_order_statistics);
		
		tvOrderSend.setOnClickListener(new MyOnclickListener());
		tvOrderStatement.setOnClickListener(new MyOnclickListener());
		tvOrderAfterMarket.setOnClickListener(new MyOnclickListener());
		tvOrderStatistic.setOnClickListener(new MyOnclickListener());
		
		orderTabHost =getTabHost();
		
		Intent sendIntent = new Intent(this,OrderSendActivity.class);
		Intent statementIntent = new Intent(this,OrderStatementActivity.class);
		Intent afterMarketIntent = new Intent(this,OrderAfterMarket.class);
		Intent statisticIntent = new Intent(this,OrderStatisticActivity.class);
		
		orderTabHost.addTab(orderTabHost.newTabSpec(TAB_ORDER_SEND)
				.setIndicator(TAB_ORDER_SEND).setContent(sendIntent));
		orderTabHost.addTab(orderTabHost.newTabSpec(TAB_ORDER_STATEMENT)
				.setIndicator(TAB_ORDER_STATEMENT).setContent(statementIntent));
		orderTabHost.addTab(orderTabHost.newTabSpec(TAB_ORDER_AFTER_MARKET)
				.setIndicator(TAB_ORDER_AFTER_MARKET).setContent(afterMarketIntent));
		orderTabHost.addTab(orderTabHost.newTabSpec(TAB_ORDER_STATISTIC)
				.setIndicator(TAB_ORDER_STATISTIC).setContent(statisticIntent));
		
		orderTabHost.setCurrentTabByTag(TAB_ORDER_SEND);
		tvOrderSend.setSelected(true);
	}
	class MyOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.tv_order_sending:
				Toast.makeText(OrderAndLogisticsActivity.this, "第一个页面", 0).show();
				
				orderTabHost.setCurrentTabByTag(TAB_ORDER_SEND);
				tvOrderSend.setSelected(true);
				tvOrderStatement.setSelected(false);
				tvOrderAfterMarket.setSelected(false);
				tvOrderStatistic.setSelected(false);
				break;
			case R.id.tv_order_statement:
				Toast.makeText(OrderAndLogisticsActivity.this, "第二个页面", 0).show();
				orderTabHost.setCurrentTabByTag(TAB_ORDER_STATEMENT);
				tvOrderSend.setSelected(false);
				tvOrderStatement.setSelected(true);
				tvOrderAfterMarket.setSelected(false);
				tvOrderStatistic.setSelected(false);
				break;
			case R.id.tv_order_aftermarket:
				Toast.makeText(OrderAndLogisticsActivity.this, "第三个页面", 0).show();
				orderTabHost.setCurrentTabByTag(TAB_ORDER_AFTER_MARKET);
				tvOrderSend.setSelected(false);
				tvOrderStatement.setSelected(false);
				tvOrderAfterMarket.setSelected(true);
				tvOrderStatistic.setSelected(false);
				break;
			case R.id.tv_order_statistics:
				Toast.makeText(OrderAndLogisticsActivity.this, "第四个页面", 0).show();
				orderTabHost.setCurrentTabByTag(TAB_ORDER_STATISTIC);
				tvOrderSend.setSelected(false);
				tvOrderStatement.setSelected(false);
				tvOrderAfterMarket.setSelected(false);
				tvOrderStatistic.setSelected(true);
				break;
			}
		}
		
	}
}

*/