package com.refnil.uqcard;

import android.bluetooth.BluetoothSocket;

import com.refnil.uqcard.library.LinkConnections;
import com.refnil.uqcard.library.Message;
import com.refnil.uqcard.library.Server;

public class BluetoothLinkConnection extends LinkConnections {

	public BluetoothLinkConnection(BluetoothSocket bs,Server s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(Message arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

}
