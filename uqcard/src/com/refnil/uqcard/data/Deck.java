package com.refnil.uqcard.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497696027546651007L;
	private ArrayList<Card> cards;
	private String deckName;

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public Deck(ArrayList<Card> cards, String deckName) {
		this.cards = cards;
		this.deckName = deckName;
	}

	public Card CardAt(int index) {
		return cards.get(index);
	}

	public Card pop() {
		return cards.remove(0);
	}

	public static Deck createDeck(CardStore cs){
		
		ArrayList<Card> cards = new ArrayList<Card>(40);
		for (int i = 0; i < 40; i++) {
			cards.add(cs.getCard(i%2));
		}
		return new Deck(cards, "TEST");
	}
}