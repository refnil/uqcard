package com.refnil.uqcard.library.message;

import com.refnil.uqcard.Event;

public class RequestServer extends UqcardMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5782595459508270076L;
	final public Event event;
	public RequestServer(Event event){
		this.event = event;
	}
}
