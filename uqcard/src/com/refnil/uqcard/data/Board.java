package com.refnil.uqcard.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.refnil.uqcard.data.CachedCardStore.CachedStoreNotInitialised;
import com.refnil.uqcard.event.AttackEvent;
import com.refnil.uqcard.event.BeginGameEvent;
import com.refnil.uqcard.event.BeginTurnEvent;
import com.refnil.uqcard.event.DrawCardEvent;
import com.refnil.uqcard.event.EndGameEvent;
import com.refnil.uqcard.event.EndTurnEvent;
import com.refnil.uqcard.event.Event;
import com.refnil.uqcard.event.Event_Type;
import com.refnil.uqcard.event.PutCardEvent;
import com.refnil.uqcard.event.RemoveEvent;
import com.refnil.uqcard.event.SendDeckEvent;
import com.refnil.uqcard.library.AbstractListenable;

import android.util.Log;

public class Board extends AbstractListenable<Event> {

	private final static String TAG = "Board";
	
	public CachedCardStore cardStoreBidon;
	
	private int phase;
	private int tour;
	private int playerID;
	private List<CreatureCard> opponentHandCards;
	private List<CreatureCard> playerHandCards;
	private CreatureCard[] opponentBoardCards;
	private CreatureCard[] playerBoardCards;
	private Stack<CreatureCard> opponentStackCards;
	private Stack<CreatureCard> playerStackCards;
	private Stack<CreatureCard> opponentGraveyardCards;
	private Stack<CreatureCard> playerGraveyardCards;
	private Deck playerDeck;
	private Deck opponentDeck;

	public Board()
	{
		try {
			cardStoreBidon = CachedCardStore.getStore();
		} catch (CachedStoreNotInitialised e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setTour(1);

		playerDeck = Deck.createDeck(cardStoreBidon);
		opponentDeck = Deck.createDeck(cardStoreBidon);

		playerBoardCards = new CreatureCard[12];
		opponentBoardCards = new CreatureCard[12];
		playerHandCards = new LinkedList<CreatureCard>();
		opponentHandCards = new LinkedList<CreatureCard>();
		opponentStackCards = new Stack<CreatureCard>();
		playerStackCards = new Stack<CreatureCard>();
		opponentGraveyardCards = new Stack<CreatureCard>();
		playerGraveyardCards = new Stack<CreatureCard>();

	}
	
	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public List<CreatureCard> getOpponentHandCards() {
		return opponentHandCards;
	}

	public void setOpponentHandCards(List<CreatureCard> opponentCards) {
		this.opponentHandCards = opponentCards;
	}

	public void addOpponentHandCard(CreatureCard card) {
		this.opponentHandCards.add(card);
	}

	public void deleteOpponentHandCard(CreatureCard card) {
		this.opponentHandCards.remove(card);
	}

	public List<CreatureCard> getPlayerHandCards() {
		return playerHandCards;
	}

	public void setPlayerHandCards(List<CreatureCard> playerCards) {
		this.playerHandCards = playerCards;
	}

	public void addPlayerHandCard(CreatureCard card) {
		this.playerHandCards.add(card);
	}

	public void deletePlayerHandCard(CreatureCard card) {
		this.playerHandCards.remove(card);
	}

	public CreatureCard[]  getOpponentBoardCards() {
		return opponentBoardCards;
	}

	public void setOpponentBoardCards(CreatureCard[]  opponentBoardCards) {
		this.opponentBoardCards = opponentBoardCards;
	}

	public void addOpponentBoardCard(CreatureCard card,int position) {
		this.opponentBoardCards[position] = card ;
	}

	/*public void deleteOpponentBoardCard(Card card) {
		this.opponentBoardCards.remove(card);
	}*/

	public CreatureCard[]  getPlayerBoardCards() {
		return playerBoardCards;
	}

	public void setPlayerBoardCards(CreatureCard[] playerBoardCards) {
		this.playerBoardCards = playerBoardCards;
	}

	public void addPlayerBoardCard(CreatureCard card,int position) {
		this.playerBoardCards[position] = card ;
	}

	/*public void deletePlayerBoardCard(Card card) {
		this.playerBoardCards.remove(card);
	}*/

	public Stack<CreatureCard> getOpponentStackCards() {
		return opponentStackCards;
	}

	public void setOpponentStackCards(Stack<CreatureCard> opponentStackCards) {
		this.opponentStackCards = opponentStackCards;
	}

	public Card opponentTakeCardInStack() {
		return this.opponentStackCards.pop();
	}

	public Stack<CreatureCard> getPlayerStackCards() {
		return playerStackCards;
	}

	public void setPlayerStackCards(Stack<CreatureCard> playerStackCards) {
		this.playerStackCards = playerStackCards;
	}

	public Card playerTakeCardInStack() {
		return this.playerStackCards.pop();
	}

	public Stack<CreatureCard> getOpponentGraveyardCards() {
		return opponentGraveyardCards;
	}

	public void setOpponentGraveyardCards(Stack<CreatureCard> opponentGraveyardCards) {
		this.opponentGraveyardCards = opponentGraveyardCards;
	}

	public void opponentAddCardInGraveyard(CreatureCard c) {
		this.opponentGraveyardCards.push(c);
	}

	public Stack<CreatureCard> getPlayerGraveyardCards() {
		return playerGraveyardCards;
	}

	public void setPlayerGraveyardCards(Stack<CreatureCard> playerGraveyardCards) {
		this.playerGraveyardCards = playerGraveyardCards;
	}

	public void playerAddCardInGraveyard(CreatureCard c) {
		this.playerGraveyardCards.push(c);
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}


	public void receiveEvent(Event event) {
		if (event.type == Event_Type.BEGIN_GAME) {
			BeginGameAction((BeginGameEvent)event);
		}
		else if(event.type == Event_Type.PUT_CARD)
		{
			PutCardAction((PutCardEvent)event);
		}

		else if (event.type == Event_Type.BEGIN_TURN) {
			BeginTurnAction((BeginTurnEvent)event);
		}

		else if (event.type == Event_Type.END_TURN) {
			EndTurnAction((EndTurnEvent)event);
		}

		else if (event.type == Event_Type.END_GAME) {
			EndGameAction((EndGameEvent)event);
		}
		
		else if(event.type == Event_Type.DRAW_CARD){
			DrawCardAction((DrawCardEvent)event);
		}
		else if(event.type == Event_Type.SEND_DECK)
		{
			SendDeckAction((SendDeckEvent) event);
		}

		else if (event instanceof AttackEvent) {
			BattleAction((AttackEvent)event);
		}
		else if(event instanceof RemoveEvent){
			RemoveAction((RemoveEvent)event);
		}
	}
	
	void RemoveAction(RemoveEvent event)
	{
		Log.i(TAG, "DEAD");
		int playerid = (event.getuid() / 40) + 1;
		
		if(playerid==this.playerID)
			this.getPlayerBoardCards()[this.getCardPositionOnBoard(event.getuid(), true)] = null;
		else
			this.getOpponentBoardCards()[this.getCardPositionOnBoard(event.getuid(), false)] = null;
		tell(event);
	}
	
	void BeginGameAction(BeginGameEvent event)
	{
		for(int i =0; i<40; i++)
		{
			this.getOpponentStackCards().add((CreatureCard)cardStoreBidon.getCard(1));
			this.getPlayerStackCards().add((CreatureCard)cardStoreBidon.getCard(1));
		}
		tell(event);
	}
	
	void SendDeckAction(SendDeckEvent event)
	{
		this.setPlayerDeck(event.getDecklist());
		playerStackCards = new Stack<CreatureCard>();
		
		for(int i =0; i < this.getPlayerDeck().getCards().size(); i++)
		{
			playerStackCards.add((CreatureCard)this.getPlayerDeck().CardAt(i));
		}
		this.setPlayerStackCards(playerStackCards);
		
	}
	
	void BeginTurnAction(BeginTurnEvent event)
	{
		this.setTour(this.getTour() + 1);
		Log.i(TAG, "Turn " + this.getTour() + " begins");
		tell(event);
	}
	
	void DrawCardAction(DrawCardEvent event)
	{
		if(event.getCardUID() / 40 == playerID-1)
		{
			CreatureCard c = cardStoreBidon.getCard(event.getCardID());
			
			//playerTakeCardInStack();
			c.setUid(event.getCardUID());
			addPlayerHandCard(c);
			tell(event);
		}
	}
	
	void PutCardAction(PutCardEvent event)
	{
		CreatureCard c = (CreatureCard)cardStoreBidon.getCard(event.getCardID());
		boolean found = false;
		for(int i=0;i<this.getPlayerHandCards().size();i++)
		{
			found = this.getPlayerHandCards().get(i).getUid() == event.getCardUID();
			if(found)
				break;
		}
		
		
		if(found)
		{
			c.setUid(event.getCardUID());
			deletePlayerHandCard(c);
			addPlayerBoardCard(c,event.getPosition());
		}
		else
		{
			c.setUid(event.getCardUID());
			deleteOpponentHandCard(c);
			addOpponentBoardCard(c,event.getPosition());
		}
		tell(event);
	}
	
	void BattleAction(AttackEvent event)
	{
		CreatureCard[] list =  getOpponentBoardCards();
		int opponent = -1,player = -1;
		for(int i=0;i<list.length;i++)
		{
			if(list[i] != null)
			{
				if(list[i].getUid() == event.getPlayer())
				{
					player = i;
					break;
				}
				else if(list[i].getUid() == event.getOpponent())
				{
					opponent = i;
					break;
				}
			}
		}
		list =  getPlayerBoardCards();
		
		for(int i=0;i<list.length;i++)
		{
			if(list[i] != null)
			{
				if(list[i].getUid() == event.getPlayer())
				{
					player = i;
					break;
				}
				else if(list[i].getUid() == event.getOpponent())
				{
					opponent = i;
					break;
				}
			}
		}
		
		if(event.getPlayer()/40 == playerID-1)
		{
			int hp = ((CreatureCard)this.getOpponentBoardCards()[opponent]).getHp();
			int attack = ((CreatureCard)this.getPlayerBoardCards()[player]).getAtk();
			attack -= ((CreatureCard)this.getOpponentBoardCards()[opponent]).getDef();
			((CreatureCard)this.getOpponentBoardCards()[opponent]).setHp(hp-attack);
		}
		else
		{
			int hp = ((CreatureCard)this.getPlayerBoardCards()[opponent]).getHp();
			int attack = ((CreatureCard)this.getOpponentBoardCards()[player]).getAtk();
			attack -= ((CreatureCard)this.getPlayerBoardCards()[opponent]).getDef();
			((CreatureCard)this.getPlayerBoardCards()[opponent]).setHp(hp - attack);
			
		}	
		tell(event);
	}
	
	void EndTurnAction(EndTurnEvent event)
	{
		Log.i(TAG, "Turn " + this.getTour() + " ends");
		tell(event);
	}
	
	void EndGameAction(EndGameEvent event)
	{
		tell(event);
	}

	public Deck getPlayerDeck() {
		return playerDeck;
	}

	public void setPlayerDeck(Deck playerDeck) {
		this.playerDeck = playerDeck;
	}

	public Deck getOpponentDeck() {
		return opponentDeck;
	}

	public void setOpponentDeck(Deck opponentDeck) {
		this.opponentDeck = opponentDeck;
	}
	
	public Card getCardByUID(int uid)
	{
		for(int i = 0; i< playerBoardCards.length; i++)
		{
			if(playerBoardCards[i] != null)
				if(uid == playerBoardCards[i].getUid())
				{
					return  playerBoardCards[i];
				}
			
		}
		
		for(int i = 0; i< opponentBoardCards.length; i++)
		{
			if(opponentBoardCards[i] != null)
			if(uid == opponentBoardCards[i].getUid())
			{
				return  opponentBoardCards[i];
			}
			
		}
		
		for(int i = 0; i< playerHandCards.size(); i++)
		{
			if(playerHandCards.get(i) != null)
			if(uid == playerHandCards.get(i).getUid())
			{
				return  playerHandCards.get(i);
			}
			
		}
		
		for(int i = 0; i< opponentHandCards.size(); i++)
		{
			if(opponentHandCards.get(i) != null)
			if(uid == opponentHandCards.get(i).getUid())
			{
				return  opponentHandCards.get(i);
			}
			
		}
		return null;
	
	}
	
	public int getCardPositionOnBoard(int uid,boolean isPlayer)
	{
		int position = -1;
		CreatureCard tab[];
		
		if(isPlayer)
			tab= this.getPlayerBoardCards();
		else
			tab = this.getOpponentBoardCards();
		
		for(int i=0;i<tab.length;i++)
		{
			if(tab[i]!=null)
			{
				if(tab[i].getUid() == uid)
				{
					position = i;
					break;
				}
			}
		}
		Log.i(TAG, "position trouvé : " + position);
		return position;
	}
}
