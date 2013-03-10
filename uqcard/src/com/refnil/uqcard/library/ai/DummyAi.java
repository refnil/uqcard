package com.refnil.uqcard.library.ai;

import android.os.RemoteException;
import android.util.Log;

import com.refnil.uqcard.BeginTurn;
import com.refnil.uqcard.EndTurn;
import com.refnil.uqcard.Event;
import com.refnil.uqcard.library.Player;
import com.refnil.uqcard.library.message.CardDeckMessage;
import com.refnil.uqcard.library.message.EmptyDeck;

public class DummyAi extends AbstractAI {

	private final static String TAG = "DummyAi";

	private int turn = 0;

	public DummyAi(Player p) {
		super(p);
		Log.i(TAG, "Start dummy ai");
		try {
			p.connect("DUMMY_AI", new CardDeckMessage(0, 1, new EmptyDeck()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onMessage(Event e) {
		// TODO Auto-generated method stub
		BeginTurn bt = e instanceof BeginTurn ? (BeginTurn) e : null;

		if (bt != null) {
			turn++;
			if (turn % 2 == 0) {
				try {
					Log.i(TAG, "I want to end my turn.");
					sendEvent(new EndTurn());
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
