package com.refnil.uqcard;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CardClickListener implements OnClickListener{

	Context c;
	CardClickListener(Context c)
	{
		this.c = c;
	}
	
	public void onClick(View v) {
		if(v.getId() == R.id.opponentBack1)
		Toast.makeText(this.c, "Attack", Toast.LENGTH_SHORT).show();
		
	}

}
