package com.refnil.uqcard.event;

import android.os.RemoteException;
import com.refnil.uqcard.BoardActivity;
import com.refnil.uqcard.library.Player;

public class EventManager {

	private Player p;
	private BoardActivity board;
	private int selectedCard;
	private int selectedCardHand;
	private int selectedCardHandUID;

	public EventManager(Player p) {
		this.p = p;
	}
	
	public void sendToPlayer(Event event) {
		try {
			p.sendEvent(event);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BoardActivity getBoard() {
		return board;
	}

	public void setBoard(BoardActivity board) {
		this.board = board;
	}

	public int getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(int selectedCard,boolean opponent) {
		
		if(this.getSelectedCard() != -1 && opponent)
		{
			this.sendToPlayer(new AttackEvent(this.getSelectedCard(),selectedCard));	
			this.selectedCard = -1;
		}
		else
			this.selectedCard = selectedCard;
	}

	public int getSelectedCardHand() {
		return selectedCardHand;
	}

	public void setSelectedCardHand(int selectedCardHand) {
		this.selectedCardHand = selectedCardHand;
	}

	public int getSelectedCardHandUID() {
		return selectedCardHandUID;
	}

	public void setSelectedCardHandUID(int selectedCardHandUID) {
		this.selectedCardHandUID = selectedCardHandUID;
	}
}