package com.refnil.uqcard.board;

import com.refnil.uqcard.R;
import com.refnil.uqcard.R.drawable;
import com.refnil.uqcard.R.id;
import com.refnil.uqcard.R.layout;
import com.refnil.uqcard.R.menu;
import com.refnil.uqcard.board.SemiClosedSlidingDrawer.OnDrawerCloseListener;
import com.refnil.uqcard.board.SemiClosedSlidingDrawer.OnDrawerOpenListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class BoardViewActivity extends Activity {
	private ImageAdapter adapter;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   	    View view = inflater.inflate(R.layout.activity_board, null);
   	    setContentView(view);
   	    view.setOnTouchListener(new BoardOnTouchListener((SemiClosedSlidingDrawer)findViewById(R.id.mySlidingDrawer),(Gallery) findViewById(R.id.Gallery)));       
        
        TextView tv = (TextView) findViewById(R.id.opponentText);
        tv.setText("My opponent");
        tv = (TextView) findViewById(R.id.playerText);
        tv.setText("Me");
        
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
        iv = (ImageView) findViewById(R.id.opponentFront5);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentGeneral);
        iv.setTag(R.drawable.coeur);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.opponentCemetery);
        iv.setTag(R.drawable.coeur);
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
        iv = (ImageView) findViewById(R.id.playerFront5);
        iv.setTag(R.drawable.carreau);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerGeneral);
        iv.setTag(R.drawable.coeur);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerPhenomenon);
        iv.setTag(R.drawable.coeur);
        iv.setOnClickListener(c);
        iv.setOnLongClickListener(lc);
        iv = (ImageView) findViewById(R.id.playerCemetery);
        iv.setTag(R.drawable.coeur);
        iv.setOnLongClickListener(lc);
        
        // Hand initialisation
        
       
        
        Gallery gallery = (Gallery)findViewById(R.id.Gallery);
        adapter = new ImageAdapter(this);
        gallery.setAdapter(adapter);   
        
        gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(), FullCardActivity.class);
				int id = (int) adapter.getItemId(arg2);
				i.putExtra("id", id);
				startActivity(i);
				
			}
        });
        
        SemiClosedSlidingDrawer slider = (SemiClosedSlidingDrawer) findViewById(R.id.mySlidingDrawer);
        slider.setOnDrawerOpenListener(new com.refnil.uqcard.board.SemiClosedSlidingDrawer.OnDrawerOpenListener()
        {

			public void onDrawerOpened() {
				Gallery gallery = (Gallery)findViewById(R.id.Gallery);
				gallery.setScaleY((float) 2);
				gallery.setScaleX((float) 1.5);
				gallery.setTranslationY(55);
			}
        	
        });
        
        slider.setOnDrawerCloseListener(new com.refnil.uqcard.board.SemiClosedSlidingDrawer.OnDrawerCloseListener()
        {

			public void onDrawerClosed() {
				Gallery gallery = (Gallery)findViewById(R.id.Gallery);
				gallery.setScaleY((float) 0.9);
				gallery.setScaleX((float) 0.9);
				gallery.setTranslationY(-7);
			}
        	
        });
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_board, menu);
        return true;
    }
}
