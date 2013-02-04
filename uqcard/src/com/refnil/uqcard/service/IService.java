package com.refnil.uqcard.service;

import android.bluetooth.BluetoothDevice;
import com.refnil.uqcard.library.Server;

public interface IService {
	
	public static final String TYPE = "type";
	
	public static final int NOTHING = 0;
	public static final int CONNECT_BLUETOOTH = 1;
	
	public void createServer();
	public void destroyServer();
	public Server getServer();
	
	public void listenBluetooth();
	public void listenBluetooth(int nb);
	public void connect(BluetoothDevice bd);

}
