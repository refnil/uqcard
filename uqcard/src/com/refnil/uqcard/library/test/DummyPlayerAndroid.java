package com.refnil.uqcard.library.test;

import android.util.Log;

import com.refnil.uqcard.library.Listenable;
import com.refnil.uqcard.library.Listener;

public class DummyPlayerAndroid implements Listener<String> {

	private final String TAG = "DummyPlayer";
	
	public DummyPlayerAndroid(Listenable<String> p){
		p.subscribe(this);
	}

	public void onMessage(String m) {
		Log.i(TAG, m);

	}

	public void onClose() {
		Log.i(TAG, "Closed");
	}

}
