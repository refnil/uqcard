package com.refnil.uqcard.event;

import com.refnil.uqcard.view.CardView;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.ImageView;

public class CardViewOpponentOnClickListener implements OnClickListener {

	EventManager em;
	public CardViewOpponentOnClickListener(EventManager em)
	{
		this.em = em;
	}
	public void onClick(View v) {
		if(v instanceof ImageView && em != null)
		{
			Log.i("click", "chose EnemyCard");
			GridLayout gl = (GridLayout) v.getParent();
			int position = gl.indexOfChild(v);
			em.setSelectedCard(position, true);
		}
	}

}