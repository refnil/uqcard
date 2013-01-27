package com.refnil.uqcard;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.refnil.uqcard.library.LinkConnections;
import com.refnil.uqcard.library.Message;
import com.refnil.uqcard.library.Server;

public class BluetoothLinkConnection extends LinkConnections {

	final BluetoothSocket bs;
	final ObjectInputStream is;
	final ObjectOutputStream os;

	public BluetoothLinkConnection(BluetoothSocket bs, Server s)
			throws IOException {
		super(s);
		Log.i("uqcard","Constructor blc begin");
		// TODO Auto-generated constructor stub
		this.bs = bs;
		Log.i("uqcard","blc bs");
		InputStream ris  = bs.getInputStream();
		Log.i("uqcard","ris done");
		
		if(s==null)
		{
			is = new ObjectInputStream(bs.getInputStream());
			os = new ObjectOutputStream(bs.getOutputStream());
		}
		else
		{
			os = new ObjectOutputStream(bs.getOutputStream());
			is = new ObjectInputStream(bs.getInputStream());
		}
		
		Log.i("uqcard","Constructor blc end");
	}

	@Override
	public void send(Message arg0) {
		// TODO Auto-generated method stub
		try {
			Log.i("uqcard",arg0.toString());
			os.writeObject(arg0);
			Log.i("uqcard","done writing");
			
		} catch (IOException e) {
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		(new Runnable(){
			Message m;

			public void run() {
				// TODO Auto-generated method stub
				while (true) {
		            try {
		                // Read from the InputStream
		                m = (Message)is.readObject();
		                // Send the obtained bytes to the UI activity
		                Log.i("",m.toString());
		                receive(m);
		            } catch (IOException e) {
		                break;
		            } catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
			}
			
		}).run();
	}
	
	public void cancel() {
        try {
            bs.close();
        } catch (IOException e) { }
    }

}
