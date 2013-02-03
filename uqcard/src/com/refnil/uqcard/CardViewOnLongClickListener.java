package com.refnil.uqcard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnLongClickListener;

public class CardViewOnLongClickListener implements OnLongClickListener {

	Context context;

	public CardViewOnLongClickListener(Context c) {
		this.context = c;
	}

	public boolean onLongClick(View v) {
		CardView iv = (CardView) v;
		if (iv.getCard() != null) {
			Intent i = new Intent(context, FullCardActivity.class);
			i.putExtra("Card", iv.getCard());
			((Activity) context).startActivity(i);
		}
		return true;
	}

}
