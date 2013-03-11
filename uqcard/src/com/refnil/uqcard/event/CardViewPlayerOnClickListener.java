package com.refnil.uqcard.event;

import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.CardStoreBidon;
import com.refnil.uqcard.view.CardView;

import android.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.TextView;

public class CardViewPlayerOnClickListener implements OnClickListener {

	EventManager em;
	

	public CardViewPlayerOnClickListener(EventManager em) {
		this.em = em;
	}

	public void onClick(View v) {
		if(v instanceof CardView)
			em.setSelectedCard(((CardView)v).getCard().getUid(), false);
		else
		{
			GridLayout gl = (GridLayout)v.getParent();
			for(int i=0;i<gl.getChildCount();i++)
			{
				if((TextView)gl.getChildAt(i) == (TextView)v)
				{
					Card c = CardStoreBidon.getCard(em.getSelectedCardHand());
					c.setUid(em.getSelectedCardHandUID());
					em.sendToPlayer(new PutCardEvent(c,i));
					break;
				}
			}
		}
	}

}