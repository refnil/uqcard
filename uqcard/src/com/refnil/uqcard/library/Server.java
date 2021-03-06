package com.refnil.uqcard.library;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.refnil.uqcard.data.ServerBoard;
import com.refnil.uqcard.event.Event;
import com.refnil.uqcard.library.message.Close;
import com.refnil.uqcard.library.message.ConnectPlayer;
import com.refnil.uqcard.library.message.ConnectedPlayer;
import com.refnil.uqcard.library.message.DeckMessage;
import com.refnil.uqcard.library.message.DisconnectPlayer;
import com.refnil.uqcard.library.message.DisconnectedPlayer;
import com.refnil.uqcard.library.message.EventMessage;
import com.refnil.uqcard.library.message.RequestServer;
import com.refnil.uqcard.library.message.UqcardMessage;
import com.refnil.uqcard.library.message.YouAre;

public class Server extends AbstractServer implements Listener<Event> {
	
	private final static String TAG = "Server";

	private ServerBoard board = new ServerBoard();

	private List<TempPlayer> players = new ArrayList<TempPlayer>();
	private int numberOfPlayer = 0;
	
	private List<UqcardMessage> messages = new LinkedList<UqcardMessage>();

	public Server(Looper looper) {
		super(looper);
		// TODO Auto-generated constructor stub
		board.subscribe(this);
	}

	@Override
	protected void handleUqcardMessage(Messenger sender, UqcardMessage um) {
		// TODO Auto-generated method stub
		
		Log.i(TAG, "Server received:" + um);
		
		messages.add(um);
		
		ConnectPlayer cp = um instanceof ConnectPlayer?(ConnectPlayer)um:null;
		DisconnectPlayer dp = um instanceof DisconnectPlayer?(DisconnectPlayer)um:null;
		RequestServer rs = um instanceof RequestServer?(RequestServer)um:null;
		Close c = um instanceof Close?(Close)um:null;
		DeckMessage dm = um instanceof DeckMessage? (DeckMessage)um : null;

		if (cp != null) {
			if (numberOfPlayer < 2) {
				numberOfPlayer++;
				players.add(new TempPlayer(numberOfPlayer, sender, cp.name));
				try {
					sendTo(sender, new YouAre(numberOfPlayer));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					tellToAll(new ConnectedPlayer(cp.name));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (dp != null) {
			ListIterator<TempPlayer> it = players.listIterator();

			while (it.hasNext()) {
				TempPlayer tp = it.next();
				if (sender == tp.m) {
					it.remove();
					try {
						tellToAll(new DisconnectedPlayer(tp.name));
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		} else if (rs != null) {
			Log.i(TAG,rs.event.type.toString());
			board.receiveEvent(rs.event);
		} else if (c != null) {
			try {
				tellToAll(new Close());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (dm != null)
		{
			dm.getDeck();
		}

	}

	private void tellToAll(UqcardMessage um) throws RemoteException {
		Log.i(TAG, "Server sent:" + um);
		ListIterator<TempPlayer> it = players.listIterator();

		while (it.hasNext()) {
			sendTo(it.next().m, um);
		}
	}

	public void onMessage(Event m) {
		// TODO Auto-generated method stub
		try {
			tellToAll(new EventMessage(m));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onClose() {
		// TODO Auto-generated method stub

	}

	private class TempPlayer {
		final private int id;
		final public Messenger m;
		final public String name;

		public TempPlayer(int id, Messenger m, String name) {
			this.id = id;
			this.m = m;
			this.name = name;
		}
	}

}
