package com.refnil.uqcard;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	private Context ctx;
	int imageBackground;
	Integer[] pics = {
            R.drawable.android_logo, R.drawable.blackberry_logo,
            R.drawable.ios_logo, R.drawable.windows_logo};   
	
	public ImageAdapter(Context c) {
		ctx = c;
		TypedArray ta = c.obtainStyledAttributes(R.styleable.Gallery1);
		imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
		ta.recycle();
	}
	
	public int getCount() {
		
		return pics.length;
	}

	
	public Object getItem(int arg0) {
		return arg0;
	}

	
	public long getItemId(int arg0) {
		long id = (long) pics[arg0];
		return id;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ImageView iv = new ImageView(ctx);
		iv.setImageResource(pics[arg0]);
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		iv.setLayoutParams(new Gallery.LayoutParams(150,120));
		iv.setBackgroundResource(imageBackground);
		return iv;
	}

}
