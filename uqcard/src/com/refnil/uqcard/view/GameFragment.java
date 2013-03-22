package com.refnil.uqcard.view;

import com.refnil.uqcard.R;
import com.refnil.uqcard.TabsActivity;
import com.refnil.uqcard.service.IService;
import com.refnil.uqcard.service.UqcardService;
import com.refnil.uqcard.service.UqcardService.LocalBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GameFragment extends Fragment {
	
	private String TAG = "GameFragment";
	IService mService;
	ServiceConnection mConnection;
	private Messenger messenger = new Messenger(new Handler());
	private Handler handler = new Handler() {
	    public void handleMessage(Message message) {
	      Object connected = message.obj;
	      if (connected.toString() == "true") {
	        Log.i(TAG,
	            "Connected " + connected.toString());
	        ((TabsActivity)getActivity()).startBoardFragment(true);
	      } else {
	    	  Log.i(TAG,"Connected failed");
	      }

	    };
	  };
	  
	public GameFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.game_menu,
		        container, false);
		
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

		final Button h = (Button) view.findViewById(R.id.textViewHost);
		final Button c = (Button) view.findViewById(R.id.textViewConnect);

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
				Log.v(TAG,"Try to listen");
				mService.listenBluetooth();
			}

		});

		c.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getActivity().getApplicationContext(), "BluetoothConnect",
						Toast.LENGTH_SHORT).show();
				((TabsActivity)getActivity()).startBluetoothFragment(R.id.gameMenuLayout);
			}
		});
		
		h.setClickable(false);
		c.setClickable(false);

		Intent intent = new Intent(getActivity(), UqcardService.class);
		Messenger messenger = new Messenger(handler);
		intent.putExtra("MESSENGER", messenger);
		getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		
		return view;
	}
}