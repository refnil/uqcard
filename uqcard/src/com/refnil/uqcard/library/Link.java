package com.refnil.uqcard.library;

import com.refnil.uqcard.library.message.IdMessage;
import com.refnil.uqcard.library.message.UqcardMessage;

public class Link {
	
	private int id;
	private LinkConnection lc;
	private Proxy from;
	private AbstractActor to;
	
	
	public Link(int id, LinkConnection lc, Proxy from, AbstractActor to){
		this.id = id;
		this.lc = lc;
		this.from = from;
		this.to = to;
	}


	public void receive(UqcardMessage message) {
		// TODO Auto-generated method stub
		from.receive(message);
	}


	public void send(UqcardMessage um) {
		// TODO Auto-generated method stub
		lc.send(new IdMessage(id,um));
	}

}
