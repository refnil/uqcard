package com.refnil.uqcard;

import java.util.Set;

import com.refnil.uqcard.service.IService;
import com.refnil.uqcard.service.UqcardService;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class BluetoothConnect extends Activity {

	private BluetoothEntryAdapter dbea;
	private BluetoothEntryAdapter pbea;
	final private int REQUEST_ENABLE_BT = 1337 ;
	private final static String TAG = "BluetoothConnect";

	private TextView bdlh;

	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

		private boolean header = false;

		public void onReceive(Context context, Intent intent) {
			
			String action = intent.getAction();
			// When discovery finds a device
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				// Get the BluetoothDevice object from the Intent
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				
				// Check if the device is already present

				boolean present = false;
				for (int i = 0; i < dbea.getCount(); i++) {

					if (device.getAddress().equals(dbea.getItem(i).address)) {
						present = true;
					}
				}
				for (int i = 0; i < pbea.getCount(); i++) {
					if (device.getAddress().equals(pbea.getItem(i).address)) {
						present = true;
					}
				}

				if (!present) {
					Log.i(TAG, "Discovered: " + device.getAddress());
					dbea.add(new BluetoothEntry(device.getName(), device
							.getAddress()));
					if (!header) {
						bdlh.setVisibility(View.VISIBLE);
						header = true;
					}
				}

			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth_connect);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(mReceiver, filter); // Don't forget to
												// unregister during
												// onDestroy

		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		} else {
			setUpList();
		}

		bdlh = (TextView) findViewById(R.id.BluetoothDiscoveredListHeader);

	}

	private void setUpList() {
		final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		Context c = getApplicationContext();

		Button b = (Button) findViewById(R.id.BluetoothDiscoveriesButton);

		b.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Register the BroadcastReceiver
				Button b = ((Button) v);
				if (!mBluetoothAdapter.isDiscovering()) {
					mBluetoothAdapter.startDiscovery();
					b.setText("Cancel discovery");
				} else {
					mBluetoothAdapter.cancelDiscovery();
					b.setText("Start discovery");
				}

			}

		});

		ListView discoveredView = (ListView) findViewById(R.id.BluetoothDiscoveredList);
		ListView pairedView = (ListView) findViewById(R.id.BluetoothPairedList);

		OnItemClickListener icl = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				TextView name = (TextView) v
						.findViewById(R.id.BluetoothEntry_Name);
				TextView addressView = (TextView) v
						.findViewById(R.id.BluetoothEntry_Address);

				final CharSequence address = addressView.getText();

				AlertDialog.Builder adb = new AlertDialog.Builder(
						BluetoothConnect.this);

				AlertDialog ad = adb.setMessage(
						"Voulez-vous vous connecter à " + name.getText() + "("
								+ address + ") ?")
						.setPositiveButton(R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// User clicked OK button
										Intent i = new Intent(
												getApplicationContext(),
												UqcardService.class);
										i.putExtra(IService.TYPE,
												IService.CONNECT_BLUETOOTH);
										i.putExtra("address", address);
										startService(i);
									}
								})
						.setNegativeButton(R.string.cancel,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// User clicked OK button
									}
								}).create();

				ad.show();
			}

		};

		discoveredView.setOnItemClickListener(icl);
		pairedView.setOnItemClickListener(icl);

		dbea = new BluetoothEntryAdapter(c);
		pbea = new BluetoothEntryAdapter(c);

		pairedView.setAdapter(pbea);
		discoveredView.setAdapter(dbea);

		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
				.getBondedDevices();

		// If there are paired devices
		if (pairedDevices.size() > 0) {
			TextView tv = (TextView) findViewById(R.id.BluetoothPairedListHeader);
			tv.setVisibility(View.VISIBLE);

			// Loop through paired devices
			for (BluetoothDevice device : pairedDevices) {
				// Add the name and address to an array adapter to show in a
				// ListView
				pbea.add(new BluetoothEntry(device.getName(), device
						.getAddress()));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bluetooth_connect, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mReceiver);
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

	private class BluetoothEntryAdapter extends ArrayAdapter<BluetoothEntry> {

		LayoutInflater li = getLayoutInflater();

		public BluetoothEntryAdapter(Context c) {
			super(c, R.layout.bluetooth_entry);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row;

			if (null == convertView) {
				row = li.inflate(R.layout.bluetooth_entry, null);
			} else {
				row = convertView;
			}

			TextView name = (TextView) row
					.findViewById(R.id.BluetoothEntry_Name);
			TextView address = (TextView) row
					.findViewById(R.id.BluetoothEntry_Address);
			BluetoothEntry be = getItem(position);

			name.setText(be.name);
			address.setText(be.address);

			return row;
		}

	}

	private class BluetoothEntry {

		private String name;
		private String address;

		public BluetoothEntry(String name, String address) {
			this.name = name;
			this.setAddress(address);
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	}

}
