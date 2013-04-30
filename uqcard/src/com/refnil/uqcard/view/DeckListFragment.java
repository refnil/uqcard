package com.refnil.uqcard.view;

import java.io.IOException;

import com.refnil.uqcard.R;
import com.refnil.uqcard.data.CachedCardStore;
import com.refnil.uqcard.data.Deck;

import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeckListFragment extends Fragment {
	
	CachedCardStore ccs;

	public DeckListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Deck deck;
		LinearLayout cardListLayout;
		View view = inflater.inflate( R.layout.activity_deck_list,
		        container, false);
		
		try {
			ccs = CachedCardStore.initAndGet(getResources().openRawResource(R.raw.card));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		deck = Deck.createDeck(ccs);
		
		cardListLayout = (LinearLayout) view.findViewById(R.id.cardListLayout);
		cardListLayout.setOrientation(LinearLayout.VERTICAL);
		for(int i=0;i<40;i++)
		{
			TextView t = new TextView(getActivity());
			t.setText(String.valueOf(deck.CardAt(0).get_Id()));
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