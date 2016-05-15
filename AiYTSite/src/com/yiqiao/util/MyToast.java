package com.yiqiao.util;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
	public static void toast(Context context, String text,int type) {
		if (type == 1) {
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		} else if (type == 2) {
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}
		
	}
}
