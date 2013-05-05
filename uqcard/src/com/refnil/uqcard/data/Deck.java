package com.refnil.uqcard.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.refnil.uqcard.data.test.DeckTest;

public class Deck implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497696027546651007L;
	private	ArrayList<CreatureCard> cards;
	private String deckName;

	public ArrayList<CreatureCard> getCards() {
		return cards;
	}

	public void setCards(ArrayList<CreatureCard> cards) {
		this.cards = cards;
	}

	public Deck(ArrayList<CreatureCard> cards,String deckName) {
		this.cards = cards;
		this.deckName = deckName;
	}
	
	public Deck()
	{
		this.cards = DeckTest.createDeck();
		this.deckName = "TEST";
	}

	public Card CardAt(int index)
	{
		return cards.get(index);
	}
	
	public Card pop()
	{
		return cards.remove(0);
	}
}