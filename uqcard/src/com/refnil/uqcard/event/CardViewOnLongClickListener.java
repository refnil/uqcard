package com.refnil.uqcard.event;

import com.refnil.uqcard.TabsActivity;
import com.refnil.uqcard.view.CardView;
import android.view.View;
import android.view.View.OnLongClickListener;

public class CardViewOnLongClickListener implements OnLongClickListener {

	TabsActivity activity;

	public CardViewOnLongClickListener(TabsActivity a) {
		this.activity = a;
	}

	public boolean onLongClick(View v) {
		if(v instanceof CardView)
		{
			CardView iv = (CardView) v;
			((TabsActivity) activity).startFullCardFragment(iv);

			return true;
		}
		else
			return false;
	}

}
