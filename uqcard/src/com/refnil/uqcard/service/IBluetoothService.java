package com.refnil.uqcard.service;

import java.io.IOException;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.res.Resources.NotFoundException;

public interface IBluetoothService {
	public void listen() throws NotFoundException, IOException;
	public BluetoothSocket getSocket();
	public void connect(BluetoothDevice bd) throws NotFoundException, IOException;
}
