package com.refnil.uqcard.library;

public interface Listener<T> {
public void onMessage(T m);
public void onClose();
}
