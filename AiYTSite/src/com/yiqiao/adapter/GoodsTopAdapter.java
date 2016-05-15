package com.yiqiao.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsTop;
import com.yiqiao.util.MyConstants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsTopAdapter extends BaseAdapter{

	private List<GoodsTop> datas;
	private Context context;
	
	String ImageUrl = MyConstants.IMAGE1_PATH;
	DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.load_download)
	.showImageOnFail(R.drawable.load_error)
	.cacheInMemory(true)
	.cacheOnDisc(true)
	.bitmapConfig(Bitmap.Config.RGB_565)
	.build();
	
	public GoodsTopAdapter(List<GoodsTop> datas, Context context) {
		super();
		this.datas = datas;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public GoodsTop getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vHolder = null;
		GoodsTop goodsTop = getItem(position);

		
		if(convertView == null) {
			vHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.stock_classify_level1_listitem, null);
			vHolder.imageView = (ImageView) convertView.findViewById(R.id.stock_level1_listview_image);
			vHolder.name = (TextView) convertView.findViewById(R.id.stock_level1_listview_name);
			vHolder.sales = (TextView) convertView.findViewById(R.id.stock_level1_listview_sales);
			
			convertView.setTag(vHolder);
		} else {
			
			vHolder = (ViewHolder) convertView.getTag();
		}
		
		vHolder.name.setText(goodsTop.getName());
		vHolder.sales.setText(goodsTop.getSalesVolume()+"");//此处必须转化为字符串类型，否则报错
//		vHolder.imageView.setImageResource(R.drawable.muji);
		ImageLoader.getInstance().displayImage(ImageUrl, vHolder.imageView, options);
		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView name, sales;
	}
}
