package com.refnil.uqcard;

public class SelectedCardEvent extends Event{

	CardView card;
	SelectedCardEvent(Event_Type t,CardView card) {
		super(t);
		this.card = card;
	}

}
