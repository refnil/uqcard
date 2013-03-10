package com.refnil.uqcard.library.message;

import java.util.ArrayList;

public abstract class DeckMessage extends UqcardMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3653790192122928919L;
	public ArrayList<Integer> getDeck()
	{
		ArrayList<Integer> deck = new ArrayList<Integer>();
		recursiveDeck(deck);
		return deck;
	}
	
	protected abstract  void recursiveDeck(ArrayList<Integer> deck);
}
