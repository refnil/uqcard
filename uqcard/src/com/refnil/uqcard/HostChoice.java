package com.refnil.uqcard;

import com.refnil.uqcard.service.IService;
import com.refnil.uqcard.service.UqcardService;
import com.refnil.uqcard.service.UqcardService.LocalBinder;
import com.refnil.uqcard.library.Listenable;
import com.refnil.uqcard.library.Player;
import com.refnil.uqcard.library.test.DummyPlayerAndroid;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import android.support.v4.app.NavUtils;

public class HostChoice extends Activity {

	private String TAG = "HostChoice";
	IService mService;
	ServiceConnection mConnection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host_choice);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		final Button h = (Button) findViewById(R.id.host_button);
		final Button c = (Button) findViewById(R.id.client_button);

		mConnection = new ServiceConnection() {

			// Called when the connection with the service is established
			public void onServiceConnected(ComponentName className,
					IBinder service) {
				Log.e(TAG, "onServiceConnected");
				// Because we have bound to an explicit
				// service that is running in our own process, we can
				// cast its IBinder to a concrete class and directly access it.
				mService = (IService) ((LocalBinder) service).getService();
				h.setClickable(true);
				c.setClickable(true);
			}

			// Called when the connection with the service disconnects
			// unexpectedly
			public void onServiceDisconnected(ComponentName className) {
				Log.e(TAG, "onServiceDisconnected");
				h.setClickable(false);
				c.setClickable(false);
			}
		};

		h.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mService.createServer();
				HandlerThread t = new HandlerThread("Player");
				Player p = new Player(t.getLooper(),mService.getServer());
				t.start();
				Log.v(TAG,"Try to listen");
				mService.listenBluetooth();
				
			}

		});

		c.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "BluetoothConnect",
						Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(), BluetoothConnect.class);
				startActivity(i);
			}
		});
		
		h.setClickable(false);
		c.setClickable(false);

		Intent intent = new Intent(this, UqcardService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_host_choice, menu);
		return true;
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		unbindService(mConnection);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
