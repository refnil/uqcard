package com.refnil.uqcard.library.message;

import com.refnil.uqcard.Event;

public class RequestServer extends UqcardMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5782595459508270076L;
	final public Event event;
	final public int playerId;
	public RequestServer(int playerId, Event event){
		this.event = event;
		this.playerId = playerId;
	}
}
