package com.refnil.uqcard.event;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.Deck;

public class SendDeckEvent extends Event{
	private int player;
	private Deck decklist;
	public SendDeckEvent(int player,Deck decklist) {
		super(Event_Type.SEND_DECK);
		this.setPlayer(player);
		this.setDecklist(decklist);

		// TODO Auto-generated constructor stub
	}
	public Deck getDecklist() {
		return decklist;
	}
	public void setDecklist(Deck decklist) {
		this.decklist = decklist;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}

}
