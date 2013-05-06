package com.refnil.uqcard.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.refnil.uqcard.event.AttackEvent;
import com.refnil.uqcard.event.BeginTurnEvent;
import com.refnil.uqcard.event.DrawCardEvent;
import com.refnil.uqcard.event.Event;
import com.refnil.uqcard.event.Event_Type;
import com.refnil.uqcard.event.PutCardEvent;
import com.refnil.uqcard.event.RemoveEvent;
import com.refnil.uqcard.event.SendDeckEvent;

import android.util.Log;

public class ServerBoard extends Board {
	
	final private static String TAG = "ServerBoard";
	private int UIDedDecks;
	private Deck deckBidon;
	private int playerID,opponentID;
	
	public ServerBoard()
	{
		this.setTour(1);
		UIDedDecks = 0;
		//deckBidon = new DeckTest();

	}
	
	@Override
	public void receiveEvent(Event event) {
		if (event.type == Event_Type.BEGIN_GAME) {
			Log.i(TAG, "server Game begins");

			tell(event);
		}

		if (event.type == Event_Type.BEGIN_TURN) {
			// DOIT RECEVOIR DECK DU SERVER, SO FAR THIS IS SHIT
			/*tell(new DrawCardEvent(deckBidon.CardAt(0).get_Id(), deckBidon.CardAt(0).getUid()));
			deckBidon.pop();*/

		}
		
		if (event.type == Event_Type.SEND_DECK) {
			SendDeckEvent se = (SendDeckEvent) event;
			Stack<CreatureCard> deckStack = new Stack<CreatureCard>();
			long seed = System.nanoTime();
			int cpt=0;

			for (int i = UIDedDecks * 40; i < (UIDedDecks * 40) + 40; i++) {
				se.getDecklist().getCards().get(cpt).setUid(i);
				cpt++;
			}
			UIDedDecks++;

			Collections.shuffle(se.getDecklist().getCards(), new Random(seed));

			deckStack.addAll(se.getDecklist().getCards());
			
			if (se.getPlayer() == 1) {
				Log.i(TAG, "Setting host deck");
				this.setPlayerStackCards(deckStack);
				playerID = 1;
				tell(event);
			} else {
				Log.i(TAG, "Setting host's enemy deck");
				this.setOpponentStackCards(deckStack);
				opponentID = se.getPlayer();
				tell(event);
			}
		}
		
		
		
		if (event.type == Event_Type.DECLARE_ATTACK) {
			
			AttackEvent ae = (AttackEvent)event;
			int playerid = ae.getPlayer() / 40;
			if(this.getTour() %2 != playerid)
			{
				Log.i(TAG, ae.getPlayer()+" attacks "+ae.getOpponent());
	
				CreatureCard good = (CreatureCard) getCardByUID(ae.getPlayer());
				CreatureCard evil = (CreatureCard) getCardByUID(ae.getOpponent());
				int atkgood = good.getAtk();
				int evildef = evil.getDef();
				int damage = good.getAtk()-evil.getDef();
				if(damage>0)
				{
					evil.setHp(evil.getHp()-damage);
					tell(ae);
					if(evil.getHp()<=0)
					{
						playerid = (ae.getOpponent() / 40)+1;
						int position;
						if(playerid==1)
						{
							position = this.getCardPositionOnBoard(ae.getOpponent(), true);
							this.getPlayerBoardCards()[position]=null;
						}
						else
						{
							position = this.getCardPositionOnBoard(ae.getOpponent(), false);
							this.getOpponentBoardCards()[position]=null;
						}
						Log.i(TAG, "DEAD");
						tell(new RemoveEvent(evil.getUid(),position));
					}
				}
				else
					Log.i(TAG, "You can't attack. Wait your turn.");
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
			int playerid = (pe.getCardUID()/40);
			if(this.getTour() % 2 != playerid)
			{
				if(pe.getPosition() !=5 && pe.getPosition() !=11)
				{
					Log.i(TAG, "l.112");
					List<CreatureCard> stack;
					CreatureCard[] tab;
					if(((PutCardEvent)event).getCardUID() / 40 +1 == playerID)
					{
						stack = this.getPlayerHandCards();
						tab = this.getPlayerBoardCards();
						Log.i(TAG, "l.131 player ");
					}
					else
					{
						stack = this.getOpponentHandCards();
						tab = this.getOpponentBoardCards();
						Log.i(TAG, "l.131 opp ");
					}
					
					if(tab[pe.getPosition()] == null)
					{
						Log.i(TAG, "l.128");
						for(int i=0; i<stack.size(); i++)
						{
							Log.i(TAG, "l.131 stack "+String.valueOf(stack.get(i).getUid()));
							
							
							if(stack.get(i).getUid() == pe.getCardUID())
							{
								tab[pe.getPosition()] =stack.get(i);
								stack.remove(i);
								Log.i(TAG, "l.136");
								tell(pe);
								break;
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
				Log.i(TAG, "It's not your turn. You can't put a card.");
			
		}
		
		if (event.type == Event_Type.END_TURN) {
			Log.i(TAG, "Turn " + this.getTour() + " ends");

			this.setTour(getTour()+1);
			tell(new BeginTurnEvent());
			
			if(getTour() %2 ==playerID)
			{
				this.getPlayerHandCards().add(this.getPlayerStackCards().get(0));
				tell(new DrawCardEvent(this.getPlayerStackCards().get(0).get_Id(), getPlayerStackCards().get(0).getUid()));
				this.getPlayerStackCards().pop();
			}
			else
			{
				Log.i(TAG, "uid  " + getOpponentStackCards().get(0).getUid());
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
