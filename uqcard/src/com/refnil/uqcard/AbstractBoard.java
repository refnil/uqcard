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

public abstract class AbstractBoard extends Activity implements Listener<Event>{
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
	
	public void onClose()
	{
		
	}
	
	public abstract void BeginTurnAction(BeginTurnEvent event);
	
	public abstract void EndTurnAction(EndTurnEvent event);
	
	public abstract void BeginGameAction(BeginGameEvent event);
	
	public abstract void EndGameAction(EndGameEvent event);
	
	public abstract void DrawCardAction(DrawCardEvent event);
	
	public abstract void BattleAction(AttackEvent event);
	
	public abstract void PutCardAction(PutCardEvent event);
	
	final public void SelectedCardAction(SelectedCardEvent event)
	{
		em.setSelectedCard(event.getCard().getCard().getUid(),event.isOpponent());
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
			BeginGameAction((BeginGameEvent)e);	
		
		else if(e.type == Event_Type.END_GAME)
			EndGameAction((EndGameEvent)e);
		
		else if(e.type == Event_Type.BEGIN_TURN)
			BeginTurnAction((BeginTurnEvent)e);
		
		else if(e.type == Event_Type.END_TURN)
			EndTurnAction((EndTurnEvent)e);
		
		else if(e.type == Event_Type.DRAW_CARD)
			DrawCardAction((DrawCardEvent)e);
		
		else if(e.type == Event_Type.DECLARE_ATTACK)
			BattleAction((AttackEvent)e);
		
		else if(e.type == Event_Type.PUT_CARD)
			PutCardAction((PutCardEvent)e);
			
	}
}
