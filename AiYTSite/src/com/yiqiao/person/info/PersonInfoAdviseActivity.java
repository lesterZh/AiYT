package com.yiqiao.person.info;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yiqiao.aiytsite.R;

public class PersonInfoAdviseActivity extends FinalActivity {
	@ViewInject(id=R.id.feedback_back) private Button backButton;
	@ViewInject(id=R.id.feedback_submit) private Button submitButton;
	@ViewInject(id=R.id.feedback_text_theme_value) private EditText titleEditText;
	@ViewInject(id=R.id.feedback_text_leavemessage_value) private EditText messageEditText;
	@ViewInject(id=R.id.feedback_text_contactway_value) private EditText contactEditText;
	private Context mContext;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_info_commit_comment_advise);
		mContext = this;
		
		backButton.setOnClickListener(new MyOnClickListener());
		submitButton.setOnClickListener(new MyOnClickListener());
	}
	
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.feedback_back:
				back();
				break;
			case R.id.feedback_submit:
				
				if (titleEditText.getText().toString().equals("") 
						|| messageEditText.getText().toString().equals("")
						|| contactEditText.getText().toString().equals("")) {
					new AlertDialog.Builder(mContext)
					.setTitle("提示")
					.setMessage("请输入有效内容")
					.setPositiveButton("确定", null)
					.create().show();
				} else {
					String title = titleEditText.getText().toString();
					String message = messageEditText.getText().toString();
					String contact = contactEditText.getText().toString();
					
					//上传到服务器
				}
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
