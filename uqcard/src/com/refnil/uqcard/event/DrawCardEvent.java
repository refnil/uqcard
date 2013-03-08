package com.refnil.uqcard.event;

public class DrawCardEvent extends Event{


	private static final long serialVersionUID = -4766338023601191411L;
	private int card;

	public DrawCardEvent(int c) {
		super(Event_Type.DRAW_CARD);
		this.setCard(c);
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}

}
