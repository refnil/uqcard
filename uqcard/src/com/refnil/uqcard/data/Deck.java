package com.refnil.uqcard.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;

public class Deck implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497696027546651007L;

	public static final String DEFAUT_SAVE_PATH = "Deck.save";

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

	public Card CardAt(int index) {
		return cards.get(index);
	}

	public Card pop() {
		return cards.remove(0);
	}

	public static Deck createDeck(CardStore cs) {

		LinkedList<Integer> listCard = new LinkedList<Integer>();
		for (int i = 0; i < 20; i++) {
			listCard.add(0);
			listCard.add(1);
		}
		return createSpecificDeck(cs, listCard, "DEFAUT");
	}

	public static Deck createSpecificDeck(CardStore cs,
			Iterable<Integer> listCard, String name) {
		ArrayList<CreatureCard> cards = new ArrayList<CreatureCard>(40);
		for (Integer i : listCard) {
			cards.add(cs.getCard(i));
		}
		return new Deck(cards, name);
	}

	public static Deck createDeckFromStream(CardStore cs, InputStream is)
			throws IOException {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
		String name = bfr.readLine();
		LinkedList<Integer> listCard = new LinkedList<Integer>();
		String reading;

		while ((reading = bfr.readLine()) != null)
			listCard.add(Integer.valueOf(reading));
		return createSpecificDeck(cs, listCard, name);

	}

	public void save(OutputStream os) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
		bw.write(deckName);
		bw.newLine();
		for (Card c : cards) {
			bw.write(String.valueOf(c.get_Id()));
			bw.newLine();
		}
	}

}
