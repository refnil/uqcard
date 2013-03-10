package com.refnil.uqcard;

import java.util.Vector;

public class Deck {
	private	Vector<Card> cards;
	
	public Deck(Vector<Card> cards) {
		this.cards = cards;
	}
	
	public Card getCardAt(int index)
	{
		return cards.get(index);
	}
}
