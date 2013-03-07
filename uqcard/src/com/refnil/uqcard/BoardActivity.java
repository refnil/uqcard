package com.refnil.uqcard;

import com.refnil.uqcard.R;
import com.refnil.uqcard.event.*;
import com.refnil.uqcard.view.ImageAdapter;
import com.refnil.uqcard.view.SemiClosedSlidingDrawer;
import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Gallery;
import android.widget.TextView;

public class BoardActivity extends BoardEventInterface {
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

	@Override
	public void BeginTurnAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EndTurnAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BeginGameAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EndGameAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DrawCardAction(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BattleAction(int id, int id1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PutCardAction(int id,int pos) {
		// TODO Auto-generated method stub
		
	}
}
