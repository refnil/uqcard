package com.refnil.uqcard.library;

import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.refnil.uqcard.Board;
import com.refnil.uqcard.Event;
import com.refnil.uqcard.library.message.Close;
import com.refnil.uqcard.library.message.ConnectPlayer;
import com.refnil.uqcard.library.message.ConnectedPlayer;
import com.refnil.uqcard.library.message.EventMessage;
import com.refnil.uqcard.library.message.RequestServer;
import com.refnil.uqcard.library.message.UqcardMessage;
import com.refnil.uqcard.library.message.YouAre;

public class Player extends AbstractPlayer {
	
	private final static String TAG = "Player";

	private Board board;

	public Player(Looper looper, AbstractServer as) {
		super(looper, as);
		// TODO Auto-generated constructor stub
	}

	public void connect(String name) throws RemoteException {
		sendTo(getServer(), new ConnectPlayer(name));
	}

	public void sendEvent(Event e) throws RemoteException {
		sendTo(getServer(), new RequestServer(e));
	}

	public Board getBoard() {
		return board;
	}

	@SuppressWarnings("null")
	@Override
	protected void handleUqcardMessage(Messenger sender, UqcardMessage um) {
		// TODO Auto-generated method stub
		EventMessage em = null;
		YouAre ya = null;
		ConnectedPlayer cp = null;
		Close c = null;
		
		Log.i(TAG,um.toString());
		
		if (um.as(em)) { // EventMessage
			board.receiveEvent(em.event);
		} else if (um.as(ya)) { // YouAre
			board.setPlayerID(ya.id);
		} else if (um.as(cp)) { // ConnectedPlayer

		} else if (um.as(c)) { // Close
			close();
		}

	}

}
