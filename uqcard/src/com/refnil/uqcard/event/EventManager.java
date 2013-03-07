package com.refnil.uqcard.event;

import android.os.RemoteException;
import com.refnil.uqcard.BoardActivity;
import com.refnil.uqcard.library.Player;

public class EventManager {

	private Player p;
	private BoardActivity board;
	private int selectedCard;

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
	
	public void sendEventToEventManager(Event e)
	{
		if(e instanceof SelectedCardEvent)
		{
			/*if(e.type == Event_Type.SELECTED_PLAYER_CARD)
			{
				setSelectedCard(((SelectedCardEvent)e).getCard());
				board.handleEvent(e);
			}
			else
			{
				AttackEvent ae = new AttackEvent(((SelectedCardEvent)e).getCard(),getSelectedCard());
				sendToPlayer(ae);
				selectedCard = -1;
			}*/
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

	public void setSelectedCard(int selectedCard) {
		this.selectedCard = selectedCard;
	}
}