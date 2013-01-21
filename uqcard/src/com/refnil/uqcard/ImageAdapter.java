package com.refnil.uqcard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.View.MeasureSpec;
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
		Card myCard = new Card(ctx, "nom", "desc","flav", 2);

		
		View v = myCard.getCardView(ctx,150,120);
		
		Bitmap viewCapture = loadBitmapFromView(v);
		ImageView iv = new ImageView(ctx);
		
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		iv.setLayoutParams(new Gallery.LayoutParams(150,120));
		iv.setBackgroundResource(imageBackground);
		iv.setImageBitmap(viewCapture);
		return iv;
	}
	
	public static Bitmap loadBitmapFromView(View v) {
		Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.RGB_565);     
	    Canvas c = new Canvas(b);
	    v.measure(MeasureSpec.makeMeasureSpec(v.getLayoutParams().width, MeasureSpec.EXACTLY),
	            MeasureSpec.makeMeasureSpec(v.getLayoutParams().height, MeasureSpec.EXACTLY));
	    v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
	    v.draw(c);
	    return b;
	}
	

}
