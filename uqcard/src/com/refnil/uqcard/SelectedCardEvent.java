package com.refnil.uqcard;

import java.io.Serializable;

public class SelectedCardEvent extends Event implements Serializable{
	
	private static final long serialVersionUID = -1388745581161354890L;
	CardView card;
	
	SelectedCardEvent(Event_Type t, CardView c)
	{
		super(t);
		card = c;
	}
}
