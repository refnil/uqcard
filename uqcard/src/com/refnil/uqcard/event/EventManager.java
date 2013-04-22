package com.refnil.uqcard.event;

import android.os.RemoteException;
import android.util.Log;

import com.refnil.uqcard.library.Player;

public class EventManager {

	private Player p;
	private int selectedCard;
	private int selectedCardHand;
	private int selectedCardHandUID;

	public EventManager(Player p) {
		this.p = p;
	}
	
	public void sendToPlayer(Event event) {
		try {
			Log.i("eventmanager", "send to player ");
			p.sendEvent(event);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public BoardActivity getBoard() {
		return board;
	}

	public void setBoard(BoardActivity board) {
		this.board = board;
	}*/

	public int getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(int selectedCard,boolean opponent) {
		Log.i("click", "in selectedEvent");
		if(this.getSelectedCard() != -1 && opponent)
		{
			Log.i("click", "pew pew");
			this.sendToPlayer(new AttackEvent(this.getSelectedCard(),selectedCard));	
			this.selectedCard = -1;
		}
		else
		{
			Log.i("click", "no pew pew");
			this.selectedCard = selectedCard;
		}
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