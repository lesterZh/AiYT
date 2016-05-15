package com.yiqiao.stockpile;

import com.yiqiao.aiytsite.R;
import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.yiqiao.activity.BaseActivity;
import com.yiqiao.adapter.ChangeStockAdapterSimple;

public class StockClassifyLevel2SimpleChangeStock extends FinalActivity{
	@ViewInject(id=R.id.stock_classify_level2_change_stock_ok_btn) private Button okButton;
	@ViewInject(id=R.id.stock_classify_level2_change_stock_cancle_btn) private Button cancleButton;
	@ViewInject(id=R.id.stock_classify_level2_change_stock_listview)private ListView listView;
	
//	private ListView listView;
//	private Button okButton;
//	private Button cancleButton;
	private ChangeStockAdapterSimple changeStockAdapter;
	private Context mContext;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_classify_level2_change_stock);
        setTitle("ÐÞ¸Ä¿â´æ");
        
        mContext = this;
        
//        listView = (ListView) findViewById(R.id.stock_classify_level2_change_stock_listview);
//        okButton = (Button) findViewById(R.id.stock_classify_level2_change_info_ok_btn);
//        cancleButton = findViewById(id)
        
        okButton.setOnClickListener(new MyOnClickListener());
        cancleButton.setOnClickListener(new MyOnClickListener());
        changeStockAdapter = new ChangeStockAdapterSimple(mContext, StockClassifyLevel2Simple.stockDatas);
        listView.setAdapter(changeStockAdapter);
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.stock_classify_level2_change_stock_ok_btn:
				Intent intent = new Intent();
				intent.putExtra("data_from_changeStockActivity", "changeOK");
				setResult(RESULT_OK, intent);
				finish();
				break;
				
			case R.id.stock_classify_level2_change_stock_cancle_btn:
				
				finish();
				break;

			default:
				break;
			}
		}
		
	}
}
