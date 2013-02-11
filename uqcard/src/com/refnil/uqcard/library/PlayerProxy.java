package com.refnil.uqcard.library;

import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;

import com.refnil.uqcard.library.message.UqcardMessage;

public class PlayerProxy extends AbstractPlayer implements Proxy{
	
	private Link l;

	public PlayerProxy(Looper looper, AbstractServer as, LinkConnection lc) {
		super(looper,as);
		// TODO Auto-generated constructor stub
		this.l = lc.newLink(this,as.getMessenger());	
	}

	@Override
	protected void handleUqcardMessage(Messenger sender, UqcardMessage um) {
		// TODO Auto-generated method stub
		l.send(um);
	}

	public Link getLink() {
		// TODO Auto-generated method stub
		return l;
	}

	public void receive(Messenger to, UqcardMessage um) {
		// TODO Auto-generated method stub
		try {
			sendTo(to,um);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
