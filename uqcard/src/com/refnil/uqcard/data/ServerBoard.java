package com.refnil.uqcard.data;

import com.refnil.uqcard.data.test.DeckTest;
import com.refnil.uqcard.event.AttackEvent;
import com.refnil.uqcard.event.BeginTurnEvent;
import com.refnil.uqcard.event.DrawCardEvent;
import com.refnil.uqcard.event.Event;
import com.refnil.uqcard.event.Event_Type;
import com.refnil.uqcard.event.PutCardEvent;
import com.refnil.uqcard.event.SelectedCardEvent;

import android.util.Log;

public class ServerBoard extends Board {
	
	final private static String TAG = "ServerBoard";
	
	public ServerBoard()
	{
		this.setTour(1);
	}
	
	@Override
	public void receiveEvent(Event event) {
		if (event.type == Event_Type.BEGIN_GAME) {
			Log.i(TAG, "Game begins");
			tell(event);
		}

		if (event.type == Event_Type.BEGIN_TURN) {
			Log.i(TAG, "Turn " + this.getTour() + " begins");
			tell(new DrawCardEvent((int)getPlayerDeck().drawCardAt(0).get_Id()));

		}
		
		if (event.type == Event_Type.DECLARE_ATTACK) {
			
			AttackEvent ae = (AttackEvent)event;
			Log.i(TAG, ae.getPlayer()+" attacks "+ae.getOpponent());

			CreatureCard good = (CreatureCard) getCardByUID(ae.getPlayer());
			CreatureCard evil = (CreatureCard) getCardByUID(ae.getOpponent());
			int damage = good.getAtk()-evil.getDef();
			if(damage>0)
			{
				evil.setHp(evil.getHp()-damage);
				tell(ae);
			}
		}
		if (event.type == Event_Type.DRAW_CARD) {
			DrawCardEvent de = (DrawCardEvent) event;
			Log.i(TAG, "Drawing card "+de.getCard());
			
			getPlayerHandCards().add(getCardByUID(de.getCard()));
			tell(de);
		}
		
		if (event.type == Event_Type.PUT_CARD) {
			PutCardEvent pe = (PutCardEvent) event;
			Log.i(TAG, "Putting card "+pe.getCard()+" on play");
			
			if(pe.isOpponent())
			{
				if(pe.getPosition() !=5 && pe.getPosition() !=11)
				{
					if(this.getOpponentBoardCards()[pe.getPosition()] == null)
					{
						this.getOpponentBoardCards()[pe.getPosition()] =this.getCardByUID(pe.getCard());
						tell(pe);
					}
					else
					{
						Log.i(TAG, "Already a card in position "+pe.getPosition());
					}
				}
				else
				{
					Log.i(TAG, "Cant put card on deck or graveyard ");
				}
			}
			else
			{
				if(pe.getPosition() !=5 && pe.getPosition() !=11)
				{
					if(this.getPlayerBoardCards()[pe.getPosition()] == null)
					{
						this.getPlayerBoardCards()[pe.getPosition()] =this.getCardByUID(pe.getCard());
						tell(pe);
					}
					else
					{
						Log.i(TAG, "Already a card in position "+pe.getPosition());
					}
				}
				else
				{
					Log.i(TAG, "Cant put card on deck or graveyard ");
				}
			}
		}
		
		if (event.type == Event_Type.END_TURN) {
			Log.i(TAG, "Turn " + this.getTour() + " ends");

			this.setTour(getTour()+1);
			tell(new BeginTurnEvent());
		}

		if (event.type == Event_Type.END_GAME) {
			Log.i(TAG, "Game ends");
			tell(event);
		}
	}

}
