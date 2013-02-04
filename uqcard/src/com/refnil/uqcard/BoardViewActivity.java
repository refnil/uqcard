package com.refnil.uqcard;

import com.refnil.uqcard.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;

public class BoardViewActivity extends Activity {
	private ImageAdapter adapter;
	private EventManager em;
	private Board board;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		em = new EventManager();
		board = new Board(getApplicationContext(),this);
		
		/************************** TEMP ZONE **********************************/
		setContentView(R.layout.test);
		Button b = (Button) findViewById(R.id.button1);
		b.setEnabled(false);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				GameConditionEvent gc = em.getGameConditionEvent();
				if(gc.type != Event_Type.BEGIN_GAME)
					gc.nextPhase();
				em.sendPhaseToPlayer(gc);
			}
			
		});
		
		b = (Button) findViewById(R.id.Button01);
		b.setEnabled(false);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				TurnPhaseEvent tp = em.getTurnPhaseEvent();
				if(tp.type != Event_Type.BEGIN_TURN)
					tp.nextPhase();
				em.sendPhaseToPlayer(tp);
				
			}
			
		});
		
		b = (Button) findViewById(R.id.Button02);
		b.setEnabled(false);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				TurnPhaseEvent tp = em.getTurnPhaseEvent();
				if(tp.type != Event_Type.END_TURN)
					tp.nextPhase();
				em.sendPhaseToPlayer(tp);
				
			}
			
		});
		
		b = (Button) findViewById(R.id.button2);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Button b = (Button) findViewById(R.id.button2);
				board.setPlayerID(1);
				b.setEnabled(false);
				b = (Button) findViewById(R.id.button3);
				b.setEnabled(false);
			}
			
		});
		
		b = (Button) findViewById(R.id.button3);
		b.setEnabled(false);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Button b = (Button) findViewById(R.id.button2);
				board.setPlayerID(2);
				b.setEnabled(false);
				b = (Button) findViewById(R.id.button3);
				b.setEnabled(false);
				GameConditionEvent gc = em.getGameConditionEvent();
				if(gc.type != Event_Type.BEGIN_GAME)
					gc.nextPhase();
				em.sendPhaseToPlayer(gc);
			}
			
		});
		
		b = (Button) findViewById(R.id.Button03);
		b.setEnabled(false);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				GameConditionEvent gc = em.getGameConditionEvent();
				if(gc.type != Event_Type.END_GAME)
					gc.nextPhase();
				em.sendPhaseToPlayer(gc);
				
			}
			
		});
		
		/***************************** END *************************************/
		/*LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
				(Gallery) findViewById(R.id.Gallery)));*/

	}
	
	public void onMessage(Event e)
	{
		if(e instanceof GameConditionEvent)
		{
			if(e.type == Event_Type.BEGIN_GAME)
			{
				Button b = (Button) findViewById(R.id.Button03);
				b.setEnabled(true);
				b = (Button) findViewById(R.id.Button01);
				if(board.getPlayerID() == 1)
					b.setEnabled(true);
				else
					b.setEnabled(false);
				b = (Button) findViewById(R.id.Button02);
				b.setEnabled(false);
				b = (Button) findViewById(R.id.button1);
				b.setEnabled(false);
			}
			else
			{
				Button b = (Button) findViewById(R.id.button1);
				b.setEnabled(true);
				b = (Button) findViewById(R.id.Button01);
				b.setEnabled(false);
				b = (Button) findViewById(R.id.Button02);
				b.setEnabled(false);
				b = (Button) findViewById(R.id.Button03);
				b.setEnabled(false);
			}
		}
		else
		{
				if(e instanceof TurnPhaseEvent)
				{
					if(e.type == Event_Type.BEGIN_TURN && e.id == board.getPlayerID())
					{
						Button  b = (Button) findViewById(R.id.Button01);
						b.setEnabled(false);
						b = (Button) findViewById(R.id.Button02);
						b.setEnabled(true);
					}
					else
					{
						if(e.type == Event_Type.END_TURN)
						{
							Button b = (Button) findViewById(R.id.Button01);
							if(e.id != board.getPlayerID())
								b.setEnabled(true);
							else
								b.setEnabled(false);
							b = (Button) findViewById(R.id.Button02);
							b.setEnabled(false);
						}
					}
				}
		}
	}
	
	public void onClose()
	{
		
	}
}
