package com.refnil.uqcard.library;

import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.refnil.uqcard.data.Board;
import com.refnil.uqcard.event.Event;
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
		board = new Board();
	}

	public void connect(String name) throws RemoteException {
		sendTo(getServer(), new ConnectPlayer(name));
	}

	public void sendEvent(Event e) throws RemoteException {
		sendTo(getServer(), new RequestServer(board.getPlayerID(),e));
	}

	public Board getBoard() {
		return board;
	}

	@Override
	protected void handleUqcardMessage(Messenger sender, UqcardMessage um) {
		// TODO Auto-generated method stub
		EventMessage em = um instanceof EventMessage ? (EventMessage) um : null;
		YouAre ya = um instanceof YouAre ? (YouAre) um : null;
		ConnectedPlayer cp = um instanceof ConnectedPlayer ? (ConnectedPlayer) um
				: null;
		Close c = um instanceof Close ? (Close) um : null;

		Log.i(TAG, um.toString());

		if (em != null) { // EventMessage
			board.receiveEvent(em.event);
		} else if (ya != null) { // YouAre
			Log.i(TAG, String.valueOf(ya.id));
			board.setPlayerID(ya.id);
		} else if (cp != null) { // ConnectedPlayer

		} else if (c != null) { // Close
			close();
		}

	}

}
