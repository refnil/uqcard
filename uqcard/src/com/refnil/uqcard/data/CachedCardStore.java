package com.refnil.uqcard.data;

import android.util.SparseArray;

public class CachedCardStore implements CardStore {

	private CardStore cs;
	private SparseArray<Card> cardMap = new SparseArray<Card>();

	public CachedCardStore(CardStore cs) {

		this.cs = cs;
	}

	public Card getCard(int id,CardType type) {
		// TODO Auto-generated method stub
		Card c = cardMap.get(id);
		if (c == null) {
			c = cs.getCard(id, type);
			cardMap.append(id, c);
		}
		return c;
	}

}
