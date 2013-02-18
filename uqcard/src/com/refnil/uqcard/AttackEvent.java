package com.refnil.uqcard;

import java.io.Serializable;

public class AttackEvent extends Event{

	private static final long serialVersionUID = 4417563050991299544L;
	
	CardView opponent;
	CardView player;
	AttackEvent(CardView opp, CardView pl)
	{
		super(Event_Type.DECLARE_ATTACK);
		this.opponent = opp;
		this.player = pl;
	}
}
