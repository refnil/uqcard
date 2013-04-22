package com.refnil.uqcard.data.test;

import java.util.ArrayList;
import java.util.Vector;

import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.CardStore;
import com.refnil.uqcard.data.CreatureCard;
import com.refnil.uqcard.data.Deck;
import com.refnil.uqcard.data.DummyCardStore;

public class DeckTest extends Deck{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2114504418021401695L;
	public static DummyCardStore cardStore = new DummyCardStore();

	public DeckTest()
	{
		super(createDeck(),"Test");
	}

	public static ArrayList<CreatureCard> createDeck()
	{
		ArrayList<CreatureCard> cards = new ArrayList<CreatureCard>(40);
		for(int i =0;i<40;i++)
		{
			cards.add((CreatureCard)cardStore.getCard(1));
		}
		return cards;
	}



}