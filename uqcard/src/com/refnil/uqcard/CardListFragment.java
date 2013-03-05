package com.refnil.uqcard;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CardListFragment extends Fragment {
	
	public CardListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.card_list,
		        container, false);
		
		ArrayList<String> listItems=new ArrayList<String>();
	    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),
	            android.R.layout.simple_list_item_1,
	            listItems);
	    
	    //Temp
		listItems.add("Small raton 1/1");
		//End temp
		
		ListView lv = (ListView) view.findViewById(R.id.listViewCardsList);
		lv.setAdapter(adapter);
		
		return view;
	}
}