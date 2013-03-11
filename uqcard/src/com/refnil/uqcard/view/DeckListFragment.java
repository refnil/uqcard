package com.refnil.uqcard.view;

import com.refnil.uqcard.R;
import com.refnil.uqcard.data.Deck;
import com.refnil.uqcard.data.test.DeckTest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeckListFragment extends Fragment {
	
	

	public DeckListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Deck deck;
		LinearLayout cardListLayout;
		View view = inflater.inflate( R.layout.activity_deck_list,
		        container, false);
		deck = new Deck(DeckTest.createDeck(),"Test");
		cardListLayout = (LinearLayout) view.findViewById(R.id.cardListLayout);
		cardListLayout.setOrientation(LinearLayout.VERTICAL);
		for(int i=0;i<40;i++)
		{
			TextView t = new TextView(getActivity());
			t.setText(String.valueOf(deck.drawCardAt(0).get_Id()));
			t.setClickable(true);
			t.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					TextView t =(TextView) ((View) v.getParent().getParent().getParent()).findViewById(R.id.cardDesc);
					t.setText(((TextView)v).getText());

				}
			});

			cardListLayout.addView(t);

		}

		return view;
	}
}