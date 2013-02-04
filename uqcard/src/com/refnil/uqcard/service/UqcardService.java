package com.refnil.uqcard.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.refnil.uqcard.BluetoothLinkConnection;
import com.refnil.uqcard.BoardViewActivity;
import com.refnil.uqcard.R;
import com.refnil.uqcard.Board;
import com.refnil.uqcard.library.LinkConnections;
import com.refnil.uqcard.library.Server;
import com.refnil.uqcard.library.Close;
import com.refnil.uqcard.library.Player;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class UqcardService extends Service implements IService {

	private final String TAG = "Uqcard service";
	private final IBinder mBinder = new LocalBinder();
	private UUID uuid;
	private Server server = null;
	private Player player = null;
	private Set<LinkConnections> lcs = new HashSet<LinkConnections>();

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
		case 1:
			BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
			ba.cancelDiscovery();
			String add = intent.getStringExtra("address");
			if (!add.isEmpty()) {
				connect(ba.getRemoteDevice(add));
			}
			break;
		}

		return START_STICKY;
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
			server = new Server();
		}
	}

	public Server getServer() {
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
					BluetoothLinkConnection blc = new BluetoothLinkConnection(bs, server);
					blc.start();
					lcs.add(blc);
					Intent i = new Intent(UqcardService.this, BoardViewActivity.class);
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
			server.tell(new Close());
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
							BluetoothLinkConnection blc = new BluetoothLinkConnection(bs, server);
							blc.start();
							lcs.add(blc);
							Log.i(TAG, "Connection received");
							if(nb == 1){
								Intent i = new Intent(UqcardService.this, BoardViewActivity.class);
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
			player = new Player(server);
			player.start();
			player.connect("ROger");
		}
		return player;
	}

	public Board getBoard() {
		// TODO Auto-generated method stub
		
		return player!=null?player.board():null;
	}

}
