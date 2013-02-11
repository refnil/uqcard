package com.refnil.uqcard;

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

public class BoardActivity extends Activity implements OnTouchListener {
	private ImageAdapter adapter;
	private float pointY;
	private int phase=1;
	private CardView selectedCard;
	private OnClickListener c = new OnClickListener()
	{

		public void onClick(View v) {
			//FIXER SIZE
			ImageView iv = ((CardView) v).getCardImageView(getApplicationContext(), 200, 100);
			if(phase == 0 && iv.getDrawable() != null && selectedCard != null)
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
					case R.id.opponentFront4 : 
					case R.id.opponentFront5 : attackCard(iv);
												break;
					}
			}
			else
			{
				if((phase == 1 && iv.getDrawable() == null) || (phase == 0 && selectedCard == null))
				{
					
					switch(iv.getId())
					{
					case R.id.playerPhenomenon : placeCard(iv);
												break;
					case R.id.playerBack1:
					case R.id.playerBack2:
					case R.id.playerBack3:
					case R.id.playerFront1:
					case R.id.playerFront2:
					case R.id.playerFront3:
					case R.id.playerFront4: 
					case R.id.playerFront5 : if(phase == 0)
												selectedCard = null;//iv.getId();
											else
												placeCard(iv);
											break;
					}
				}
			}
			
		}
		
	};
	
	private OnLongClickListener lc = new OnLongClickListener(){

		public boolean onLongClick(View v) {
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
        
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   	    View view = inflater.inflate(R.layout.activity_board, null);
   	    setContentView(view);
   	    view.setOnTouchListener(this);       
        
        TextView tv = (TextView) findViewById(R.id.opponentText);
        tv.setText("My opponent");
        tv = (TextView) findViewById(R.id.playerText);
        tv.setText("Me");
        
        
        
        //Opponent
        CardView cd;
        cd= (CardView) findViewById(R.id.opponentBack1);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd.setBackgroundColor(0);
        
        cd.setTag(R.drawable.carreau);

        cd = (CardView) findViewById(R.id.opponentBack2);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        
        cd = (CardView) findViewById(R.id.opponentBack3);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        
        cd = (CardView) findViewById(R.id.opponentFront1);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setTag(R.drawable.carreau);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.opponentFront2);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.opponentFront3);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.opponentFront4);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.opponentFront5);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.opponentGeneral);
        cd.setTag(R.drawable.coeur);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.opponentCemetery);
        cd.setTag(R.drawable.coeur);
        cd.setOnLongClickListener(lc);
        
        //Player
        cd = (CardView) findViewById(R.id.playerBack1);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerBack2);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerBack3);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerFront1);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerFront2);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerFront3);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerFront4);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerFront5);
        cd.setTag(R.drawable.carreau);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerGeneral);
        cd.setTag(R.drawable.coeur);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerPhenomenon);
        cd.setTag(R.drawable.coeur);
        cd.setOnClickListener(c);
        cd.setOnLongClickListener(lc);
        cd = (CardView) findViewById(R.id.playerCemetery);
        cd.setTag(R.drawable.coeur);
        cd.setOnLongClickListener(lc);
        
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
        slider.setOnDrawerOpenListener(new com.refnil.uqcard.SemiClosedSlidingDrawer.OnDrawerOpenListener()
        {

			public void onDrawerOpened() {
				Gallery gallery = (Gallery)findViewById(R.id.Gallery);
				gallery.setScaleY((float) 2);
				gallery.setScaleX((float) 1.5);
				gallery.setTranslationY(55);
			}
        	
        });
        
        slider.setOnDrawerCloseListener(new com.refnil.uqcard.SemiClosedSlidingDrawer.OnDrawerCloseListener()
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
	
	public void attackCard(ImageView iv)
	{
		if(selectedCard != null)
		{

			Toast.makeText(getApplicationContext(), "Attack done", Toast.LENGTH_SHORT).show();
			selectedCard = null;
			
			//TEMP
			phase = 1;
		}
	}
	
	public void placeCard(ImageView iv)
	{
		if(selectedCard != null)
		{
			//SETTER DES VRAI SIZE LA LA
			iv.setImageBitmap(selectedCard.loadBitmapFromView(selectedCard.getCardView(getApplicationContext(), 200, 100)));
			iv.setTag(selectedCard);
			selectedCard = null;
			
			//TEMP
			phase = 0;
		}
	}
	
	public void showCard(ImageView image)
	{
		Intent i = new Intent(getApplicationContext(), FullCardActivity.class);
		int id=(Integer) image.getTag();
		i.putExtra("id",id);
		startActivity(i);
	}

	public boolean onTouch(View arg0, MotionEvent event) {
		 float eventY = event.getY();

		    switch (event.getAction()) {
		    case MotionEvent.ACTION_DOWN:
		      pointY=eventY;
		      return true;
		    case MotionEvent.ACTION_MOVE:
		      break;
		    case MotionEvent.ACTION_UP:
		    	SemiClosedSlidingDrawer slider = (SemiClosedSlidingDrawer) findViewById(R.id.mySlidingDrawer);
		    	Gallery gallery = (Gallery)findViewById(R.id.Gallery);
		    	if(eventY<pointY)
		    	{
		    		if(slider.isOpened())
		    		{
		    		selectedCard = ((ImageAdapter) gallery.getAdapter()).getCardView(gallery.getSelectedItemPosition(), null, null);
		        	slider.animateClose();
		        	pointY =0;
		    		}
		    	}
		      break;
		    default:
		      return false;
		    }
			return false;
	}
}
