package com.refnil.uqcard;

import com.refnil.uqcard.event.EventManager;
import com.refnil.uqcard.event.Event_Type;
import com.refnil.uqcard.event.SelectedCardEvent;
import com.refnil.uqcard.view.CardView;

import android.view.View;
import android.view.View.OnClickListener;

public class CardViewOpponentOnClickListener implements OnClickListener {

	EventManager em;
	CardViewOpponentOnClickListener(EventManager em)
	{
		this.em = em;
	}
	public void onClick(View v) {
		em.sendEventToEventManager(new SelectedCardEvent(Event_Type.SELECTED_OPPONENT_CARD,(CardView)v));
	}

}