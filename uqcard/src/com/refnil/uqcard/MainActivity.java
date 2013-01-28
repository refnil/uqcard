package com.refnil.uqcard;

import com.refnil.uqcard.board.BoardActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button b = (Button) findViewById(R.id.buttonSingle);
		b.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), BoardActivity.class);
		        startActivity(i);
				
			}
			
		});
		
		b = (Button) findViewById(R.id.buttonMulti);
		b.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Multi", Toast.LENGTH_SHORT).show();
				//Intent i = new Intent(getApplicationContext(), BoardActivity.class);
		        //startActivity(i);
				
			}
			
		});
		
		b = (Button) findViewById(R.id.buttonEditor);
		b.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Editor", Toast.LENGTH_SHORT).show();
				//Intent i = new Intent(getApplicationContext(), BoardActivity.class);
		        //startActivity(i);
				
			}
			
		});
		
		b = (Button) findViewById(R.id.buttonOptions);
		b.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Options", Toast.LENGTH_SHORT).show();
				//Intent i = new Intent(getApplicationContext(), BoardActivity.class);
		        //startActivity(i);
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hand, menu);
		return true;
	}

}
