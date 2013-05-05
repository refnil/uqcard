package com.refnil.uqcard.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.refnil.uqcard.data.test.DeckTest;
import com.refnil.uqcard.event.AttackEvent;
import com.refnil.uqcard.event.BeginTurnEvent;
import com.refnil.uqcard.event.DrawCardEvent;
import com.refnil.uqcard.event.Event;
import com.refnil.uqcard.event.Event_Type;
import com.refnil.uqcard.event.PutCardEvent;
import com.refnil.uqcard.event.SendDeckEvent;

import android.util.Log;

public class ServerBoard extends Board {
	
	private DummyCardStore cardStore = new DummyCardStore();
	final private static String TAG = "ServerBoard";
	private int UIDedDecks;
	private DeckTest deckBidon;
	
	public ServerBoard()
	{
		this.setTour(1);
		UIDedDecks = 0;
		deckBidon = new DeckTest();

	}
	
	@Override
	public void receiveEvent(Event event) {
		if (event.type == Event_Type.BEGIN_GAME) {
			Log.i(TAG, "server Game begins");

			tell(event);
			
			/*CreatureCard playa = (CreatureCard) cardStore.getCard(2);
			playa.setUid(0);
			tell(new PutCardEvent(playa, 0));
			
			CreatureCard enemy = (CreatureCard) cardStore.getCard(2);
			enemy.setUid(40);
			tell(new PutCardEvent(enemy, 0));*/

		}

		if (event.type == Event_Type.BEGIN_TURN) {
			// DOIT RECEVOIR DECK DU SERVER, SO FAR THIS IS SHIT
			/*tell(new DrawCardEvent(deckBidon.CardAt(0).get_Id(), deckBidon.CardAt(0).getUid()));
			deckBidon.pop();*/

		}
		
		if (event.type == Event_Type.SEND_DECK) {
			SendDeckEvent se = (SendDeckEvent) event;
			Log.i(TAG, "ASDASDWFFAS DSAFWAFDASD SAD " + String.valueOf(se.getPlayer()));
			Stack<Card> deckStack = new Stack<Card>();
			long seed = System.nanoTime();

			for (int i = UIDedDecks * 40; i < (UIDedDecks * 40) + 40; i++) {
				se.getDecklist().getCards().get(i).setUid(i);
			}
			UIDedDecks++;

			Collections.shuffle(se.getDecklist().getCards(), new Random(seed));

			deckStack.addAll(se.getDecklist().getCards());
			
			if (se.getPlayer() == 0) {
				Log.i(TAG, "Setting host deck");
				this.setPlayerStackCards(deckStack);
				tell(event);
			} else {
				Log.i(TAG, "Setting host's enemy deck");
				this.setOpponentStackCards(deckStack);
				tell(event);
			}
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
		/*if (event.type == Event_Type.DRAW_CARD) {
			DrawCardEvent de = (DrawCardEvent) event;
			Log.i(TAG, "server drawing card "+de.getCardUID());
			
			getPlayerHandCards().add(getCardByUID(de.getCardUID()));
			tell(de);
		}
		*/
		if (event.type == Event_Type.PUT_CARD) {
			PutCardEvent pe = (PutCardEvent) event;
			Log.i(TAG, "Putting card "+pe.getCardUID()+" on play");
			//FIXME

			if(true)
			{
				if(pe.getPosition() !=5 && pe.getPosition() !=11)
				{
					if(this.getOpponentBoardCards()[pe.getPosition()] == null)
					{
						for(int i=0; i<this.getPlayerHandCards().size(); i++)
						{
							if(this.getPlayerHandCards().get(i).getUid() == pe.getCardUID())
							{
								this.getOpponentBoardCards()[pe.getPosition()] =this.getPlayerHandCards().get(i);
								this.getPlayerHandCards().remove(i);
								tell(pe);
							}
						}
						
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
						this.getPlayerBoardCards()[pe.getPosition()] =this.getCardByUID(pe.getCardUID());
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
			
			if(getTour() %2 ==1)
			{
				this.getPlayerHandCards().add(this.getPlayerStackCards().get(0));
				tell(new DrawCardEvent(this.getPlayerStackCards().get(0).get_Id(), getPlayerStackCards().get(0).getUid()));
				this.getPlayerStackCards().pop();
			}
			else
			{
				this.getOpponentHandCards().add(this.getOpponentStackCards().get(0));
				tell(new DrawCardEvent(this.getOpponentStackCards().get(0).get_Id(), getOpponentStackCards().get(0).getUid()));
				this.getOpponentStackCards().pop();
			}
			
			
			// not good code, to burn.
			if(deckBidon.getCards().size() > 1)
			{
				deckBidon.pop();
			}
		}

		if (event.type == Event_Type.END_GAME) {
			Log.i(TAG, "Game ends");
			tell(event);
		}
	}
	

	void receiveDeck(ArrayList<Integer> idList,int playerNumber)
	{
		
	}
	

}
