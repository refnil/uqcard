package com.refnil.uqcard.view;

import com.refnil.uqcard.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ImageAdapter extends BaseAdapter {

	private Context ctx;
	int imageBackground;
	CardView pics[];

	public ImageAdapter(Context c,CardView tab[]) {
		ctx = c;
		TypedArray ta = c.obtainStyledAttributes(R.styleable.Gallery1);
		imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
		pics = tab;
		ta.recycle();
	}

	public int getCount() {

		return pics.length;
	}

	public Object getItem(int pos) {
		return pics[pos];
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
			WindowManager wm = (WindowManager) ctx
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			return pics[arg0].getCardImageView(ctx, size.x, size.y);
	}

	public CardView getCardView(int arg0, View arg1, ViewGroup arg2) {
		return (CardView)arg1;

	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
