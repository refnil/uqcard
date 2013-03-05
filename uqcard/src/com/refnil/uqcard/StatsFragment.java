package com.refnil.uqcard;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class StatsFragment extends Fragment {
	

	public StatsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.stats,
		        container, false);
		ArrayList<String> items = new ArrayList<String>();    
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
	            android.R.layout.simple_list_item_1,
	            items);
		//Temp
		items.add("Win");
		items.add("1");
		items.add("Lose");
		items.add("2");
		//End temp   
		GridView gv = (GridView) view.findViewById(R.id.gridViewStats);
		gv.setAdapter(adapter);
		return view;
	}
}