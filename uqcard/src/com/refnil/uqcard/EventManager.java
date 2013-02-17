package com.refnil.uqcard;

import android.os.RemoteException;

import com.refnil.uqcard.library.Player;

public class EventManager {

	private GameConditionEvent gce;
	private TurnPhaseEvent tpe;
	private Player p;
	private BoardActivity board;
	private CardView selectedCard;

	public EventManager(Player p) {
		this.gce = new GameConditionEvent(Event_Type.BEGIN_GAME);
		this.tpe = new TurnPhaseEvent(Event_Type.BEGIN_TURN);
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
			if(e.type == Event_Type.SELECTED_PLAYER_CARD)
			{
				setSelectedCard(((SelectedCardEvent)e).card);
				board.handleEvent(e);
			}
			else
			{
				AttackEvent ae = new AttackEvent(((SelectedCardEvent)e).card,getSelectedCard());
				sendToPlayer(ae);
			}
		}
	}

	public GameConditionEvent getGameConditionEvent()
	{
		return this.gce;
	}
	
	public TurnPhaseEvent getTurnPhaseEvent()
	{
		return this.tpe;
	}

	public BoardActivity getBoard() {
		return board;
	}

	public void setBoard(BoardActivity board) {
		this.board = board;
	}

	public CardView getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(CardView selectedCard) {
		this.selectedCard = selectedCard;
	}
}