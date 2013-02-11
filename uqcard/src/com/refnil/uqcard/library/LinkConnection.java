package com.refnil.uqcard.library;

import com.refnil.uqcard.library.message.IdMessage;
import com.refnil.uqcard.library.message.UqcardMessage;

import android.os.HandlerThread;
import android.util.SparseArray;

public class LinkConnection {
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

	public void receive(UqcardMessage um) {
		IdMessage m = UqcardMessage.get(um);
		if (m != null) {
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

	public Link newLink(AbstractActor from, AbstractActor to) {
		int id = highestId++;
		Link l = new Link(id, this, from, to);
		map.append(id, l);
		return l;

	}
}
