package com.refnil.uqcard.library.message;

import com.refnil.uqcard.Event;

public class EventMessage extends UqcardMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4473419025165360028L;
	final public Event event;

	public EventMessage(Event event) {
		this.event = event;
	}

}
