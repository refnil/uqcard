package com.refnil.uqcard;

public class EventManager {

	private GameConditionEvent gce;
	private TurnPhaseEvent tpe;

	public EventManager() {
		this.gce = new GameConditionEvent(Event_Type.BEGIN_GAME);
		this.tpe = new TurnPhaseEvent(Event_Type.BEGIN_TURN);
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
