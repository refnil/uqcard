package com.refnil.uqcard;

import android.view.View;
import android.view.View.OnClickListener;

public class CardViewOpponentOnClickListener implements OnClickListener {

	EventManager em;
	CardViewOpponentOnClickListener(EventManager em)
	{
		this.em = em;
	}
	public void onClick(View v) {
		CardView cv = (CardView) v;
		em.sendEventToPlayer(new SelectedCardEvent(Event_Type.SELECT_OPPONENT_CARD,cv.getCard()));
	}

}
