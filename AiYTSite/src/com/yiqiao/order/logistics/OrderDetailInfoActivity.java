package com.yiqiao.order.logistics;

import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import com.yiqiao.adapter.OrderGoodsInfoListAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderGoodsInfo;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.ui.ListViewForScrollView;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyUtility;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class OrderDetailInfoActivity extends FinalActivity{
	@ViewInject(id=R.id.order_detail_info_main_scrollview) private ScrollView mainScrollView;
	@ViewInject(id=R.id.order_detail_info__title_back) private ImageView backImageView;
	@ViewInject(id=R.id.order_detail_info_seq) private TextView seqTextView;
	@ViewInject(id=R.id.order_detail_info_order_state) private TextView orderStateTextView;
	@ViewInject(id=R.id.order_detail_info_create_time) private TextView creatTimeTextView;
	@ViewInject(id=R.id.order_detail_info_goods_listview) private ListView goodsListView;
	@ViewInject(id=R.id.order_detail_info_consignee_name) private TextView consigneeNameTextView;
	@ViewInject(id=R.id.order_detail_info_consignee_tel) private TextView consigneeTelTextView;
	@ViewInject(id=R.id.order_detail_info_consignee_address) private TextView consigneeAddressTextView;
	
	@ViewInject(id=R.id.order_detail_info_logistics_deliver_time) private TextView deliverTimeTextView;
	@ViewInject(id=R.id.order_detail_info_logistics_company) private TextView logicsticsCompanyTextView;
	@ViewInject(id=R.id.order_detail_info_logistics_serial) private TextView logicsticsSerialTextView;
	@ViewInject(id=R.id.order_detail_info_logistics_receive_time_layout) private LinearLayout receiveTimeLayout;
	@ViewInject(id=R.id.order_detail_info_logistics_receive_time) private TextView receiveTimeTextView;
	
	
	@ViewInject(id=R.id.order_detail_info_logicstics_info_layout) private LinearLayout logisticsLayout;
	@ViewInject(id=R.id.order_detail_info_consignee_layout) private LinearLayout receiverAndLogisticsLayout;
	@ViewInject(id=R.id.order_detail_info_cancle_reason_layout) private LinearLayout cancleReasonLayout;
	@ViewInject(id=R.id.order_detail_info_cancle_reason) private TextView cancleReasonTextView;
	
	@ViewInject(id=R.id.order_detail_info_return_goods_layout) private LinearLayout returnGoodsLayout;
	
	
	private Context mContext;
	private OrderTotalInfo orderTotalInfo;
	private List<OrderGoodsInfo> orderGoodsInfosList;
	private OrderGoodsInfoListAdapter orderGoodsInfoListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_detail_info);
		mContext = this;
		
		orderTotalInfo = (OrderTotalInfo) getIntent().getSerializableExtra("orderTotalInfo");
		System.out.println(orderTotalInfo.getOrderSeq());
		initView();
	}
	
	private void initView() {
		backImageView.setOnClickListener(new MyOnClickListener());
		
		//根据上一活动传递过来的对象信息 更新UI
		//订单状态设定
		switch (orderTotalInfo.getOrderState()) {
		case MyConstants.ORDER_STATE_WEIFAHUO:
			orderStateTextView.setText("订单状态：未发货");
			logisticsLayout.setVisibility(View.GONE);//隐藏物流信息
			
			break;
		case MyConstants.ORDER_STATE_YIFAHUO:
			orderStateTextView.setText("订单状态：已发货");
			break;
		case MyConstants.ORDER_STATE_WANCHENG:
			orderStateTextView.setText("订单状态：已完成");
			
			break;
		
		case MyConstants.ORDER_STATE_YIQUXIAO:
			orderStateTextView.setText("订单状态：已取消");
			logisticsLayout.setVisibility(View.GONE);//隐藏物流信息
			cancleReasonLayout.setVisibility(View.VISIBLE);//显示取消订单原因
			break;
			
		case MyConstants.ORDER_STATE_QUXIAO_SHENAQINF:
			orderStateTextView.setText("订单状态：申请取消");
			
			break;
		case MyConstants.ORDER_STATE_TUIHUO_SHENAQINF:
			orderStateTextView.setText("订单状态：申请退货");
			
			break;
		case MyConstants.ORDER_STATE_TUIHUO_ZHONG:
			orderStateTextView.setText("订单状态：退货中");
			returnGoodsLayout.setVisibility(View.VISIBLE);//显示退货信息
			break;
		case MyConstants.ORDER_STATE_YITUIHUO:
			orderStateTextView.setText("订单状态：已退货");
			returnGoodsLayout.setVisibility(View.VISIBLE);//显示退货信息
			break;

		default:
			break;
		}
		seqTextView.setText(orderTotalInfo.getOrderSeq());
		creatTimeTextView.setText("订单日期："+orderTotalInfo.getOrderTime());
		
		orderGoodsInfosList = orderTotalInfo.getGoodsInfosList();
		orderGoodsInfoListAdapter = new OrderGoodsInfoListAdapter(mContext, orderGoodsInfosList);
		goodsListView.setAdapter(orderGoodsInfoListAdapter);
		MyUtility.setListViewHeightBasedOnChildren(goodsListView);//c重新设置listview高度
		mainScrollView.smoothScrollTo(0, 0); //将scrollview滚动到开头显示
		
		//发货信息设置
		consigneeNameTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getConsumerName());
		consigneeTelTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getTel());
		consigneeAddressTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getAddress());
		
		logicsticsCompanyTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getLogisticsCompany());
		logicsticsSerialTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getLogisticsSerial());
		deliverTimeTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getDeliverTime());
		
		if (orderTotalInfo.getOrderState() == MyConstants.ORDER_STATE_WANCHENG) {
			receiveTimeLayout.setVisibility(View.VISIBLE);
			receiveTimeTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getReceiveTime());
		}
		
		if (orderTotalInfo.getOrderState() == MyConstants.ORDER_STATE_YIQUXIAO) {
			cancleReasonTextView.setText(orderTotalInfo.getOrderCancleReason());
		}
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_detail_info__title_back://定义返回按键
				back();
				break;

			default:
				break;
			}
		}
	}
	
	private void back() {
		super.onBackPressed();
	}

}
