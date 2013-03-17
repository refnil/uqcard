package com.refnil.uqcard.event;

import com.refnil.uqcard.view.CardView;
import android.view.View;
import android.view.View.OnClickListener;

public class CardViewOpponentOnClickListener implements OnClickListener {

	EventManager em;
	public CardViewOpponentOnClickListener(EventManager em)
	{
		this.em = em;
	}
	public void onClick(View v) {
		if(v instanceof CardView && em != null)
			em.setSelectedCard(((CardView)v).getCard().getUid(), true);
	}

}