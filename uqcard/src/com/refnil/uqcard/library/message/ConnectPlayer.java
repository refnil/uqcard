package com.refnil.uqcard.library.message;

public class ConnectPlayer extends UqcardMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7038095499822916808L;
	final public String name;
	final public DeckMessage dm;

	public ConnectPlayer(String name, DeckMessage dm) {
		this.name = name;
		this.dm = dm;
	}
}