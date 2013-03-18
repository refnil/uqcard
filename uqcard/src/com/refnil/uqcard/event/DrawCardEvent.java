package com.refnil.uqcard.event;

public class DrawCardEvent extends Event{


	private static final long serialVersionUID = -4766338023601191411L;
	private int cardID;
	private int cardUID;

	public DrawCardEvent(int id,int uid) {
		super(Event_Type.DRAW_CARD);
		this.setCardID(id);
		this.setCardUID(uid);
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
