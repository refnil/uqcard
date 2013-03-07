package com.refnil.uqcard.view;

import com.refnil.uqcard.BoardActivity;
import com.refnil.uqcard.R;
import com.refnil.uqcard.R.id;
import com.refnil.uqcard.R.layout;
import com.refnil.uqcard.service.IService;
import com.refnil.uqcard.service.UqcardService;

import android.content.Intent;
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
				Intent i = new Intent(getActivity().getApplicationContext(),
						UqcardService.class);
				i.putExtra(IService.TYPE, IService.START_AI_LAME);
				getActivity().startService(i);
				
				i = new Intent(getActivity().getApplicationContext(),
						BoardActivity.class);
				startActivity(i);
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