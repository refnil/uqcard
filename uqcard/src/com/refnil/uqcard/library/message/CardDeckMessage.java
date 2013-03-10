package com.refnil.uqcard.library.message;

import java.util.ArrayList;

public class CardDeckMessage extends DeckMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1501222062382688241L;

	private final int id;
	private final int number;
	private final DeckMessage dm;
	

	public CardDeckMessage(int id, int number, DeckMessage dm) {
		this.id = id;
		this.number = number;
		this.dm = dm;
	}

	@Override
	protected void recursiveDeck(ArrayList<Integer> deck) {
		// TODO Auto-generated method stub
		for (int i = 0; i < number; i++) {
			deck.add(id);
		}
		dm.recursiveDeck(deck);
	}

}
