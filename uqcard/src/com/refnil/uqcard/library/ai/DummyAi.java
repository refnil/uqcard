package com.refnil.uqcard.library.ai;

import android.os.RemoteException;
import android.util.Log;

import com.refnil.uqcard.event.BeginTurnEvent;
import com.refnil.uqcard.event.EndTurnEvent;
import com.refnil.uqcard.event.Event;
import com.refnil.uqcard.library.Player;

public class DummyAi extends AbstractAI {

	private final static String TAG = "DummyAi";

	private int turn = 0;

	public DummyAi(Player p) {
		super(p);
		Log.i(TAG, "Start dummy ai");
		try {
			p.connect("DUMMY_AI");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onMessage(Event e) {
		// TODO Auto-generated method stub
		BeginTurnEvent bt = e instanceof BeginTurnEvent ? (BeginTurnEvent) e : null;

		if (bt != null) {
			turn++;
			if (turn % 2 == 0) {
				try {
					Log.i(TAG, "I want to end my turn.");
					sendEvent(new EndTurnEvent());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	public void onClose() {
		// TODO Auto-generated method stub

	}

}
