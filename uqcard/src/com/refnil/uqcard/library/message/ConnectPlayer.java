package com.refnil.uqcard.library.message;

public class ConnectPlayer extends UqcardMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7038095499822916808L;
	final public String name;

	public ConnectPlayer(String name) {
		this.name = name;
	}
}