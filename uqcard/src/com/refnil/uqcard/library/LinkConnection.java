package com.refnil.uqcard.library;

import com.refnil.uqcard.library.message.IdMessage;
import com.refnil.uqcard.library.message.UqcardMessage;

import android.os.HandlerThread;
import android.os.Messenger;
import android.util.SparseArray;

public abstract class LinkConnection {
	private AbstractServer as;
	private SparseArray<Link> map = new SparseArray<Link>();
	private int highestId = 0;

	public LinkConnection(AbstractServer as) {
		if (as != null) {
			this.as = as;

		} else {
			HandlerThread t = new HandlerThread("ProxyServer");
			as = new ProxyServer(t.getLooper(), this);
			t.start();
		}
	}

	@SuppressWarnings("null")
	public void receive(UqcardMessage um) {
		IdMessage m = null;
		if ((m = um.get())!=null) {
			Link l = map.get(m.id);
			if (l == null) {
				HandlerThread t = new HandlerThread("ProxyPlayer");
				PlayerProxy pp = new PlayerProxy(t.getLooper(), as, this);
				t.start();
				l = pp.getLink();
			}
			l.receive(m.message);
		}
	}

	public Link newLink(Proxy from, Messenger to) {
		int id = highestId++;
		Link l = new Link(id, this, from,to);
		map.append(id, l);
		return l;

	}

	abstract public void send(UqcardMessage idMessage);
	abstract public void start();
}
