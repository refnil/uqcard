package com.refnil.uqcard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context ctx;
	int imageBackground;
	Card carte;
	Integer[] pics = {R.drawable.carreau,R.drawable.coeur,R.drawable.trefle,R.drawable.pique};
	
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
		Card myCard = new Card(ctx, "chat", "chat","chat", 2);
		View v = myCard.getCardView(ctx);
		Bitmap viewCapture = null;
		v.setDrawingCacheEnabled(true);
		viewCapture = Bitmap.createBitmap(v.getDrawingCache());
		v.setDrawingCacheEnabled(false);
		
		ImageView iv = new ImageView(ctx);
		iv.setImageBitmap(viewCapture);
		
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		iv.setLayoutParams(new Gallery.LayoutParams(150,120));
		iv.setBackgroundResource(imageBackground);
		return iv;
	}
	

}
