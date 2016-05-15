package com.yiqiao.adapter;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yiqiao.activity.LoginEditActivity;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsChild;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeStockAdapterSimple extends BaseAdapter{

	private List<HashMap<String, String>> datasList;
	private Context mContext;
	
	public ChangeStockAdapterSimple(Context context, List<HashMap<String, String>> datas) {
		super();
		// TODO Auto-generated constructor stub
		mContext = context;
		this.datasList = datas;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datasList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datasList.get(position);
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
		HashMap<String, String> map = datasList.get(position);
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.stock_classify_level2_change_stock_listitem, null);
			
			viewHolder.typeTextView = (TextView) convertView.findViewById(R.id.stock_classify_level2_change_stock_type_textview);
			viewHolder.stockEditText = (EditText) convertView.findViewById(R.id.stock_classify_level2_change_stock_stock_edittext);
			
			convertView.setTag(viewHolder);
		} else {
			
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.typeTextView.setText(map.get("type"));
		viewHolder.stockEditText.setText(map.get("stock"));
		viewHolder.stockEditText.addTextChangedListener(new MyTextWatcher(position));
		return convertView;
	}
	
	class ViewHolder {
		TextView typeTextView;
		EditText stockEditText;
	}
	
	class MyTextWatcher implements TextWatcher {

		private int position;
		public MyTextWatcher(int position) {
			super();
			this.position = position;
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if (isInteger(s.toString())) {
				datasList.get(position).put("stock", s.toString());
			} else {
				
				new AlertDialog.Builder(mContext)
				.setTitle("格式错误").setMessage("请输入有效数字！")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
				.create().show();
			}
		}
		
	}
	
	public static boolean isInteger(String str) {
		try {
			int i = Integer.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
	}

}
