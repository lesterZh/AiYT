package com.yiqiao.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yiqiao.adapter.OrderQuXiaoShenQingScanAdapter.ViewHolder;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.order.logistics.OrderApplyReasonDialogActivity;
import com.yiqiao.order.logistics.OrderDetailInfoActivity;
import com.yiqiao.order.logistics.OrderQuXiaoShengQingScanActivity;
import com.yiqiao.order.logistics.OrderTuiHuoShengQingScanActivity;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyToast;

public class OrderTuiHuoShenQingScanAdapter extends BaseAdapter{
	private List<OrderTotalInfo> orderTotalInfosList;
	private Context mContext;
	
	String ImageUrl = "http://192.168.0.111/image/ya.jpg";
	DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.load_download)
	.showImageOnFail(R.drawable.load_error)
	.cacheInMemory(true)
	.cacheOnDisc(true)
	.bitmapConfig(Bitmap.Config.RGB_565)
	.build();
	
	
	public OrderTuiHuoShenQingScanAdapter(Context context, List<OrderTotalInfo> orderTotalInfos) {
		super();
		orderTotalInfosList = orderTotalInfos;
		mContext = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orderTotalInfosList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return orderTotalInfosList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(position);
		// TODO Auto-generated method stub
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.order_quxiao_shenqing_scan_list_item, null);
			viewHolder.orderSeq = (TextView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_seq);
			viewHolder.goodsName = (TextView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_goods_name);
			viewHolder.goodsAttri = (TextView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_goods_attri);
			viewHolder.priceValue = (TextView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_price_value);
			viewHolder.goodsAmount = (TextView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_amount);
			viewHolder.creatTime = (TextView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_create_time);
			viewHolder.totalMoney = (TextView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_total_money);
			
			viewHolder.agreeButton = (Button) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_agree_btn);
			viewHolder.notAgreeButton = (Button) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_not_agree_btn);
			viewHolder.goodsImageView = (ImageView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_goods_image);
			viewHolder.toDetailImage = (ImageView) convertView.findViewById(R.id.order_quxiao_shenqing_list_item_arrow_detail);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.orderSeq.setText(orderTotalInfo.getOrderSeq());//订单号
		viewHolder.creatTime.setText("订单日期："+orderTotalInfo.getOrderTime());//订单时间
		viewHolder.goodsName.setText(orderTotalInfo.getGoodsInfosList().get(0).getGoodsName());//多个商品时首页默认显示第一个
		viewHolder.goodsAttri.setText(orderTotalInfo.getGoodsInfosList().get(0).getAttribute());//商品属性
		viewHolder.goodsAmount.setText(orderTotalInfo.getGoodsInfosList().get(0).getNums()+"");//商品数量
		viewHolder.priceValue.setText(orderTotalInfo.getGoodsInfosList().get(0).getPrice()+"");//商品单价
		
		viewHolder.totalMoney.setText("实付："+orderTotalInfo.getRealPayMoney());
		
		//为按钮添加标签
		viewHolder.agreeButton.setTag(position);
		viewHolder.notAgreeButton.setTag(position);
		viewHolder.toDetailImage.setTag(position);
		
		//添加监听事件
		viewHolder.agreeButton.setOnClickListener(new MyOnClickListener());
		viewHolder.notAgreeButton.setOnClickListener(new MyOnClickListener());
		viewHolder.toDetailImage.setOnClickListener(new MyOnClickListener());
		//图片
		ImageLoader.getInstance().displayImage(MyConstants.IMAGE1_PATH, viewHolder.goodsImageView, options);
		return convertView;
	}
	
	class ViewHolder {
		TextView orderSeq, goodsName,goodsAttri, priceValue, goodsAmount,creatTime,totalMoney;
		Button agreeButton,notAgreeButton;
		ImageView goodsImageView, toDetailImage;
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int ps = (Integer)v.getTag();
			switch (v.getId()) {
			case R.id.order_quxiao_shenqing_list_item_agree_btn:
//				MyToast.toast(mContext, "agree:"+ps, 0);
				Intent intent2 = new Intent(mContext, OrderApplyReasonDialogActivity.class);
				intent2.putExtra("position", ps);
				intent2.putExtra("button", "agree");
				((OrderTuiHuoShengQingScanActivity)mContext).startActivityForResult(intent2, 1);//同意取消
				break;
				
			case R.id.order_quxiao_shenqing_list_item_not_agree_btn:
//				MyToast.toast(mContext, "not agree"+ps, 0);
				Intent intent3 = new Intent(mContext, OrderApplyReasonDialogActivity.class);
				intent3.putExtra("position", ps);
				intent3.putExtra("button", "notagree");
				((OrderTuiHuoShengQingScanActivity)mContext).startActivityForResult(intent3, 2);//不同意取消
				break;
				
			case R.id.order_quxiao_shenqing_list_item_arrow_detail:
//				MyToast.toast(mContext, "detail:"+ps, 0);
				Intent intent = new Intent(mContext, OrderDetailInfoActivity.class);
				OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(ps);
				intent.putExtra("orderTotalInfo", orderTotalInfo);
				mContext.startActivity(intent);
				
				break;

			default:
				break;
			}
		}
		
	}
}
