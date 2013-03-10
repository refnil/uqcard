package com.refnil.uqcard.event;

import com.refnil.uqcard.view.CardView;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class CardViewPlayerOnClickListener implements OnClickListener {

	EventManager em;
	

	public CardViewPlayerOnClickListener(EventManager em) {
		this.em = em;
	}

	public void onClick(View v) {
		Log.i("playerlistener", "yarrr");
		//em.setSelectedCard(((CardView)v).getCard().getUid(), false);
	}

}