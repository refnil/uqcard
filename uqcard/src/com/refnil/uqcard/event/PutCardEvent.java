package com.refnil.uqcard.event;

public class PutCardEvent extends Event{

	private static final long serialVersionUID = 50728585326483695L;
	private int card;
	private int position;

	public PutCardEvent(int card,int position) {
		super(Event_Type.PUT_CARD);
		setCard(card);
		setPosition(position);
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
	

}
