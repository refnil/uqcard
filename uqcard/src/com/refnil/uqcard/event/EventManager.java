package com.refnil.uqcard.event;

import android.os.RemoteException;
import android.util.Log;

import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.library.Player;

public class EventManager {

	private Player p;
	private int selectedCard;
	private int selectedCardHand;
	private int selectedCardHandUID;

	public EventManager(Player p) {
		this.p = p;
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	public void sendToPlayer(Event event) {
		try {
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
		
		if(this.getSelectedCard() != -1 && opponent)
		{
			Card[] cards = this.p.getBoard().getOpponentBoardCards();
			int carduid = cards[selectedCard].getUid();
			
			Log.i("click", "pew pew");
			this.sendToPlayer(new AttackEvent(carduid,this.getSelectedCard()));	
			this.selectedCard = -1;
		}
		else
		{
			Card[] cards = this.p.getBoard().getPlayerBoardCards();
			int carduid = cards[selectedCard].getUid();
			this.selectedCard = carduid;
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