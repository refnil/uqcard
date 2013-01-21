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
		// TODO Auto-generated constructor stub
		this.bs = bs;
		InputStream input = bs.getInputStream();
		is = new ObjectInputStream(input);
		os = new ObjectOutputStream(bs.getOutputStream());
	}

	@Override
	public void send(Message arg0) {
		// TODO Auto-generated method stub
		try {
			os.writeObject(arg0);
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
