package com.refnil.uqcard.library.message;

public class DisconnectedPlayer extends UqcardMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2391541366577083139L;
	final public String name;
	
	public DisconnectedPlayer(String name){
		this.name = name;
	}

}
