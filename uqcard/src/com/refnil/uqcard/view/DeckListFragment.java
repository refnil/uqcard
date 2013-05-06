package com.refnil.uqcard.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.refnil.uqcard.R;
import com.refnil.uqcard.data.CachedCardStore;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.CreatureCard;
import com.refnil.uqcard.data.Deck;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeckListFragment extends Fragment {

	private CachedCardStore ccs;

	private TextView name;
	private TextView description;
	private TextView attaque;
	private TextView defence;
	private TextView vie;
	
	private Button addButton;
	private Button saveButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout cardListLayout;
		View view = inflater.inflate(R.layout.activity_deck_list, container,
				false);
		name = (TextView) view.findViewById(R.id.cardName);
		description = (TextView) view.findViewById(R.id.cardDesc);
		attaque = (TextView) view.findViewById(R.id.cardAtt);
		defence = (TextView) view.findViewById(R.id.cardDef);
		vie = (TextView) view.findViewById(R.id.cardVie);
		
		addButton = (Button) view.findViewById(R.id.addCard);
		saveButton =  (Button) view.findViewById(R.id.save);
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		try {
			ccs = CachedCardStore.initAndGet(getResources().openRawResource(
					R.raw.card));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Deck deck;
		try {
			deck = Deck.createDeckFromStream(ccs, getActivity().openFileInput(Deck.DEFAUT_SAVE_PATH));
		} catch (Exception e) {
			deck = Deck.createDeck(ccs);
		}

		cardListLayout = (LinearLayout) view.findViewById(R.id.cardListLayout);
		cardListLayout.setOrientation(LinearLayout.VERTICAL);

		for (int i = 0; i < 40; i++) {
			CreatureCard cur = (CreatureCard) deck.CardAt(i);
			TextView t = new TextView(getActivity());
			t.setText(String.valueOf(cur.getName()));

			t.setClickable(true);
			t.setOnClickListener(new CardListOnClickListener(cur.getName(), cur
					.getDescription(), "Attaque: "
					+ String.valueOf(cur.getAtk()), "Defence: "
					+ String.valueOf(cur.getDef()), "Vie: "
					+ String.valueOf(cur.getHp())));

			cardListLayout.addView(t);

		}

		return view;
	}

	private class CardListOnClickListener implements OnClickListener {

		private CharSequence l_name;
		private CharSequence l_description;
		private CharSequence l_attaque;
		private CharSequence l_defence;
		private CharSequence l_vie;

		public CardListOnClickListener(CharSequence name,
				CharSequence description, CharSequence attaque,
				CharSequence defence, CharSequence vie) {
			l_name = name;
			l_description = description;
			l_attaque = attaque;
			l_defence = defence;
			l_vie = vie;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			name.setText(l_name);
			description.setText(l_description);
			attaque.setText(l_attaque);
			defence.setText(l_defence);
			vie.setText(l_vie);
		}

	}
}