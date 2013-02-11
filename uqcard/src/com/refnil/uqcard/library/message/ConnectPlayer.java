package com.refnil.uqcard.library.message;

public class ConnectPlayer extends UqcardMessage {
	final public String name;

	public ConnectPlayer(String name) {
		this.name = name;
	}
}