package com.refnil.uqcard.event;

import com.refnil.uqcard.data.CachedCardStore;
import com.refnil.uqcard.data.CachedCardStore.CachedStoreNotInitialised;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.DummyCardStore;
import com.refnil.uqcard.view.CardView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CardViewPlayerOnClickListener implements OnClickListener {

	private EventManager em;
	private CachedCardStore CardStoreBidon;
	Context c;

	public CardViewPlayerOnClickListener(EventManager em,Context c) {
		this.em = em;

		try {
			CardStoreBidon = CachedCardStore.getStore();
		} catch (CachedStoreNotInitialised e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.c = c;

	}
	
	public void onClick(View v) {
		if(em != null)
		{
			if(v instanceof ImageView )
			{
				GridLayout gl = (GridLayout) v.getParent();
				int position = gl.indexOfChild(v);
				boolean can = em.setSelectedCard(position, false);
				if(!can)
					Toast.makeText(c, "Cette carte a déjà attaqué.", Toast.LENGTH_SHORT).show();
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
