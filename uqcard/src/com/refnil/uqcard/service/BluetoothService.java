package com.refnil.uqcard.service;

import java.io.IOException;
import java.util.UUID;

import com.refnil.uqcard.R;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BluetoothService extends Service implements IBluetoothService {
	
	private final IBinder mBinder = new LocalBinder();
	private BluetoothSocket bs = null;
	private BluetoothAdapter ba = null;
	
	@Override
	public void onCreate() {
		Log.i("uqcard","service creat");
		ba = BluetoothAdapter.getDefaultAdapter();
		
		if(ba == null)
			stopSelf();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	      Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
	      
	      
	      return START_NOT_STICKY;
	  }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
	public void onDestroy(){
		Log.i("uqcard","service destroy");
	}
	
	public class LocalBinder extends Binder {
        public IBluetoothService getService() {
            // Return this instance of LocalService so clients can call public methods
            return BluetoothService.this;
        }
    }

	public void listen() throws NotFoundException, IOException {
		// TODO Auto-generated method stub
		bs = ba.listenUsingRfcommWithServiceRecord("Server", UUID.fromString(getResources().getString(R.string.UUID))).accept();
		
	}

	public BluetoothSocket getSocket() {
		// TODO Auto-generated method stub
		return bs;
	}

	public void connect(BluetoothDevice bd) throws NotFoundException, IOException {
		// TODO Auto-generated method stub
		bs = bd.createInsecureRfcommSocketToServiceRecord(UUID.fromString(getResources().getString(R.string.UUID)));
		bs.connect();
		
	}


}
