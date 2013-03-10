package com.refnil.uqcard.event;

public class AttackEvent extends Event{

	private static final long serialVersionUID = 4417563050991299544L;
	
	private int opponent;
	private int player;
	private boolean yourAttack;
	
	AttackEvent(int opp, int pl)
	{
		super(Event_Type.DECLARE_ATTACK);
		this.setOpponent(opp);
		this.setPlayer(pl);
	}
	public int getOpponent() {
		return opponent;
	}
	public void setOpponent(int opponent) {
		this.opponent = opponent;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public boolean isYourAttack() {
		return yourAttack;
	}
	public void setYourAttack(boolean yourAttack) {
		this.yourAttack = yourAttack;
	}
}
