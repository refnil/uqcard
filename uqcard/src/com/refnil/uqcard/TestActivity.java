package com.refnil.uqcard;

import java.util.List;

import com.refnil.uqcard.data.Board;
import com.refnil.uqcard.event.AttackEvent;
import com.refnil.uqcard.event.EndTurnEvent;
import com.refnil.uqcard.event.Event;
import com.refnil.uqcard.event.EventManager;
import com.refnil.uqcard.event.Event_Type;
import com.refnil.uqcard.event.GameConditionEvent;
import com.refnil.uqcard.event.SelectedCardEvent;
import com.refnil.uqcard.library.Player;
import com.refnil.uqcard.service.IService;
import com.refnil.uqcard.service.UqcardService;
import com.refnil.uqcard.service.UqcardService.LocalBinder;
import com.refnil.uqcard.view.CardView;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestActivity extends BoardEventInterface{
	
	@Override
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
		
		setContentView(R.layout.activity_test);
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				GameConditionEvent gc = em.getGameConditionEvent();
				if(gc.type != Event_Type.BEGIN_GAME)
					gc.nextPhase();
				em.sendToPlayer(gc);
			}
			
		});
		
		b = (Button) findViewById(R.id.Button02);
		b.setEnabled(false);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				em.sendToPlayer(new EndTurnEvent());
				
			}
			
		});
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
	public void DrawCardAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BattleAction() {
		// TODO Auto-generated method stub
		
	}	
}
