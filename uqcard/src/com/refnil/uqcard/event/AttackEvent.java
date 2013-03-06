package com.refnil.uqcard.event;

import com.refnil.uqcard.CardView;

public class AttackEvent extends Event{

	private static final long serialVersionUID = 4417563050991299544L;
	
	private CardView opponent;
	private CardView player;
	AttackEvent(CardView opp, CardView pl)
	{
		super(Event_Type.DECLARE_ATTACK);
		this.setOpponent(opp);
		this.setPlayer(pl);
	}
	public CardView getOpponent() {
		return opponent;
	}
	public void setOpponent(CardView opponent) {
		this.opponent = opponent;
	}
	public CardView getPlayer() {
		return player;
	}
	public void setPlayer(CardView player) {
		this.player = player;
	}
}
