package com.refnil.uqcard;

import com.refnil.uqcard.data.Deck;
import com.refnil.uqcard.data.test.DeckTest;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeckListActivity extends Activity {

	private Deck deck;
	private LinearLayout cardListLayout;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_list);
		deck = new Deck(DeckTest.createDeck(),"Test");
		cardListLayout = (LinearLayout) findViewById(R.id.cardListLayout);
		cardListLayout.setOrientation(LinearLayout.VERTICAL);
		for(int i=0;i<40;i++)
		{
			TextView t = new TextView(this);
			t.setText(String.valueOf(deck.drawCardAt(0).get_Id()));
			t.setClickable(true);
			t.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					TextView t =(TextView)findViewById(R.id.cardDesc);
					t.setText(((TextView)v).getText());

				}
			});

			cardListLayout.addView(t);

		}

	}

}