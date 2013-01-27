package com.refnil.uqcard;

import java.io.IOException;
import com.refnil.uqcard.library.Server;
import com.refnil.uqcard.library.Talk;
import com.refnil.uqcard.service.BluetoothService;
import com.refnil.uqcard.service.IBluetoothService;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BluetoothActivity extends Activity {
	
	private IBluetoothService mBoundService;
	private boolean mIsBound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth);
		
		final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		Button host = (Button)findViewById(R.id.buttonHost);
		Button client = (Button)findViewById(R.id.buttonClient);
		Button service = (Button)findViewById(R.id.buttonService);
		
		host.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mIsBound){
					try {
						mBoundService.listen();
						BluetoothSocket bs = mBoundService.getSocket();  
						Log.i("",bs==null?"null":"notnull");
						BluetoothLinkConnection blc = new BluetoothLinkConnection(bs, new Server());
						Log.i("","blc created");
						blc.send(new Talk("lol"));
						Log.i("","Message send");
					} catch (NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
			
		});
		
		client.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mIsBound){
					
					try {
						mBoundService.connect(mBluetoothAdapter.getBondedDevices().iterator().next());
						BluetoothSocket bs = mBoundService.getSocket();
						BluetoothLinkConnection blc = new BluetoothLinkConnection(bs, null);
						blc.start();
					} catch (NotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		
		service.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String lol;
				if(mIsBound){
					lol = "Bound";
				}
				else{
					lol = "notBound";
				}
				Toast.makeText(getApplicationContext(), lol, Toast.LENGTH_SHORT).show();
			}
			
		});
		
		startService(new Intent(this,BluetoothService.class));
	
	}
	
	protected void onStart(){
		super.onStart();
		bindService(new Intent(this, BluetoothService.class), mConnection, Context.BIND_AUTO_CREATE);
	    
	}
	
	private ServiceConnection mConnection = new ServiceConnection() {
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        // This is called when the connection with the service has been
	        // established, giving us the service object we can use to
	        // interact with the service.  Because we have bound to a explicit
	        // service that we know is running in our own process, we can
	        // cast its IBinder to a concrete class and directly access it.
	        mBoundService = ((BluetoothService.LocalBinder)service).getService();
	        mIsBound = true;

	    }

	    public void onServiceDisconnected(ComponentName className) {
	        // This is called when the connection with the service has been
	        // unexpectedly disconnected -- that is, its process crashed.
	        // Because it is running in our same process, we should never
	        // see this happen.
	        mBoundService = null;
	    }
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bluetooth, menu);
		return true;
	}

}
