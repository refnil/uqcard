package com.refnil.uqcard.event;

import com.refnil.uqcard.data.Card;

public class PutCardEvent extends Event{

	private static final long serialVersionUID = 50728585326483695L;
	private int cardID;
	private int cardUID;
	private int position;

	public PutCardEvent(Card card,int position) {
		super(Event_Type.PUT_CARD);
		setCardID(card.get_Id());
		setCardUID(card.getUid());
		setPosition(position);
	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int card) {
		this.cardID = card;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getCardUID() {
		return cardUID;
	}

	public void setCardUID(int cardUID) {
		this.cardUID = cardUID;
	}
	

}
