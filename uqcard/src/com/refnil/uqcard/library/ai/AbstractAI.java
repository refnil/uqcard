package com.refnil.uqcard.library.ai;

import android.os.RemoteException;

import com.refnil.uqcard.Board;
import com.refnil.uqcard.Event;
import com.refnil.uqcard.library.Listener;
import com.refnil.uqcard.library.Player;

public abstract class AbstractAI implements Listener<Event>{
	
	private Player p;
	private Board b;
	
	public  AbstractAI(Player p)
	{
		this.p = p;
		b = p.getBoard();
		b.subscribe(this);
		
	}
	
	final protected Board getBoard(){
		return b;
	}
	
	final protected void sendEvent(Event e) throws RemoteException
	{
		p.sendEvent(e);
	}
	
}
