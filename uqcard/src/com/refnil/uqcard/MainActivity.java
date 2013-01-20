package com.refnil.uqcard;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

import com.refnil.uqcard.library.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView t = (TextView)findViewById(R.id.MainTextView);
		t.setText(mainTest.shishi());
		
		
		Intent i = new Intent(getApplicationContext(), HandActivity.class);
        // passing array index
        //startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hand, menu);
		return true;
	}

}
