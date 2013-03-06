package com.refnil.uqcard;

import com.refnil.uqcard.R;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.view.CardView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class FullCardActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent i = this.getIntent();
		Card card = i.getExtras().getParcelable("Card");
		CardView cardView = new CardView(getApplicationContext(), card);

		setContentView(cardView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_full_card, menu);
		return true;
	}
}
