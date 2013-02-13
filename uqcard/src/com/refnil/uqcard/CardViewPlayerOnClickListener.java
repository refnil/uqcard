package com.refnil.uqcard;

import android.view.View;
import android.view.View.OnClickListener;

public class CardViewPlayerOnClickListener implements OnClickListener {

	Board board;

	CardViewPlayerOnClickListener(Board board) {
		this.board = board;
	}

	public void onClick(View v) {
		CardView cv = (CardView) v;
		if (cv.getCard() == null)
			board.receiveEvent(new PlaceCardEvent(
					Event_Type.PLACE_PLAYER_CARD, cv, v.getId()));
		else
			board.receiveEvent
			em.sendEventToPlayer(new SelectedCardEvent(
					Event_Type.SELECT_PLAYER_CARD, cv.getCard()));
	}

}
