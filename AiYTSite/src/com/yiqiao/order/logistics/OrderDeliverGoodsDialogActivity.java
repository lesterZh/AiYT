package com.yiqiao.order.logistics;

import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderInvoiceInfo;
import com.yiqiao.ui.CustomProgress;
import com.yiqiao.ui.DateTimePickDialogUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class OrderDeliverGoodsDialogActivity extends FinalActivity{
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_ok_btn) private Button okButton;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_cancle_btn) private Button cancleButton;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_input_time) private EditText timeEditText;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_input_seq) private EditText seqEditText;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_input_company) private Spinner companySpinner;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_input_company_other) private EditText otherCompanyEditText;
	private Context mContext;
	private static final String[] expressCompany = {"顺丰快递","申通快递","圆通快递","中通快递","汇通快运","韵达快递","天天快递","EMS","宅急送","其它快递公司"};
	private ArrayAdapter<String> adapter;
	private String selectedCompany;
	private String expressSeq;
	private String deliverTime;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_wei_fa_huo_deliver_goods_dialog_activity);
		mContext = this;
		
		setTitle("输入发货信息");
		position = getIntent().getIntExtra("position", -1);
		
		adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, expressCompany);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		companySpinner.setAdapter(adapter);
		
		okButton.setOnClickListener(new MyOnClickListener());
		cancleButton.setOnClickListener(new MyOnClickListener());
		//发货公司选择
		companySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selectedCompany = expressCompany[position];
//				System.out.println(selectedCompany);
				if ("其它快递公司".equals(selectedCompany)) {
					otherCompanyEditText.setVisibility(View.VISIBLE);
				} else {
					otherCompanyEditText.setVisibility(View.GONE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		
		//发货时间确定
		timeEditText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String initDateTime="2015年6月6日 11:11";
				DateTimePickDialogUtil dateTimePickDialogUtil = new DateTimePickDialogUtil(OrderDeliverGoodsDialogActivity.this, initDateTime);
				dateTimePickDialogUtil.dateTimePicKDialog(timeEditText);
			}
		});
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_wei_fa_huo_deliver_goods_ok_btn:
				
				//发货订单信息
				OrderInvoiceInfo orderInvoiceInfo = new OrderInvoiceInfo();
				if ("其它快递公司".equals(selectedCompany)) {
					selectedCompany = otherCompanyEditText.getText().toString();
				}
				expressSeq = seqEditText.getText().toString();//单号
				String timeString = timeEditText.getText().toString();//发货时间
				
				if (expressSeq.equals("") || timeString.equals("")) {
					new AlertDialog.Builder(mContext)
					.setTitle("提示").setMessage("请输入有效信息！")
					.setPositiveButton("确认", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					})
					.create().show();
				} else {
					
					orderInvoiceInfo.setDeliverTime(timeString);
					orderInvoiceInfo.setLogisticsCompany(selectedCompany);
					orderInvoiceInfo.setLogisticsSerial(expressSeq);
					
					
					
					Intent intent = new Intent();
					intent.putExtra("position", position);
					intent.putExtra("orderInvoiceInfo", orderInvoiceInfo);
					setResult(RESULT_OK, intent);
					
					finish();
				}
				
				break;
				
			case R.id.order_wei_fa_huo_deliver_goods_cancle_btn:
				
				setResult(RESULT_CANCELED);
				finish();
				break;

			default:
				break;
			}
		}
		
	}

}
