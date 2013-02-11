package com.refnil.uqcard.library;

import java.util.HashMap;
import java.util.Map;

import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;

import com.refnil.uqcard.library.message.UqcardMessage;

public class ServeurProxy extends AbstractServer implements Proxy {
	
	private LinkConnection lc;
	private Map<Messenger,Link> messengerMap = new HashMap<Messenger,Link>();
	

	public ServeurProxy(Looper looper,LinkConnection lc) {
		super(looper);
		// TODO Auto-generated constructor stub
		this.lc = lc;
	}

	public void receive(Messenger m, UqcardMessage um) {
		// TODO Auto-generated method stub
		try {
			sendTo(m,um);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void handleUqcardMessage(Messenger sender, UqcardMessage um) {
		// TODO Auto-generated method stub
		Link l = messengerMap.get(sender);
		if(l==null){
			l = lc.newLink(this,sender);
			messengerMap.put(sender, l);
		}
	}

}
