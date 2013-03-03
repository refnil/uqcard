package com.refnil.uqcard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DeckListFragment extends Fragment {
	

	public DeckListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		TextView textView = new TextView(getActivity());
		textView.setBackgroundColor(Color.RED);
		textView.setGravity(Gravity.CENTER);
		textView.setText("Deck list");
		return textView;
	}
}