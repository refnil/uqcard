package com.refnil.uqcard;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class BoardActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        int phase = 1;
        CardClickListener c = new CardClickListener(getApplicationContext(),phase);
        CardLongClickListener c1 = new CardLongClickListener(getApplicationContext(),phase);
        
        TextView tv = (TextView) findViewById(R.id.opponentText);
        tv.setText("My opponent");
        tv = (TextView) findViewById(R.id.playerText);
        tv.setText("Me, myself and I");
        
        //Opponent
        ImageView iv = (ImageView) findViewById(R.id.opponentBack1);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.opponentBack2);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.opponentBack3);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.opponentFront1);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.opponentFront2);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.opponentFront3);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.opponentFront4);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.opponentGeneral);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        
        //Player
        iv = (ImageView) findViewById(R.id.playerBack1);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.playerBack2);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.playerBack3);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.playerFront1);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.playerFront2);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.playerFront3);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.playerFront4);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.playerGeneral);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        iv = (ImageView) findViewById(R.id.playerPhenomenon);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(c1);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_board, menu);
        return true;
    }
}
