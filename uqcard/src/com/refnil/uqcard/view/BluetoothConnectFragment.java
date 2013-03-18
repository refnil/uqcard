package com.refnil.uqcard.view;

import java.io.IOException;
import java.util.Set;

import com.refnil.uqcard.BluetoothLinkConnection;
import com.refnil.uqcard.R;
import com.refnil.uqcard.TabsActivity;
import com.refnil.uqcard.service.IService;
import com.refnil.uqcard.service.UqcardService;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BluetoothConnectFragment extends Fragment{
	private BluetoothEntryAdapter dbea;
	private BluetoothEntryAdapter pbea;
	final private int REQUEST_ENABLE_BT = 1337;
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
	
	
	public BluetoothConnectFragment()
	{
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_bluetooth_connect,
		        container, false);
		
		// Show the Up button in the action bar.
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		getActivity().registerReceiver(mReceiver, filter); // Don't forget to
												// unregister during
												// onDestroy

		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		} else {
			setUpList(view);
		}

		bdlh = (TextView) view.findViewById(R.id.BluetoothDiscoveredListHeader);
		
		return view;
	}
	
	private void setUpList(View view) {
		final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		Context c = getActivity().getApplicationContext();

		Button b = (Button) view.findViewById(R.id.BluetoothDiscoveriesButton);

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

		ListView discoveredView = (ListView) view.findViewById(R.id.BluetoothDiscoveredList);
		ListView pairedView = (ListView) view.findViewById(R.id.BluetoothPairedList);

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
						getActivity());

				AlertDialog ad = adb.setMessage(
						"Voulez-vous vous connecter à " + name.getText() + "("
								+ address + ") ?")
						.setPositiveButton(R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// User clicked OK button
										
												Intent i = new Intent(
														getActivity().getApplicationContext(),
														UqcardService.class);
												i.putExtra(IService.TYPE,
														IService.CONNECT_BLUETOOTH);
												i.putExtra("address", address);
												getActivity().startService(i);
									
										//((TabsActivity) getActivity()).startBoardFragment();
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
			TextView tv = (TextView) view.findViewById(R.id.BluetoothPairedListHeader);
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
	
	private class BluetoothEntryAdapter extends ArrayAdapter<BluetoothEntry> {

		LayoutInflater li = getActivity().getLayoutInflater();

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
