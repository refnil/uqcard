package com.refnil.uqcard.event;

import com.refnil.uqcard.TabsActivity;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.view.CardView;
import com.refnil.uqcard.view.ImageAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;

public class GalleryOnItemClickListener implements OnItemClickListener {

	Context context;
	Gallery gallery;
	TabsActivity activity;

	public GalleryOnItemClickListener(TabsActivity a) {
		activity = a;
		Log.i("click", "constructeur");
	}

	public void onItemClick(AdapterView<?> adapter, View v, int arg2, long arg3) {
		ImageAdapter a = (ImageAdapter) adapter.getAdapter();
		CardView vv = (CardView) a.getItem(adapter.getSelectedItemPosition());
		Log.i("itemclick", vv.getCard().getDescription());
			
		
		//((TabsActivity) activity).startFullCardFragment(vv);
	}

}
