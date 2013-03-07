package com.refnil.uqcard.event;

public class PutCardEvent extends Event{

	private static final long serialVersionUID = 50728585326483695L;
	private int card;

	public PutCardEvent(int card) {
		super(Event_Type.PUT_CARD);
		setCard(card);
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}
	

}
