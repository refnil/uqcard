package com.refnil.uqcard.library.message;

public class ConnectedPlayer extends UqcardMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2084877885113798383L;
	final public String name;
	public ConnectedPlayer(String name){
		this.name = name;
	}

}
