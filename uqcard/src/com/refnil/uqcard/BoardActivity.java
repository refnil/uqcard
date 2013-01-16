package com.refnil.uqcard;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class BoardActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        CardClickListener c = new CardClickListener(getApplicationContext());
        
        //Opponent
        ImageView iv = (ImageView) findViewById(R.id.opponentBack1);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.opponentBack2);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.opponentBack3);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.opponentFront1);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.opponentFront2);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.opponentFront3);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.opponentFront4);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.opponentGeneral);
        iv.setOnClickListener(c);
        
        //Player
        iv = (ImageView) findViewById(R.id.playerBack1);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.playerBack2);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.playerBack3);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.playerFront1);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.playerFront2);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.playerFront3);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.playerFront4);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.playerGeneral);
        iv.setOnClickListener(c);
        iv = (ImageView) findViewById(R.id.playerPhenomenon);
        iv.setOnClickListener(c);
        
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_board, menu);
        return true;
    }
    
    public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "Attack", Toast.LENGTH_SHORT).show();
	}
}
