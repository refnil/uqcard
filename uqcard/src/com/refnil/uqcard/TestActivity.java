package com.refnil.uqcard;


import com.refnil.uqcard.event.*;
import com.refnil.uqcard.library.Player;
import com.refnil.uqcard.service.IService;
import com.refnil.uqcard.service.UqcardService;
import com.refnil.uqcard.service.UqcardService.LocalBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestActivity extends AbstractBoard{
	
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
		Button b = (Button) findViewById(R.id.endturnbutton);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				em.sendToPlayer(new BeginGameEvent());
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
	public void BeginTurnAction(BeginTurnEvent event) {
		Button b = (Button) findViewById(R.id.Button02);
		b.setEnabled(true);
	}

	@Override
	public void EndTurnAction(EndTurnEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BeginGameAction(BeginGameEvent event) {
		Button b = (Button) findViewById(R.id.Button02);
		b.setEnabled(true);
		b = (Button) findViewById(R.id.endturnbutton);
		b.setEnabled(false);
		
	}

	@Override
	public void EndGameAction(EndGameEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DrawCardAction(DrawCardEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BattleAction(AttackEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PutCardAction(PutCardEvent event) {
		// TODO Auto-generated method stub
		
	}
}
