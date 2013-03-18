package com.refnil.uqcard.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.refnil.uqcard.BluetoothLinkConnection;
import com.refnil.uqcard.BoardActivity;
import com.refnil.uqcard.R;
import com.refnil.uqcard.TabsActivity;
import com.refnil.uqcard.library.AbstractServer;
import com.refnil.uqcard.library.LinkConnection;
import com.refnil.uqcard.library.Player;
import com.refnil.uqcard.library.Server;
import com.refnil.uqcard.library.ai.DummyAi;
import com.refnil.uqcard.library.message.CardDeckMessage;
import com.refnil.uqcard.library.message.EmptyDeck;

public class UqcardService extends Service implements IService {

	private final String TAG = "Uqcard service";
	private final IBinder mBinder = new LocalBinder();
	private UUID uuid;
	private AbstractServer server = null;
	private Player player = null;
	private Set<LinkConnection> lcs = new HashSet<LinkConnection>();

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		uuid = UUID.fromString(getResources().getString(R.string.UUID));
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStart");
		Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

		int type = intent.getIntExtra(IService.TYPE, IService.NOTHING);

		switch (type) {
		case CONNECT_BLUETOOTH:
			BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
			ba.cancelDiscovery();
			String add = intent.getStringExtra("address");
			if (!add.isEmpty()) {
				connect(ba.getRemoteDevice(add));
			}
			break;
		case START_AI_LAME:
			createServer();
			HandlerThread t = new HandlerThread("player_ai");
			t.start();
			Player p = new Player(t.getLooper(), server);
			new DummyAi(p);
			break;
		}

		return START_REDELIVER_INTENT;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	public void onDestroy() {
		Log.i(TAG, "onDestroy");
	}

	public class LocalBinder extends Binder {
		public IService getService() {
			// Return this instance of LocalService so clients can call public
			// methods
			return UqcardService.this;
		}
	}

	public void createServer() {
		// TODO Auto-generated method stub
		if (server == null) {

			HandlerThread t = new HandlerThread("Server");
			t.start();
			server = new Server(t.getLooper());

		}
	}

	public AbstractServer getServer() {
		// TODO Auto-generated method stub
		return server;
	}

	public void listenBluetooth() {
		// TODO Auto-generated method stub
		listenBluetooth(1);

	}

	public void connect(final BluetoothDevice bd) {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				try {
					BluetoothSocket bs = bd
							.createRfcommSocketToServiceRecord(uuid);
					bs.connect();
					BluetoothLinkConnection blc = new BluetoothLinkConnection(
							bs, server);
					server = blc.getServer();
					blc.start();
					lcs.add(blc);
					//Modification cindy
					Intent i = new Intent(UqcardService.this,
							BoardActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		t.start();

	}

	public void destroyServer() {
		// TODO Auto-generated method stub
		if (server != null) {
			server.close();
			server = null;
		}
	}

	public void listenBluetooth(final int nb) {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Runnable() {

			private int i = nb;

			public void run() {
				// TODO Auto-generated method stub

				BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
				BluetoothServerSocket bss;

				try {
					bss = ba.listenUsingRfcommWithServiceRecord("UqcardServer",
							uuid);
					Log.i(TAG, "Starting listening");

					while (i-- > 0) {
						try {
							BluetoothSocket bs = bss.accept();
							BluetoothLinkConnection blc = new BluetoothLinkConnection(
									bs, server);
							blc.start();
							lcs.add(blc);
							Log.i(TAG, "Connection received");
							if (nb == 1) {
								Intent i = new Intent(UqcardService.this,
										BoardActivity.class);
								i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								startActivity(i);
							}
						} catch (NotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		t.start();
	}

	public Player getPlayer() {
		// TODO Auto-generated method stub
		if (server != null && player == null) {
			HandlerThread t = new HandlerThread("Player");
			t.start();
			player = new Player(t.getLooper(), server);

			try {
				player.connect("ROger",new CardDeckMessage(1, 1, new EmptyDeck()));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return player;
	}

}
