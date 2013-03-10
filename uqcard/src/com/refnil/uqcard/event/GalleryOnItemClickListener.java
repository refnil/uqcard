package com.refnil.uqcard.event;

import com.refnil.uqcard.FullCardActivity;
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

	public GalleryOnItemClickListener(Context c, Gallery g) {
		context = c;
		gallery = g;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(context, FullCardActivity.class);
		CardView cv = (CardView) ((ImageAdapter)gallery.getAdapter()).getItem(gallery.getSelectedItemPosition());
		Card card = cv.getCard();
		i.putExtra("Card", card);
		((Activity) context).startActivity(i);
	}

}
