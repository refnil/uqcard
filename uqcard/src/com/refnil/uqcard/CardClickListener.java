package com.refnil.uqcard;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class CardClickListener implements OnClickListener{

	static Context c;
	static int p;
	static Integer[] attackPositions= {R.id.opponentGeneral,R.id.opponentBack1,R.id.opponentBack2,R.id.opponentBack3,R.id.opponentFront1,R.id.opponentFront2,R.id.opponentFront3,R.id.opponentFront4};
	static Integer[] placeCards= {R.id.playerPhenomenon,R.id.playerBack1,R.id.playerBack2,R.id.playerBack3,R.id.playerFront1,R.id.playerFront2,R.id.playerFront3,R.id.playerFront4};
	CardClickListener(Context c, int p)
	{
		CardClickListener.c = c;
		CardClickListener.p = p;
	}
	
	public void onClick(View v) {
		boolean availableCard = false;
		ImageView iv = (ImageView)v;
			
		
		switch(CardClickListener.p)
		{
			case 0: if(iv.getDrawable() != null)
					{
						for(int i=0;i<attackPositions.length;i++)
						{
							if(attackPositions[i] == v.getId())
								availableCard = true;
						}
						if(availableCard)
							attackCard();
					}
					break;
					
			case 1: if(iv.getDrawable()==null)
					{
						for(int i=0;i<placeCards.length;i++)
						{
							if(placeCards[i] == v.getId())
								availableCard = true;
						}
						if(availableCard)
							placeCard();
					}
					break;
		}
		
	}
	
	public void attackCard()
	{
		Toast.makeText(CardClickListener.c, "Attack", Toast.LENGTH_SHORT).show();
	}
	
	public void placeCard()
	{
		Toast.makeText(CardClickListener.c, "Place", Toast.LENGTH_SHORT).show();
	}

}
