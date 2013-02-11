package com.refnil.uqcard.library.message;

public class IdMessage extends UqcardMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4875204914086323026L;
	final public UqcardMessage message;
	final public int id;

	public IdMessage(int id, UqcardMessage m) {
		this.id = id;
		this.message = m;
	}
}