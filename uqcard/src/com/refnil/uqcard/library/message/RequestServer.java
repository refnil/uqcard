package com.refnil.uqcard.library.message;

import com.refnil.uqcard.Event;

public class RequestServer extends UqcardMessage {

	final public Event event;
	public RequestServer(Event event){
		this.event = event;
	}
}
