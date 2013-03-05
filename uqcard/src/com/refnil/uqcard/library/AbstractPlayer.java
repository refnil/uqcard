package com.refnil.uqcard.library;

import android.os.Looper;
import android.os.Messenger;

public abstract class AbstractPlayer extends AbstractActor {
	private Messenger server;

	public AbstractPlayer(Looper looper, AbstractServer as) {
		super(looper);
		// TODO Auto-generated constructor stub
		this.server = as.getMessenger();
	}
	
	protected Messenger getServer(){
		return server;
	}

}
