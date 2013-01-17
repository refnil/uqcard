package com.refnil.uqcard;

import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class CardLongClickListener implements OnLongClickListener{

	static Context c;
	static int p;
	
	CardLongClickListener(Context c,int p)
	{
		CardLongClickListener.c = c;
		CardLongClickListener.p = p;
	}
	public boolean onLongClick(View v) {
		ImageView iv = (ImageView) v;
		int vID = iv.getId();
		if(vID != R.id.playerDeck && vID != R.id.opponentDeck && iv.getDrawable() != null)
			showCard(vID);
		return false;
	}
	

	
	public void showCard(int imageID)
	{
		Toast.makeText(CardClickListener.c, "Fullscreen", Toast.LENGTH_SHORT).show();
	}

}
