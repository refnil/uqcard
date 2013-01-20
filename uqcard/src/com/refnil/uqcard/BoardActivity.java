package com.refnil.uqcard;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BoardActivity extends Activity {
	private int phase=0;
	private OnClickListener c = new OnClickListener()
	{

		public void onClick(View v) {
			ImageView iv = (ImageView) v;
			if(phase == 0 && iv.getDrawable() != null)
			{
					switch(iv.getId())
					{
					case R.id.opponentGeneral :
					case R.id.opponentBack1 : 
					case R.id.opponentBack2 :
					case R.id.opponentBack3 :
					case R.id.opponentFront1 :
					case R.id.opponentFront2 :
					case R.id.opponentFront3 :
					case R.id.opponentFront4 : attackCard();
												break;
					}
			}
			else
			{
				if(phase == 1 && iv.getDrawable() == null)
				{
					
					switch(iv.getId())
					{
					case R.id.playerPhenomenon :
					case R.id.playerBack1:
					case R.id.playerBack2:
					case R.id.playerBack3:
					case R.id.playerFront1:
					case R.id.playerFront2:
					case R.id.playerFront3:
					case R.id.playerFront4: placeCard();
											break;
					}
				}
			}
			
		}
		
	};
	
	private OnLongClickListener lc = new OnLongClickListener(){

		public boolean onLongClick(View v) {
			Toast.makeText(getApplicationContext(), "longclick", Toast.LENGTH_SHORT).show();
			ImageView iv = (ImageView) v;
			int vID = iv.getId();
			if(vID != R.id.playerDeck && vID != R.id.opponentDeck && iv.getDrawable() != null)
				showCard(iv);
			return true;
		}
		
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        
        TextView tv = (TextView) findViewById(R.id.opponentText);
        tv.setText("My opponent");
        tv = (TextView) findViewById(R.id.playerText);
        tv.setText("Me, myself and I");
        
        //Opponent
        ImageView iv = (ImageView) findViewById(R.id.opponentBack1);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentBack2);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentBack3);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentFront1);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setTag(R.drawable.carreau);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentFront2);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentFront3);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentFront4);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentGeneral);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        
        //Player
        iv = (ImageView) findViewById(R.id.playerBack1);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerBack2);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerBack3);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerFront1);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerFront2);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerFront3);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerFront4);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerGeneral);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerPhenomenon);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_board, menu);
        return true;
    }
	
	public void attackCard()
	{
		Toast.makeText(getApplicationContext(), "Attack", Toast.LENGTH_SHORT).show();
	}
	
	public void placeCard()
	{
		Toast.makeText(getApplicationContext(), "Place", Toast.LENGTH_SHORT).show();
	}
	
	public void showCard(ImageView image)
	{
		Intent i = new Intent(getApplicationContext(), FullCardActivity.class);
		int id=(Integer) image.getTag();
		i.putExtra("id",id);
		startActivity(i);
	}
}
