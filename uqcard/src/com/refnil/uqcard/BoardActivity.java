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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridLayout;
import android.widget.TextView;

public class BoardActivity extends AbstractBoard {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_board, null);
		setContentView(view);
		view.setOnTouchListener(new BoardOnTouchListener(
				(SemiClosedSlidingDrawer) findViewById(R.id.mySlidingDrawer),
				(Gallery) findViewById(R.id.Gallery),em));

		TextView tv = (TextView) findViewById(R.id.opponentText);
		tv.setText("My opponent");
		tv = (TextView) findViewById(R.id.playerText);
		tv.setText("Me");

		GridLayout glo = (GridLayout) findViewById(R.id.gridLayoutBoardOpponent);
		for(int i=0;i<glo.getChildCount();i++)
		{
			glo.getChildAt(i).setOnClickListener(new CardViewOpponentOnClickListener(em));
			glo.getChildAt(i).setOnLongClickListener(new CardViewOnLongClickListener(getApplicationContext()));
		}
		
		GridLayout glp = (GridLayout) findViewById(R.id.gridLayoutBoardPlayer);
		for(int i=0;i<glp.getChildCount();i++)
		{
			glp.getChildAt(i).setOnClickListener(new CardViewPlayerOnClickListener(em));
			glo.getChildAt(i).setOnLongClickListener(new CardViewOnLongClickListener(getApplicationContext()));
		}

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

		Card c = CardStoreBidon.getCard(event.getOpponent());
		CardView cv = new CardView(getApplicationContext(),c);
		int index;
		GridLayout gv ;
		if(event.isYourAttack())
		{
			gv = (GridLayout) findViewById(R.id.gridLayoutBoardOpponent);
			index = gv.indexOfChild(cv);
			cv.setOnClickListener(new CardViewOpponentOnClickListener(em));
		}
		else
		{
			gv = (GridLayout) findViewById(R.id.gridLayoutBoardPlayer);
			index = gv.indexOfChild(cv);
			cv.setOnClickListener(new CardViewPlayerOnClickListener(em));
		}
		cv.setClickable(true);
		cv.setOnLongClickListener(new CardViewOnLongClickListener(getApplicationContext()));
		gv.removeViewAt(index);
		
		gv.addView(cv, index);
		
	}

	@Override
	public void PutCardAction(PutCardEvent event) {
		Card c = CardStoreBidon.getCard(event.getCard());
		CardView cv = new CardView(getApplicationContext(),c);
		GridLayout gv;
		if(event.isOpponent())
			gv = (GridLayout) findViewById(R.id.gridLayoutBoardOpponent);
		else
			gv = (GridLayout) findViewById(R.id.gridLayoutBoardPlayer);
		gv.addView(cv, event.getPosition());
	}
	
	public void StartGameButton(View v)
	{
		Button b = (Button) findViewById(R.id.endturnbutton);
		b.setText(R.string.endturn);
		b.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				em.sendToPlayer(new EndTurnEvent());
				
			}
			
		});
		em.sendToPlayer(new BeginGameEvent());
	}

}
