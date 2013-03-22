package com.refnil.uqcard.event;

import com.refnil.uqcard.TabsActivity;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.view.CardView;
import com.refnil.uqcard.view.ImageAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;

public class GalleryOnItemClickListener implements OnItemClickListener {

	Context context;
	Gallery gallery;
	TabsActivity activity;

	public GalleryOnItemClickListener(TabsActivity a) {
		activity = a;
	}

	public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
		if(v instanceof CardView)
		{
			CardView iv = (CardView) v;
			((TabsActivity) activity).startFullCardFragment(iv);
		}
	}

}
