package com.refnil.uqcard;

public class EventManager {

	private GameConditionEvent gce;
	private TurnPhaseEvent tpe;

	public EventManager() {
		this.gce = (GameConditionEvent) new Event(Event_Type.BEGIN_GAME);
		this.tpe = (TurnPhaseEvent) new Event(Event_Type.BEGIN_TURN);
	}

	public void sendPhaseToPlayer(GameConditionEvent gce) {
		Player.SendEvent(this.gce);
	}

	public void sendPhaseToPlayer(TurnPhaseEvent tpe) {
		Player.SendEvent(this.tpe);
	}
}