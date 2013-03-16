package com.refnil.uqcard.view;

import java.util.List;
import com.refnil.uqcard.R;
import com.refnil.uqcard.TabsActivity;
import com.refnil.uqcard.data.Board;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.DummyCardStore;
import com.refnil.uqcard.event.AttackEvent;
import com.refnil.uqcard.event.BeginGameEvent;
import com.refnil.uqcard.event.BeginTurnEvent;
import com.refnil.uqcard.event.BoardOnTouchListener;
import com.refnil.uqcard.event.CardViewOnLongClickListener;
import com.refnil.uqcard.event.CardViewOpponentOnClickListener;
import com.refnil.uqcard.event.CardViewPlayerOnClickListener;
import com.refnil.uqcard.event.DrawCardEvent;
import com.refnil.uqcard.event.EndGameEvent;
import com.refnil.uqcard.event.EndTurnEvent;
import com.refnil.uqcard.event.EventManager;
import com.refnil.uqcard.event.PutCardEvent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridLayout;
import android.widget.TextView;

public class BoardFragment extends Fragment{
	protected final static String TAG = "BoardActivity";
	protected EventManager em;
	protected List<CardView> onBoard;
	protected Board board;
	
	public BoardFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activity_board, container,false);
		view.setOnTouchListener(new BoardOnTouchListener(
				(SemiClosedSlidingDrawer) view.findViewById(R.id.mySlidingDrawer),
				(Gallery) view.findViewById(R.id.Gallery),em));

		TextView tv = (TextView) view.findViewById(R.id.opponentText);
		tv.setText("My opponent");
		tv = (TextView) view.findViewById(R.id.playerText);
		tv.setText("Me");

		GridLayout glo = (GridLayout) view.findViewById(R.id.gridLayoutBoardOpponent);
		for(int i=0;i<glo.getChildCount();i++)
		{
			glo.getChildAt(i).setOnClickListener(new CardViewOpponentOnClickListener(em));
		}
		
		GridLayout glp = (GridLayout) view.findViewById(R.id.gridLayoutBoardPlayer);
		for(int i=0;i<glp.getChildCount();i++)
		{
			glp.getChildAt(i).setOnClickListener(new CardViewPlayerOnClickListener(em));
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
		return view;
	}
	
	
	public void StartGameButton(View v)
	{
		Button b = (Button) v;
		b.setText(R.string.endturn);
		b.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				em.sendToPlayer(new EndTurnEvent());
				
			}
			
		});
		em.sendToPlayer(new BeginGameEvent());
	}
	
	
	public void BeginTurnAction(BeginTurnEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void EndTurnAction(EndTurnEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void BeginGameAction(BeginGameEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void EndGameAction(EndGameEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void DrawCardAction(DrawCardEvent event) {
		Gallery gallery = (Gallery) getActivity().findViewById(R.id.Gallery);
		final int size = gallery.getAdapter().getCount()+1;
		CardView tab[] = new CardView[size];
		tab = ((ImageAdapter)gallery.getAdapter()).getPics();
		
		DummyCardStore store = new DummyCardStore();
		
		Card c = store.getCard(event.getCardID());
		c.setUid(event.getCardUID());
		int index = this.board.getPlayerHandCards().indexOf(c);
		tab[size-2] = new CardView(getActivity().getApplicationContext(),this.board.getPlayerHandCards().get(index));
		ImageAdapter adapter = new ImageAdapter(getActivity().getApplicationContext(),tab);
		gallery.setAdapter(adapter);
	}

	
	public void BattleAction(AttackEvent event) {

		//Doit s'updater normalement � cause des r�f�rences
		/*Card c = CardStoreBidon.getCard(event.getOpponent());
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
		
		gv.addView(cv, index);*/
		
	}

	public void PutCardAction(PutCardEvent event) 
	{
		Gallery gallery = (Gallery)getActivity().findViewById(R.id.Gallery);
		ImageAdapter adapter = (ImageAdapter) gallery.getAdapter();
		CardView cv = null;
		DummyCardStore store = new DummyCardStore();
		for(int i=0;i<adapter.getCount();i++)
		{
			if(((CardView)adapter.getItem(i)).getCard().getUid() == event.getCardUID())
			{
				cv = ((CardView)adapter.getItem(i));
				final int size = gallery.getAdapter().getCount()-1;
				CardView tab[] = new CardView[size];
				for(int j=0;j<adapter.getCount();j++)
				{
					if(j>i)
						tab[j-1] = (CardView)adapter.getItem(j);
					else if(j != i)
							tab[j] = (CardView)adapter.getItem(j);
				}
				break;
			}
		}
		GridLayout gv;
		if(cv == null)
		{
			gv = (GridLayout) getActivity().findViewById(R.id.gridLayoutBoardOpponent);
			cv = new CardView(getActivity().getApplicationContext(),store.getCard(event.getCardID()));
			cv.setOnClickListener(new CardViewOpponentOnClickListener(em));
		}
		else
		{
			gv = (GridLayout) getActivity().findViewById(R.id.gridLayoutBoardPlayer);
			cv.setOnClickListener(new CardViewPlayerOnClickListener(em));
		}
		cv.setOnLongClickListener(new CardViewOnLongClickListener((TabsActivity) this.getActivity()));
		gv.addView(cv, event.getPosition());
		em.setSelectedCardHand(-1);
		em.setSelectedCardHandUID(-1);
	}

}
