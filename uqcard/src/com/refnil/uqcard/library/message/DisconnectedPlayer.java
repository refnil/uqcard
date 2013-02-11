package com.refnil.uqcard.library.message;

public class DisconnectedPlayer extends UqcardMessage {
	final public String name;
	
	public DisconnectedPlayer(String name){
		this.name = name;
	}

}
