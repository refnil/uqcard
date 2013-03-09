package com.refnil.uqcard;

import com.refnil.uqcard.R;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.CardStoreBidon;
import com.refnil.uqcard.event.*;
import com.refnil.uqcard.view.CardView;
import com.refnil.uqcard.view.ImageAdapter;
import com.refnil.uqcard.view.SemiClosedSlidingDrawer;
import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.TextView;

public class BoardActivity extends AbstractBoard {
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

		

		// Hand (For tests.)
		/*
		Gallery gallery = (Gallery) findViewById(R.id.Gallery);
		CardView tab[]  = {new CardView(getApplicationContext(), "nom", "desc", "flav", 2)};
		adapter = new ImageAdapter(this,tab);
		gallery.setAdapter(adapter);
		gallery.setOnItemClickListener(new GalleryOnItemClickListener(
				getApplicationContext(), gallery));

		SemiClosedSlidingDrawer slider = (SemiClosedSlidingDrawer) findViewById(R.id.mySlidingDrawer);
		slider.setOnDrawerOpenListener(new SliderOnDrawerOpenListener(
				(Gallery) findViewById(R.id.Gallery)));
		slider.setOnDrawerCloseListener(new SliderOnDrawerCloseListener(
				(Gallery) findViewById(R.id.Gallery)));*/

	}

	@Override
	public void BeginTurnAction(BeginTurnEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EndTurnAction(EndTurnEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BeginGameAction(BeginGameEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EndGameAction(EndGameEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DrawCardAction(DrawCardEvent event) {
		Gallery gallery = (Gallery) findViewById(R.id.Gallery);
		final int size = gallery.getAdapter().getCount()+1;
		CardView tab[] = new CardView[size];
		tab = ((ImageAdapter)gallery.getAdapter()).getPics();
		
		Card c = CardStoreBidon.getCard(event.getCard());
		tab[size-2] = new CardView(getApplicationContext(),c);
		ImageAdapter adapter = new ImageAdapter(this,tab);
		gallery.setAdapter(adapter);
	}

	@Override
	public void BattleAction(AttackEvent event) {
		GridView gv ;
		if(event.isYourAttack())
			gv = (GridView) findViewById(R.id.gridViewBoardOpponent);
		else
			gv = (GridView) findViewById(R.id.gridViewBoardPlayer);
		Card c = CardStoreBidon.getCard(event.getOpponent());
		CardView cv = new CardView(getApplicationContext(),c);
		int index = gv.indexOfChild(cv);
		((ImageAdapter)gv.getAdapter()).replaceItem(cv, index);
		
	}

	@Override
	public void PutCardAction(PutCardEvent event) {
		Card c = CardStoreBidon.getCard(event.getCard());
		CardView cv = new CardView(getApplicationContext(),c);
		GridView gv;
		if(event.isOpponent())
			gv = (GridView) findViewById(R.id.gridViewBoardOpponent);
		else
			gv = (GridView) findViewById(R.id.gridViewBoardPlayer);
		gv.addView(cv, event.getPosition());
	}

}
