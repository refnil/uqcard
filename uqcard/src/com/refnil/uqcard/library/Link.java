package com.refnil.uqcard.library;

import com.refnil.uqcard.library.message.UqcardMessage;

public class Link {
	
	private int id;
	private LinkConnection lc;
	private AbstractActor from;
	private AbstractActor to;
	
	
	public Link(int id, LinkConnection lc, AbstractActor from, AbstractActor to){
		this.id = id;
		this.lc = lc;
		this.from = from;
		this.to = to;
	}


	public void receive(UqcardMessage message) {
		// TODO Auto-generated method stub
		
	}

}
