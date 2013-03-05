package com.refnil.uqcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class GameFragment extends Fragment {
	
	public GameFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.game_menu,
		        container, false);
		
		view.findViewById(R.id.textViewNewGame).setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Toast.makeText(getActivity().getApplicationContext(), "New game", Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
		view.findViewById(R.id.textViewConnect).setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Toast.makeText(getActivity().getApplicationContext(), "Connect", Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
		view.findViewById(R.id.textViewHost).setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Toast.makeText(getActivity().getApplicationContext(), "Host", Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
		return view;
	}
	
	
}