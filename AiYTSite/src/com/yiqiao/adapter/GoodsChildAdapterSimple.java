package com.yiqiao.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yiqiao.adapter.GoodsChildAdapter.MyLisener;
import com.yiqiao.adapter.GoodsChildAdapter.ViewHolder;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsChild;
import com.yiqiao.stockpile.ChangeInfoActivity;
import com.yiqiao.stockpile.StockClassifyLevel2;
import com.yiqiao.util.MyConstants;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsChildAdapterSimple extends BaseAdapter{
	private List<GoodsChild> datas;
	private Context mContext;
	
	String ImageUrl = "http://192.168.0.111/image/ya.jpg";
	DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.load_download)
	.showImageOnFail(R.drawable.load_error)
	.cacheInMemory(true)
	.cacheOnDisc(true)
	.bitmapConfig(Bitmap.Config.RGB_565)
	.build();
	
	public GoodsChildAdapterSimple(Context context, List<GoodsChild> datas) {
		super();
		// TODO Auto-generated constructor stub
		mContext = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
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
		GoodsChild goodsChild = datas.get(position);
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.stock_classify_level2_listitem_simple, null);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.stock_level2_listView_image);
			viewHolder.type = (TextView) convertView.findViewById(R.id.stock_level2_listView_type);
			viewHolder.color = (TextView) convertView.findViewById(R.id.stock_level2_listView_color);
			viewHolder.classify = (TextView) convertView.findViewById(R.id.stock_level2_listView_classify);
			viewHolder.price = (TextView) convertView.findViewById(R.id.stock_level2_listView_price);
			viewHolder.saleMonth = (TextView) convertView.findViewById(R.id.stock_level2_listView_saleMonth);
			viewHolder.saleAll = (TextView) convertView.findViewById(R.id.stock_level2_listView_saleAll);
			viewHolder.stock = (TextView) convertView.findViewById(R.id.stock_level2_listView_stock);
			viewHolder.changeButton = (Button) convertView.findViewById(R.id.stock_level2_listView_changeBtn);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.type.setText("规格："+goodsChild.getType());
		viewHolder.color.setText("颜色："+goodsChild.getColor());
		viewHolder.classify.setText("分类："+goodsChild.getClassify());
		viewHolder.stock.setText("库存："+goodsChild.getStock());
		viewHolder.price.setText("价格："+goodsChild.getPrice());
		viewHolder.saleMonth.setText("月销量："+goodsChild.getSaleMonth());
		viewHolder.saleAll.setText("总销量："+goodsChild.getSaleAll());
		
		ImageLoader.getInstance().displayImage(MyConstants.IMAGE1_PATH, viewHolder.imageView, options);
		
		return convertView;
	}
	
	
	class ViewHolder {
		ImageView imageView;
		TextView type, color, classify,price, saleMonth, saleAll, stock;
		Button changeButton;
	}
	
	
}
