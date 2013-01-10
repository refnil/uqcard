package com.refnil.uqcard;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter{
	private Context mContext;
	public Integer[] mThumbIds = {
            R.drawable.android_logo, R.drawable.blackberry_logo,
            R.drawable.ios_logo, R.drawable.windows_logo};
	
	public ImageAdapter(Context context)
	{
		mContext = context;
	}
	
	public int getCount() {
		 return mThumbIds.length;
	}

	public Object getItem(int position) {
		return mThumbIds[position];
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
	}
	
	/*public void addImage(int id)
	{
		images.add(id);
	}
	
	public void removeImage(int id)
	{
		int index = images.indexOf(id);
		images.remove(index);
	}*/

}
