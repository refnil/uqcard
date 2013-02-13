package com.refnil.uqcard;

public class EventManager {

	private GameConditionEvent gce;
	private TurnPhaseEvent tpe;
	private CardView selectedCard = null;
	private boolean selectedCardFromHand = false;
	private Board board;

	public EventManager(Board board) {
		this.gce = new GameConditionEvent(Event_Type.BEGIN_GAME);
		this.tpe = new TurnPhaseEvent(Event_Type.BEGIN_TURN);
		this.board = board;
	}
	
	public void receiveEvent(Event e)
	{
		if(e instanceof SelectedCardEvent)
		{
			if(selectedCard == null)
			{
				if(e.type == Event_Type.SELECT_PLAYER_CARD)
					selectedCard = ((SelectedCardEvent) e).card;
				
				//TO DO FOR CARD SELECTED FROM HAND
			}
			else
			{
				if(e.type == Event_Type.SELECT_OPPONENT_CARD && selectedCardFromHand == false)
				{
					sendEventToPlayer(new CreatureAttackEvent(Event_Type.DECLARE_ATTACK,this.selectedCard,((SelectedCardEvent) e).card));
					
				}
			}
		}
	}
	
	public void sendEventToPlayer(Event e)
	{
		Player.SendEvent(e);
	}

	public void sendPhaseToPlayer(GameConditionEvent gce) {
		Player.SendEvent(this.gce);
	}

	public void sendPhaseToPlayer(TurnPhaseEvent tpe) {
		Player.SendEvent(this.tpe);
	}
	
	public GameConditionEvent getGameConditionEvent()
	{
		return this.gce;
	}
	
	public TurnPhaseEvent getTurnPhaseEvent()
	{
		return this.tpe;
	}
}
