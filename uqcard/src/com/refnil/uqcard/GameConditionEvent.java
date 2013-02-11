package com.refnil.uqcard;

public class GameConditionEvent extends Event {

	public Event_Type type;

	GameConditionEvent(Event_Type t) {
		super(t);
		type = Event_Type.BEGIN_GAME;
	}

	public void nextPhase() {
		switch (type) {
		case BEGIN_GAME:
			type = Event_Type.END_GAME;
			break;
		case END_GAME:
			type = Event_Type.BEGIN_GAME;
			break;
		default:
			break;
 			
 		}
 	}
 	
 	public void setPhase(Event_Type t)
 	{
 		type = t;
 	} 	
}
