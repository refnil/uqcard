package com.refnil.uqcard.event;

import java.util.LinkedList;
import java.util.List;

import android.os.RemoteException;
import android.util.Log;

import com.refnil.uqcard.data.Board;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.library.Player;

public class EventManager {

	private Player p;
	private int selectedCard = -1;
	private int selectedCardHand;
	private int selectedCardHandUID;
	private List attacks = new LinkedList();

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
	
	public void clearAttacks()
	{
		attacks.clear();
	}
	
	public boolean canAttack()
	{
		for(int i=0;i<attacks.size();i++)
			if(Integer.parseInt(attacks.get(i).toString()) == this.selectedCard)
			{
				this.selectedCard = -1;
				return false;
			}
		return true;
	}
	
	public void addCardToAttack()
	{
		this.attacks.add(this.selectedCard);
	}

	public int getSelectedCard() {
		return selectedCard;
	}

	public boolean setSelectedCard(int selectedCard,boolean opponent) {
		
		if(this.getSelectedCard() != -1 && opponent)
		{
			
			
			Board b = this.p.getBoard();
			Card[] cards = b.getOpponentBoardCards();
			
			
			
			//adjusting reversed opponent grid
			if(selectedCard >=6)
			{
				selectedCard = selectedCard -6;
			}
			else
			{
				selectedCard = selectedCard +6;
			}
			
			Card sCard =  cards[selectedCard];
			int carduid = sCard.getUid();
			
			Log.i("click", "pew pew");
			this.sendToPlayer(new AttackEvent(carduid,this.getSelectedCard()));	
			this.addCardToAttack();
			this.selectedCard = -1;
		}
		else if (this.getSelectedCard() == -1 && !opponent)
		{
			Card[] cards = this.p.getBoard().getPlayerBoardCards();
			int carduid = cards[selectedCard].getUid();
			this.selectedCard = carduid;
			return this.canAttack();
		}

		return true;
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