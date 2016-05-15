package com.yiqiao.ui;

import com.yiqiao.aiytsite.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ToastFormatActivity extends Toast{
	 
	  
    public ToastFormatActivity(Context context) {  
        super(context);  
    }  
      
    public static Toast makeText(Context context,int resIconId, CharSequence text, int duration) {  
        Toast result = new Toast(context);  
          
        //获取LayoutInflater对象  
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
        //由layout文件创建一个View对象  
        View layout = inflater.inflate(R.layout.toast_format, null);  
          
        //实例化ImageView和TextView对象  
        ImageView imgToastIcon = (ImageView) layout.findViewById(R.id.toast_icon);
        TextView tvToast = (TextView) layout.findViewById(R.id.toast_message);  
          
        imgToastIcon.setImageResource(resIconId);  
        tvToast.setText(text);  
          
        result.setView(layout);  
      /*  result.setGravity(Gravity.BOTTOM, 0, 0);  
        result.setDuration(duration);  */
          
        return result;  
    }  
  
}  
