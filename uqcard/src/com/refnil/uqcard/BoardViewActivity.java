package com.refnil.uqcard;

import com.refnil.uqcard.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Gallery;
import android.widget.TextView;

public class BoardViewActivity extends Activity {
	private ImageAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_board, null);
		setContentView(view);
		view.setOnTouchListener(new BoardOnTouchListener(
				(SemiClosedSlidingDrawer) findViewById(R.id.mySlidingDrawer),
				(Gallery) findViewById(R.id.Gallery)));

		TextView tv = (TextView) findViewById(R.id.opponentText);
		tv.setText("My opponent");
		tv = (TextView) findViewById(R.id.playerText);
		tv.setText("Me");

		// TODO SET ONLONGCLICK QUAND ON AJOUTE CARTE
		// Opponent
		Context c = getApplicationContext();
		CardView iv = (CardView) findViewById(R.id.opponentBack1);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.opponentBack2);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.opponentBack3);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.opponentFront1);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.opponentFront2);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.opponentFront3);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.opponentFront4);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.opponentFront5);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.opponentGeneral);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv.setOnLongClickListener(new CardViewOnLongClickListener(c));
		iv = (CardView) findViewById(R.id.opponentPhenomenon);
		iv.setOnLongClickListener(new CardViewOnLongClickListener(c));
		iv = (CardView) findViewById(R.id.opponentCemetery);
		iv.setOnLongClickListener(new CardViewOnLongClickListener(c));

		// Player
		iv = (CardView) findViewById(R.id.playerBack1);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerBack2);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerBack3);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerFront1);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerFront2);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerFront3);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerFront4);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerFront5);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerGeneral);
		iv.setOnLongClickListener(new CardViewOnLongClickListener(c));
		iv = (CardView) findViewById(R.id.playerPhenomenon);
		iv.setOnClickListener(new CardViewOnClickListener());
		iv = (CardView) findViewById(R.id.playerCemetery);
		iv.setOnLongClickListener(new CardViewOnLongClickListener(c));

		// Hand initialisation

		Gallery gallery = (Gallery) findViewById(R.id.Gallery);
		adapter = new ImageAdapter(this);
		gallery.setAdapter(adapter);
		gallery.setOnItemClickListener(new GalleryOnItemClickListener(
				getApplicationContext(), gallery));

		SemiClosedSlidingDrawer slider = (SemiClosedSlidingDrawer) findViewById(R.id.mySlidingDrawer);
		slider.setOnDrawerOpenListener(new SliderOnDrawerOpenListener(
				(Gallery) findViewById(R.id.Gallery)));
		slider.setOnDrawerCloseListener(new SliderOnDrawerCloseListener(
				(Gallery) findViewById(R.id.Gallery)));

	}
}
