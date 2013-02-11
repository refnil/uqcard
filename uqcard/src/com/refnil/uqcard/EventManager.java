package com.refnil.uqcard;

import android.os.RemoteException;

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

	public void sendToPlayer(Event event) {
		try {
			p.sendEvent(event);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}