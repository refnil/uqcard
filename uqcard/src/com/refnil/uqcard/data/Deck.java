package com.refnil.uqcard.data;

import java.util.ArrayList;

import com.refnil.uqcard.data.test.DeckTest;

public class Deck {
	private	ArrayList<Card> cards;
	private String deckName;

	public Deck(ArrayList<Card> cards,String deckName) {
		this.cards = cards;
		this.deckName = deckName;
	}
	
	public Deck()
	{
		this.cards = DeckTest.createDeck();
		this.deckName = "TEST";
	}

	public Card drawCardAt(int index)
	{
		return cards.remove(index);
	}
}