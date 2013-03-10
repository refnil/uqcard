package com.refnil.uqcard.view;

import java.util.ArrayList;

import com.refnil.uqcard.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryFragment extends Fragment {
	
	public HistoryFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View view = inflater.inflate(R.layout.history,
		        container, false);
		
		ArrayList<String> listItems=new ArrayList<String>();
	    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),
	            android.R.layout.simple_list_item_1,
	            listItems);
	    
	    //Temp
		listItems.add("Player 1 attacked");
		//End temp
		
		ListView lv = (ListView) view.findViewById(R.id.listViewHistory);
		lv.setAdapter(adapter);
		
		return view;
	}
}