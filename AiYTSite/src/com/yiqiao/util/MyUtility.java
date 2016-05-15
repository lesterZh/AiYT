package com.yiqiao.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MyUtility {
	
	//解决listview 嵌套中显示不全的问题
	 public static void setListViewHeightBasedOnChildren(ListView listView) {  
	        ListAdapter listAdapter = listView.getAdapter();  
	        if (listAdapter == null) {  
	            return;  
	        }  
	  
	        int totalHeight = 0;  
	        for (int i = 0; i < listAdapter.getCount(); i++) {  
	            View listItem = listAdapter.getView(i, null, listView);  
	            listItem.measure(0, 0);  
	            totalHeight += listItem.getMeasuredHeight();  
	        }  
	  
	        ViewGroup.LayoutParams params = listView.getLayoutParams();  
	        params.height = totalHeight  
	                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
	        listView.setLayoutParams(params);  
	    }  
	 
	 public static int getHeight(View view)//获得某组件的高度
	 {
		 int w =View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		 int h =View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		 view.measure(w, h);
		 return view.getMeasuredHeight();
	 }

}
