package com.refnil.uqcard;

import com.refnil.uqcard.library.Player;

public class EventManager {

	private GameConditionEvent gce;
	private TurnPhaseEvent tpe;
	private Player p;

	public EventManager(Player p) {
		this.gce = new GameConditionEvent(Event_Type.BEGIN_GAME);
		this.tpe = new TurnPhaseEvent(Event_Type.BEGIN_TURN);
		this.p = p;
	}

	public void sendPhaseToPlayer(GameConditionEvent gce) {
		p.sendEvent(gce);
	}

	public void sendPhaseToPlayer(TurnPhaseEvent tpe) {
		p.sendEvent(tpe);
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
