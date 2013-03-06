package com.refnil.uqcard.event;

import com.refnil.uqcard.view.CardView;

public class SelectedCardEvent extends Event{
	
	private static final long serialVersionUID = -1388745581161354890L;
	private CardView card;
	
	public SelectedCardEvent(Event_Type t, CardView c)
	{
		super(t);
		setCard(c);
	}

	public CardView getCard() {
		return card;
	}

	public void setCard(CardView card) {
		this.card = card;
	}
}
