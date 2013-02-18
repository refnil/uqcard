package com.refnil.uqcard;

public class TurnPhaseEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -686978529918001244L;
	public Event_Type type;

	TurnPhaseEvent(Event_Type t) {
		super(t);
		type = Event_Type.BEGIN_TURN;
	}

	public void nextPhase() {
		switch (type) {
		case BEGIN_TURN:
			type = Event_Type.END_TURN;
			break;
		case END_TURN:
			type = Event_Type.BEGIN_TURN;
			break;
		default:
			break;
		}
	}
}