package com.refnil.uqcard;

public class CreatureAttackEvent extends Event{

	CardView from;
	CardView to;
	CreatureAttackEvent(Event_Type t, CardView from, CardView to) {
		super(t);
		this.from = from;
		this.to = to;
	}

}
