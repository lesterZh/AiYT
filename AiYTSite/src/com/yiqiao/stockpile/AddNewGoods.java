package com.yiqiao.stockpile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.yiqiao.activity.BaseActivity;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsChild;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyToast;

public class AddNewGoods extends BaseActivity{
	
	private static final int TAKE_PHOTO = 1,CROP_PHOTO = 2;
	
	private ImageView addGoodsImageView,photoImageView, showImageView;
	private EditText inputStock,inputPrice,inputColor,inputType;
	private Button okButton,cancleButton;
	String fileName = MyConstants.SD_ROOT_PATH+"my.jpg";
	File outputImage;
	Uri imageUrl;
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_classify_level2_add_new_goods);
		setTitle("添加新品种");
		
		mContext = this;
		
		initView();
	}

	
	private void initView() {
		//获取参数修改控件
		inputStock = (EditText) findViewById(R.id.stock_classify_level2_add_goods_stock_et);
		inputPrice = (EditText) findViewById(R.id.stock_classify_level2_add_goods_price_et);
		inputColor = (EditText) findViewById(R.id.stock_classify_level2_add_goods_color_et);
		inputType = (EditText) findViewById(R.id.stock_classify_level2_add_goods_type_et);
		
		//图片显示
		showImageView = (ImageView) findViewById(R.id.stock_classify_level2_add_goods_image_show);
		//拍照添加图片
		photoImageView = (ImageView) findViewById(R.id.stock_classify_level2_add_goods_photo_image);
		//相册添加图片
		addGoodsImageView = (ImageView) findViewById(R.id.stock_classify_level2_add_goods_add_image);
		
		okButton = (Button) findViewById(R.id.stock_classify_level2_add_goods_ok_btn);
		cancleButton = (Button) findViewById(R.id.stock_classify_level2_add_goods_cancle_btn);
		
		MyOnClickListener myOnClickListener = new MyOnClickListener();
		//注册监听事件
		okButton.setOnClickListener(myOnClickListener);
		cancleButton.setOnClickListener(myOnClickListener);
		addGoodsImageView.setOnClickListener(myOnClickListener);
		photoImageView.setOnClickListener(myOnClickListener);
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.stock_classify_level2_add_goods_photo_image:
				outputImage = new File(fileName);
				if (outputImage.exists()) {
					outputImage.delete();
				} else {
					try {
						System.out.println("creat:"+outputImage.createNewFile());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				imageUrl = Uri.fromFile(outputImage);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
				
				startActivityForResult(intent, TAKE_PHOTO);
						
				break;
				
			case R.id.stock_classify_level2_add_goods_add_image:
//				MyToast.toast(mContext, "add", 1);
				outputImage = new File(fileName);
				
				if (outputImage.exists()) {
					outputImage.delete();
				} else {
					try {
						System.out.println("creat:"+outputImage.createNewFile());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				imageUrl = Uri.fromFile(outputImage);
//				Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
//				intent2.setType("image/*");
//				intent2.putExtra("crop", true);
//				intent2.putExtra("scale", true);
//				intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
//				
//				startActivityForResult(intent2, TAKE_PHOTO);
				
				Intent getPictureIntent = new Intent(Intent.ACTION_PICK);
				getPictureIntent.setType("image/*");
				startActivityForResult(getPictureIntent, 3);
						
				break;
				
			case R.id.stock_classify_level2_add_goods_ok_btn:
//				MyToast.toast(mContext, "ok", 1);
				GoodsChild goodsChild = new GoodsChild();
				//获取新增参数值 ,将新增类传给上一个活动，提交服务器
				
				finish();
				break;
				
			case R.id.stock_classify_level2_add_goods_cancle_btn:
//				MyToast.toast(mContext, "cancle", 1);

				finish();
				break;

			default:
				break;
			}
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PHOTO :
			
			if (resultCode == RESULT_OK) {
				//Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUrl));
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUrl, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
				startActivityForResult(intent, CROP_PHOTO);
			}
			
			break;
			
		case CROP_PHOTO :
			
			if (resultCode == RESULT_OK) {
				//Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUrl));
				Bitmap bitmap = BitmapFactory.decodeFile(fileName);
				showImageView.setImageBitmap(bitmap);
			}
			break;
			
		case 3:
			if (resultCode == RESULT_OK) {
				if (data != null) {
					Uri uri = data.getData();
					showImageView.setImageURI(uri);
				}
			}
		}
	}
}
