package com.refnil.uqcard.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import com.refnil.uqcard.event.AttackEvent;
import com.refnil.uqcard.event.BeginGameEvent;
import com.refnil.uqcard.event.BeginTurnEvent;
import com.refnil.uqcard.event.DrawCardEvent;
import com.refnil.uqcard.event.EndGameEvent;
import com.refnil.uqcard.event.EndTurnEvent;
import com.refnil.uqcard.event.Event;
import com.refnil.uqcard.event.Event_Type;
import com.refnil.uqcard.event.PutCardEvent;
import com.refnil.uqcard.event.SendDeckEvent;
import com.refnil.uqcard.library.AbstractListenable;

import android.util.Log;

public class Board extends AbstractListenable<Event> {

	public boolean temp;
	private final static String TAG = "Board";
	
	private DummyCardStore cardStoreBidon = new DummyCardStore();

	private int phase;
	private int tour;
	private int playerID;
	private List<Card> opponentHandCards;
	private List<Card> playerHandCards;
	private Card[] opponentBoardCards;
	private Card[] playerBoardCards;
	private Stack<Card> opponentStackCards;
	private Stack<Card> playerStackCards;
	private Stack<Card> opponentGraveyardCards;
	private Stack<Card> playerGraveyardCards;
	private Deck playerDeck;
	private Deck opponentDeck;

	public Board()
	{
		this.setTour(1);
		playerBoardCards = new Card[12];
		opponentBoardCards = new Card[12];
		playerHandCards = new LinkedList<Card>();
		opponentHandCards = new LinkedList<Card>();
		opponentStackCards = new Stack<Card>();
		playerStackCards = new Stack<Card>();
		opponentGraveyardCards = new Stack<Card>();
		playerGraveyardCards = new Stack<Card>();
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

	public List<Card> getOpponentHandCards() {
		return opponentHandCards;
	}

	public void setOpponentHandCards(List<Card> opponentCards) {
		this.opponentHandCards = opponentCards;
	}

	public void addOpponentHandCard(Card card) {
		this.opponentHandCards.add(card);
	}

	public void deleteOpponentHandCard(Card card) {
		this.opponentHandCards.remove(card);
	}

	public List<Card> getPlayerHandCards() {
		return playerHandCards;
	}

	public void setPlayerHandCards(List<Card> playerCards) {
		this.playerHandCards = playerCards;
	}

	public void addPlayerHandCard(Card card) {
		Log.i(TAG, "begin add");
		if(card == null)
			Log.i(TAG, "card is null");
		if(playerHandCards == null)
			Log.i(TAG,"playerhandcards is null");
		this.playerHandCards.add(card);
		Log.i(TAG, "end add");
	}

	public void deletePlayerHandCard(Card card) {
		this.playerHandCards.remove(card);
	}

	public Card[]  getOpponentBoardCards() {
		return opponentBoardCards;
	}

	public void setOpponentBoardCards(Card[]  opponentBoardCards) {
		this.opponentBoardCards = opponentBoardCards;
	}

	public void addOpponentBoardCard(Card card,int position) {
		this.opponentBoardCards[position] = card ;
	}

	/*public void deleteOpponentBoardCard(Card card) {
		this.opponentBoardCards.remove(card);
	}*/

	public Card[]  getPlayerBoardCards() {
		return playerBoardCards;
	}

	public void setPlayerBoardCards(Card[] playerBoardCards) {
		this.playerBoardCards = playerBoardCards;
	}

	public void addPlayerBoardCard(Card card,int position) {
		this.opponentBoardCards[position] = card ;
	}

	/*public void deletePlayerBoardCard(Card card) {
		this.playerBoardCards.remove(card);
	}*/

	public Stack<Card> getOpponentStackCards() {
		return opponentStackCards;
	}

	public void setOpponentStackCards(Stack<Card> opponentStackCards) {
		this.opponentStackCards = opponentStackCards;
	}

	public Card opponentTakeCardInStack() {
		return this.opponentStackCards.pop();
	}

	public Stack<Card> getPlayerStackCards() {
		return playerStackCards;
	}

	public void setPlayerStackCards(Stack<Card> playerStackCards) {
		this.playerStackCards = playerStackCards;
	}

	public Card playerTakeCardInStack() {
		return this.playerStackCards.pop();
	}

	public Stack<Card> getOpponentGraveyardCards() {
		return opponentGraveyardCards;
	}

	public void setOpponentGraveyardCards(Stack<Card> opponentGraveyardCards) {
		this.opponentGraveyardCards = opponentGraveyardCards;
	}

	public void opponentAddCardInGraveyard(Card c) {
		this.opponentGraveyardCards.push(c);
	}

	public Stack<Card> getPlayerGraveyardCards() {
		return playerGraveyardCards;
	}

	public void setPlayerGraveyardCards(Stack<Card> playerGraveyardCards) {
		this.playerGraveyardCards = playerGraveyardCards;
	}

	public void playerAddCardInGraveyard(Card c) {
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
		
		else if(event.type == Event_Type.PUT_CARD)
		{
			PutCardAction((PutCardEvent)event);
		}

		else if (event instanceof AttackEvent) {
			BattleAction((AttackEvent)event);
		}
	}
	
	void BeginGameAction(BeginGameEvent event)
	{
		Log.i(TAG, "Sending deck");
		tell(new SendDeckEvent(playerID, playerDeck));
		Log.i(TAG, "Game begins");
		tell(event);
		
	}
	
	void SendDeckAction(SendDeckEvent event)
	{
		Log.i(TAG, "getting shuffled deck");
		Stack<Card> deckStack = new Stack<Card>();
		long seed = System.nanoTime();
		Collections.shuffle(event.getDecklist().getCards(), new Random(seed));
		
		deckStack.addAll(event.getDecklist().getCards());
		this.playerStackCards = deckStack;
	}
	
	void BeginTurnAction(BeginTurnEvent event)
	{
		this.setTour(this.getTour() + 1);
		Log.i(TAG, "Turn " + this.getTour() + " begins");
		tell(event);
	}
	
	void DrawCardAction(DrawCardEvent event)
	{
		Log.i(TAG, "Draw card");
		
		Card c = cardStoreBidon.getCard(event.getCardID());
		
		//playerTakeCardInStack();
		c.setUid(event.getCardUID());
		Log.i(TAG, "card id: "+c.get_Id()+" card UID: " + c.getUid());
		Log.i(TAG, "addPlayerHandCard");
		addPlayerHandCard(c);
		Log.i(TAG, "tell(event);");
		tell(event);
	}
	
	void PutCardAction(PutCardEvent event)
	{
		Log.i(TAG,"Put card");
		Card c = cardStoreBidon.getCard(event.getCardID());
		if(this.getPlayerHandCards().contains(c))
		{

			deletePlayerHandCard(c);
			c.setUid(event.getCardUID());
			addPlayerBoardCard(c,event.getPosition());
		}
		else
		{
			deleteOpponentHandCard(c);
			c.setUid(event.getCardUID());
			addOpponentBoardCard(c,event.getPosition());
		}
		tell(event);
	}
	
	void BattleAction(AttackEvent event)
	{

		Card[] list =  getOpponentBoardCards();
		int opponent = -1;
		for(int i=0;i<list.length;i++)
		{
			if(list[i].getUid() == event.getOpponent())
			{
				opponent = i;
				break;
			}
		}
		list =  getPlayerBoardCards();
		int player = -1;
		for(int i=0;i<list.length;i++)
		{
			if(list[i].getUid() == event.getPlayer())
			{
				player = i;
				break;
			}
		}
		
		((CreatureCard)this.getOpponentBoardCards()[opponent]).setHp(((CreatureCard)this.getOpponentBoardCards()[opponent]).getHp() - (((CreatureCard)this.getOpponentBoardCards()[opponent]).getAtk()- ((CreatureCard)this.getOpponentBoardCards()[opponent]).getDef()));
		tell(event);
	}
	
	void EndTurnAction(EndTurnEvent event)
	{
		Log.i(TAG, "Turn " + this.getTour() + " ends");
		tell(event);
	}
	
	void EndGameAction(EndGameEvent event)
	{
		Log.i(TAG, "Game ends");
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
			if(uid == playerBoardCards[i].getUid())
			{
				return  playerBoardCards[i];
			}
			
		}
		
		for(int i = 0; i< opponentBoardCards.length; i++)
		{
			if(uid == opponentBoardCards[i].getUid())
			{
				return  opponentBoardCards[i];
			}
			
		}
		
		for(int i = 0; i< playerHandCards.size(); i++)
		{
			if(uid == playerHandCards.get(i).getUid())
			{
				return  playerHandCards.get(i);
			}
			
		}
		
		for(int i = 0; i< opponentHandCards.size(); i++)
		{
			if(uid == opponentHandCards.get(i).getUid())
			{
				return  opponentHandCards.get(i);
			}
			
		}
		return null;
	
	}
}
