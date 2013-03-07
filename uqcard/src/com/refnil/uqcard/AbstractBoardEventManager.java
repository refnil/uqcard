package com.refnil.uqcard;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;

import com.refnil.uqcard.data.Board;
import com.refnil.uqcard.event.*;
import com.refnil.uqcard.library.Listener;
import com.refnil.uqcard.library.Player;
import com.refnil.uqcard.service.IService;
import com.refnil.uqcard.service.UqcardService;
import com.refnil.uqcard.service.UqcardService.LocalBinder;
import com.refnil.uqcard.view.CardView;

public abstract class AbstractBoardEventManager extends Activity implements Listener<Event>{
	protected final static String TAG = "BoardActivity";
	protected EventManager em;
	protected List<CardView> onBoard;
	protected Board board;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ServiceConnection mConnection = new ServiceConnection() {

			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				Log.i(TAG,"BoardViewActivity est connecter au service.");
				IService mService = (IService) ((LocalBinder) service).getService();
				Player p = mService.getPlayer();
				em = new EventManager(p);
				setBoard(p.getBoard());
				board.temp = true;
			}

			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		Intent intent = new Intent(this, UqcardService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_test, menu);
		return true;
	}	
	
	public void onClose()
	{
		
	}
	
	public abstract void BeginTurnAction();
	
	public abstract void EndTurnAction();
	
	public abstract void BeginGameAction();
	
	public abstract void EndGameAction();
	
	public abstract void DrawCardAction(int id);
	
	public abstract void BattleAction(int id,int id1);
	
	public abstract void PutCardAction(int id,int pos);
	
	final public void SelectedCardAction(int cardViewID)
	{
		em.setSelectedCard(cardViewID);
	}
	
	final protected void setBoard(Board board2) {
		// TODO Auto-generated method stub
		board = board2;
		board.subscribe(this);
	}
	
	final public void onMessage(final Event e)
	{
		this.runOnUiThread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				handleEvent(e);
			}
			
		});
	}
	
	final public void handleEvent(Event e){
		if(e.type == Event_Type.BEGIN_GAME)
			BeginGameAction();	
		
		else if(e.type == Event_Type.END_GAME)
			EndGameAction();
		
		else if(e.type == Event_Type.BEGIN_TURN)
			BeginTurnAction();
		
		else if(e.type == Event_Type.END_TURN)
			EndTurnAction();
		
		else if(e.type == Event_Type.DRAW_CARD)
			DrawCardAction(((DrawCardEvent)e).id);
		
		else if(e.type == Event_Type.DECLARE_ATTACK)
			BattleAction(((AttackEvent)e).getPlayer(),((AttackEvent)e).getOpponent());
		
		else if(e.type == Event_Type.PUT_CARD)
			PutCardAction(((PutCardEvent)e).getCard(),((PutCardEvent)e).getPosition());
			
	}
}
