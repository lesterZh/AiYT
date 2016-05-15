package com.yiqiao.adapter;

import java.text.DecimalFormat;
import java.util.List;

import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderGoodsInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderGoodsInfoListAdapter extends BaseAdapter{

	private List<OrderGoodsInfo> orderGoodsInfosList;
	private Context mContext;
	
	public OrderGoodsInfoListAdapter(Context context, List<OrderGoodsInfo> orderGoodsInfos) {
		super();
		orderGoodsInfosList = orderGoodsInfos;
		mContext = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orderGoodsInfosList.size();
//		return 5;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return orderGoodsInfosList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		OrderGoodsInfo orderGoodsInfo = orderGoodsInfosList.get(position);
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.order_goods_detail_list_item, null);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.order_goods_detail_listView_image);
			viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.order_goods_detail_listView_name);
			viewHolder.typeTextView = (TextView)convertView.findViewById(R.id.order_goods_detail_listView_type);
			viewHolder.attriTextView = (TextView)convertView.findViewById(R.id.order_goods_detail_listView_attri);
			viewHolder.priceTextView = (TextView)convertView.findViewById(R.id.order_goods_detail_listView_price);
			viewHolder.numsTextView = (TextView)convertView.findViewById(R.id.order_goods_detail_listView_nums);
			viewHolder.totalMoneyTextView = (TextView)convertView.findViewById(R.id.order_goods_detail_listView_total_money);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.nameTextView.setText(orderGoodsInfo.getGoodsName());
		viewHolder.typeTextView.setText("规格："+orderGoodsInfo.getType());
		viewHolder.attriTextView.setText(orderGoodsInfo.getAttribute());
		viewHolder.priceTextView.setText("价格："+orderGoodsInfo.getPrice());
		viewHolder.numsTextView.setText("数量："+orderGoodsInfo.getNums());
		double totalMoney = orderGoodsInfo.getPrice()*orderGoodsInfo.getNums();
		DecimalFormat dFormat = new DecimalFormat("0.0#");
		viewHolder.totalMoneyTextView.setText("金额："+dFormat.format(totalMoney));
		
		return convertView;
		
	}
	
	
	private class ViewHolder {
		ImageView imageView;
		TextView nameTextView, typeTextView, attriTextView, priceTextView, numsTextView, totalMoneyTextView;
	}

}
