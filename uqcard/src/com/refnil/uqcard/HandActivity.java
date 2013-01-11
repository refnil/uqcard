package com.refnil.uqcard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class HandActivity extends Activity implements OnTouchListener{
	private ImageAdapter adapter;
	private float pointX, pointY;
  
   	@Override
    public void onCreate(Bundle savedInstanceState) {
   		super.onCreate(savedInstanceState);
   		
   		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   	    View view = inflater.inflate(R.layout.activity_hand, null);
   	    setContentView(view);
   	    view.setOnTouchListener(this);       
        
        Gallery gallery = (Gallery)findViewById(R.id.Gallery01);
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
    }

	public boolean onTouch(View arg0, MotionEvent event) {
		float eventX = event.getX();
	    float eventY = event.getY();

	    switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
	      pointX=eventX;
	      pointY=eventY;
	      return true;
	    case MotionEvent.ACTION_MOVE:
	      //Nothing to do
	      break;
	    case MotionEvent.ACTION_UP:
	    	Toast.makeText(this, "Down to up", Toast.LENGTH_SHORT).show();
	    	Gallery gallery = (Gallery)findViewById(R.id.Gallery01);
	    	ImageView image = (ImageView)findViewById(R.id.ImageView01);
	    	if(eventY<pointY)
	        	image.setImageResource((int)gallery.getSelectedItemId());
	      break;
	    default:
	      return false;
	    }
		return false;
	}   
}