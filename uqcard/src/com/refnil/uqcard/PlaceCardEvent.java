package com.refnil.uqcard;

public class PlaceCardEvent extends Event{

	private CardView cardPlace;
	private int idPosition;
	
	PlaceCardEvent(Event_Type t,CardView card,int position) {
		super(t);
		this.setCardPlace(card);
		this.setIdPosition(position);
	}
	public CardView getCardPlace() {
		return cardPlace;
	}
	public void setCardPlace(CardView cardPlace) {
		this.cardPlace = cardPlace;
	}
	public int getIdPosition() {
		return idPosition;
	}
	public void setIdPosition(int idPosition) {
		this.idPosition = idPosition;
	}

}
