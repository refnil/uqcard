package com.refnil.uqcard.event;

public class PutCardEvent extends Event{

	private static final long serialVersionUID = 50728585326483695L;
	private int card;
	private int position;
	private boolean opponent;

	public PutCardEvent(int card,int position, boolean opponent) {
		super(Event_Type.PUT_CARD);
		setCard(card);
		setPosition(position);
		setOpponent(opponent);
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isOpponent() {
		return opponent;
	}

	public void setOpponent(boolean opponent) {
		this.opponent = opponent;
	}
	

}
