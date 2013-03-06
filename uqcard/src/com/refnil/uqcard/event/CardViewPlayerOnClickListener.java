package com.refnil.uqcard.event;

import com.refnil.uqcard.view.CardView;

import android.view.View;
import android.view.View.OnClickListener;

public class CardViewPlayerOnClickListener implements OnClickListener {

	EventManager em;
	

	CardViewPlayerOnClickListener(EventManager em) {
		this.em = em;
	}

	public void onClick(View v) {
		em.sendEventToEventManager(new SelectedCardEvent(Event_Type.SELECTED_PLAYER_CARD,(CardView) v));
	}

}