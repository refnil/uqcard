package com.refnil.uqcard;

import java.util.Vector;

public class DeckTest extends Deck{
	
	public DeckTest()
	{
		super(createDeck());

	}
	
	public static Vector<Card> createDeck()
	{
		Vector<Card> cards = new Vector<Card>(40);
		for(int i =0;i<40;i++)
		{
			cards.add(new Card(0, "test"+i, "test test description"+i, "test test flavor"+i, i, null));
		}
		return cards;
	}
	
	

}
