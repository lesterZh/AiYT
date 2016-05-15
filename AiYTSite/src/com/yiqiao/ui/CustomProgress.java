package com.yiqiao.ui;

import com.yiqiao.aiytsite.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class CustomProgress extends Dialog{

	public CustomProgress(Context context) {
		
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomProgress(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}
	
	public CustomProgress(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	/**
	 *  
	 * @param message
	 */
    public void setMessage(CharSequence message) {  
        if (message != null && message.length() > 0) {  
            findViewById(R.id.message).setVisibility(View.VISIBLE);  
            TextView txt = (TextView) findViewById(R.id.message);  
            txt.setText(message);  
            txt.invalidate();  
        }  
    }  
  
    public static CustomProgress getInstance(Context context, CharSequence message, boolean cancelable) {
    	CustomProgress dialog = new CustomProgress(context, R.style.Custom_Progress);  
        dialog.setTitle("");  
        dialog.setContentView(R.layout.progress_custom);  
        if (message == null || message.length() == 0) {  
            dialog.findViewById(R.id.message).setVisibility(View.GONE);  
        } else {  
            TextView txt = (TextView) dialog.findViewById(R.id.message);  
            txt.setText(message);  
        }  
        //设置对话框是否可消除
        dialog.setCancelable(cancelable);  
        // 设置窗体位置  中心
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;  
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
        // 设置背景透明度
//        dimAmount在0.0f和1.0f之间，0.0f完全不暗，即背景是可见的 ，1.0f时候，背景全部变黑暗。
//
//        如果要达到背景全部变暗的效果，需要设置  dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        ，否则，背景无效果。
        lp.dimAmount = 0.6f;  
        dialog.getWindow().setAttributes(lp);  
        return dialog;  
    }

    /**
     * 
     * @param context
     * @param message
     * @param cancelable
     * @param cancelListener
     * @return
     */
    public static CustomProgress show(Context context, CharSequence message, boolean cancelable, OnCancelListener cancelListener) {  
        CustomProgress dialog = new CustomProgress(context, R.style.Custom_Progress);  
        dialog.setTitle("");  
        dialog.setContentView(R.layout.progress_custom);  
        if (message == null || message.length() == 0) {  
            dialog.findViewById(R.id.message).setVisibility(View.GONE);  
        } else {  
            TextView txt = (TextView) dialog.findViewById(R.id.message);  
            txt.setText(message);  
        }  
        //设置对话框是否可消除
        dialog.setCancelable(cancelable);  
        //设置删除后监听事件   
        dialog.setOnCancelListener(cancelListener);  
        // 设置窗体位置  中心
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;  
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
        // 设置背景透明度
//      dimAmount在0.0f和1.0f之间，0.0f完全不暗，即背景是可见的 ，1.0f时候，背景全部变黑暗。
//
//        如果要达到背景全部变暗的效果，需要设置  dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        否则，背景无效果。
        lp.dimAmount = 0.6f;  
        dialog.getWindow().setAttributes(lp);  
        // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);  
        dialog.show();  
        return dialog;  
    }
}  


