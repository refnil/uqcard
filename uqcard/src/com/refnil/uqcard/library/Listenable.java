package com.refnil.uqcard.library;

public interface Listenable<T> {
	public void subscribe(Listener<T> l);

	public void unsubscribe(Listener<T> l);
}
