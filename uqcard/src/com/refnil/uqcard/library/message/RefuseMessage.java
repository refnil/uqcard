package com.refnil.uqcard.library.message;

public class RefuseMessage extends UqcardMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7872342080949642708L;

	final public int eventId;
	final public RequestServer request;
	final public RefuseReason reason;

	public RefuseMessage(int eventId, RequestServer request, RefuseReason reason) {
		this.eventId = eventId;
		this.request = request;
		this.reason = reason;
	}

}
