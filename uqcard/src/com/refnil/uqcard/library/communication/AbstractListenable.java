package com.refnil.uqcard.library.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.refnil.uqcard.library.Listenable;
import com.refnil.uqcard.library.Listener;

public abstract class AbstractListenable<T> implements Listenable<T> {
	private List<Listener<T>> list = new ArrayList<Listener<T>>();

	public void subscribe(Listener<T> l) {
		list.add(l);

	}

	public void unsubscribe(Listener<T> l) {
		list.remove(l);
	}

	public void tell(T message) {
		ListIterator<Listener<T>> li = list.listIterator();

		while (li.hasNext()) {
			li.next().onMessage(message);
		}
	}

	public void tellClose() {
		ListIterator<Listener<T>> li = list.listIterator();

		while (li.hasNext()) {
			li.next().onClose();
		}
	}
}
