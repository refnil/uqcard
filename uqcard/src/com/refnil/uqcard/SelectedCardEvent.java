package com.refnil.uqcard;

public class SelectedCardEvent extends Event{
	
	private static final long serialVersionUID = -1388745581161354890L;
	CardView card;
	
	SelectedCardEvent(Event_Type t, CardView c)
	{
		super(t);
		card = c;
	}
}
