package com.refnil.uqcard.data.test;

import java.util.ArrayList;
import java.util.Vector;

import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.Deck;

public class DeckTest extends Deck{

	public DeckTest()
	{
		super(createDeck(),"Test");

	}

	public static ArrayList<Card> createDeck()
	{
		ArrayList<Card> cards = new ArrayList<Card>(40);
		for(int i =0;i<40;i++)
		{
			cards.add(new Card(0, "test"+i, "test test description"+i, "test test flavor"+i, i, null));
		}
		return cards;
	}



}