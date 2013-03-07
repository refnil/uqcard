package com.refnil.uqcard.event;

import com.refnil.uqcard.view.CardView;

public class DrawCardEvent extends Event{
	CardView card;

	public DrawCardEvent(CardView c) {
		super(Event_Type.DRAW_CARD);
		this.card = c;
	}

	private static final long serialVersionUID = -4766338023601191411L;

}
