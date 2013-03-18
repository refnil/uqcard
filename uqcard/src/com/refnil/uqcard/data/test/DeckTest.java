package com.refnil.uqcard.data.test;

import java.util.ArrayList;
import java.util.Vector;

import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.CardStore;
import com.refnil.uqcard.data.Deck;
import com.refnil.uqcard.data.DummyCardStore;

public class DeckTest extends Deck{
	public static DummyCardStore cardStore = new DummyCardStore();

	public DeckTest()
	{
		super(createDeck(),"Test");
	}

	public static ArrayList<Card> createDeck()
	{
		ArrayList<Card> cards = new ArrayList<Card>(40);
		for(int i =0;i<40;i++)
		{
			cards.add(cardStore.getCard(0));
		}
		return cards;
	}



}