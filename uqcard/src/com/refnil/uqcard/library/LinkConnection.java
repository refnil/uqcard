package com.refnil.uqcard.library;

import com.refnil.uqcard.library.message.IdMessage;
import com.refnil.uqcard.library.message.UqcardMessage;

import android.os.HandlerThread;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;

public abstract class LinkConnection {
	private static final String TAG = "LinkConnection";
	private AbstractServer as;
	private SparseArray<Link> map = new SparseArray<Link>();
	private int highestId = 0;

	public LinkConnection(AbstractServer as) {
		if (as != null) {
			this.as = as;

		} else {
			HandlerThread t = new HandlerThread("ProxyServer");
			t.start();
            this.as = new ServerProxy(t.getLooper(), this);
			
		}
	}

	public void receive(UqcardMessage um) {
		IdMessage m = um instanceof IdMessage?(IdMessage)um:null;
		if (m!=null) {
			Log.i(TAG, "Received:" + m.message.toString());
			Link l = map.get(m.id);
			if (l == null) {
				HandlerThread t = new HandlerThread("ProxyPlayer");
                t.start();
				PlayerProxy pp = new PlayerProxy(t.getLooper(), as, this);
				l = pp.getLink();
			}
			l.receive(m.message);
		}
	}

	public Link newLink(Proxy from, Messenger to) {
		int id = highestId++;
		Log.i(TAG,"New link" + String.valueOf(id));
		Link l = new Link(id, this, from,to);
		map.append(id, l);
		return l;

	}
	
	public AbstractServer getServer(){
		return as;
	}

	abstract public void send(UqcardMessage idMessage);
	abstract public void start();
}
