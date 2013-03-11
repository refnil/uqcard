package com.refnil.uqcard.event;

import com.refnil.uqcard.data.Card;

public class DrawCardEvent extends Event{


	private static final long serialVersionUID = -4766338023601191411L;
	private int cardID;
	private int cardUID;

	public DrawCardEvent(Card c) {
		super(Event_Type.DRAW_CARD);
		this.setCardID(c.get_Id());
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int card) {
		this.cardID = card;
	}

	public int getCardUID() {
		return cardUID;
	}

	public void setCardUID(int cardUID) {
		this.cardUID = cardUID;
	}

}
