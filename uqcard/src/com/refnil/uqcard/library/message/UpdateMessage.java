package com.refnil.uqcard.library.message;

public class UpdateMessage extends UqcardMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2911355102824234269L;
	
	final public int eventId;
	
	public UpdateMessage(int eventId)
	{
		this.eventId = eventId;
	}

}
