package com.refnil.uqcard.event;

import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.DummyCardStore;
import com.refnil.uqcard.view.CardView;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CardViewPlayerOnClickListener implements OnClickListener {
	
	EventManager em;
	DummyCardStore CardStoreBidon = new DummyCardStore();

	public CardViewPlayerOnClickListener(EventManager em) {
		this.em = em;
	}
	
	public void onClick(View v) {
		if(em != null)
		{
			if(v instanceof ImageView )
			{
				GridLayout gl = (GridLayout) v.getParent();
				int position = gl.indexOfChild(v);
				em.setSelectedCard(position, false);
			}
			else
			{
				GridLayout gl = (GridLayout)v.getParent();
				for(int i=0;i<gl.getChildCount();i++)
				{
					if(gl.getChildAt(i) instanceof TextView)
					{
						if((TextView)gl.getChildAt(i) == (TextView)v)
						{
							Card c = CardStoreBidon.getCard(em.getSelectedCardHand());
							if(c != null)
							{
								c.setUid(em.getSelectedCardHandUID());
								em.sendToPlayer(new PutCardEvent(c,i));
								break;
							}
						}
					}
				}
			}
		}
	}

}