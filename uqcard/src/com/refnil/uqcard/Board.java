package com.refnil.uqcard;

import java.util.List;
import java.util.Stack;

import android.content.Context;
import android.widget.Toast;

public class Board {
	private Context c;
	private BoardViewActivity boardView;
	private int phase;
	private int tour;
	private int playerID ;
	private List<Card> opponentHandCards;
	private List<Card> playerHandCards;
	private List<Card> opponentBoardCards;
	private List<Card> playerBoardCards;
	private Stack<Card> opponentStackCards;
	private Stack<Card> playerStackCards;
	private Stack<Card> opponentGraveyardCards;
	private Stack<Card> playerGraveyardCards;
	private Card selectedCardOnBoard;

	public Board(Context c,BoardViewActivity boardView) {
		this.c = c;
		this.boardView = boardView;

		/*****************************TEMP ZONE 04-02-2013 *********************/
		CreatureCard c1 = new CreatureCard(c,1,"Nexus 4","Smartphone","LG bro.",2,null,4,4,20);
		CardView cv1 = new CardView(c,c1);
		CreatureCard c2 = new CreatureCard(c,2,"Nexus 7","7\" tablet","Asus style.",1,null,2,5,15);
		CardView cv2 = new CardView(c,c2);
		CreatureCard c3 = new CreatureCard(c,3,"Nexus 10","10\" tablet","Samsung stuff.",4,null,7,3,25);
		CardView cv3 = new CardView(c,c3);
		
		this.addCardToBoard(cv1,R.id.playerFront2,true);
		this.addCardToBoard(cv2,R.id.playerFront3,true);
		this.addCardToBoard(cv3,R.id.playerFront4,true);
		/***************************** END *************************************/
	}
	
	public void addCardToBoard(CardView card,int viewID,boolean currentPlayer)
	{
		if(currentPlayer)
		{
			playerBoardCards.add(card.getCard());
			boardView.onMessage(new PlaceCardEvent(Event_Type.PLACE_PLAYER_CARD,card,viewID));
		}
		else
		{
			opponentBoardCards.add(card.getCard());
			boardView.onMessage(new PlaceCardEvent(Event_Type.PLACE_OPPONENT_CARD,card,viewID));
		}
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
		this.playerHandCards.add(card);
	}

	public void deletePlayerHandCard(Card card) {
		this.playerHandCards.remove(card);
	}

	public List<Card> getOpponentBoardCards() {
		return opponentBoardCards;
	}

	public void setOpponentBoardCards(List<Card> opponentBoardCards) {
		this.opponentBoardCards = opponentBoardCards;
	}

	public void addOpponentBoardCard(Card card) {
		this.opponentBoardCards.add(card);
	}

	public void deleteOpponentBoardCard(Card card) {
		this.opponentBoardCards.remove(card);
	}

	public List<Card> getPlayerBoardCards() {
		return playerBoardCards;
	}

	public void setPlayerBoardCards(List<Card> playerBoardCards) {
		this.playerBoardCards = playerBoardCards;
	}

	public void addPlayerBoardCard(Card card) {
		this.playerBoardCards.add(card);
	}

	public void deletePlayerBoardCard(Card card) {
		this.playerBoardCards.remove(card);
	}

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

	public Card getSelectedCardOnBoard() {
		return selectedCardOnBoard;
	}

	public void setSelectedCardOnBoard(Card selectedCardOnBoard) {
		this.selectedCardOnBoard = selectedCardOnBoard;
	}

	public void receiveEvent(Event event) {
		if (event.type == Event_Type.BEGIN_GAME) {
			Toast.makeText(this.c, "Game begins", Toast.LENGTH_SHORT).show();
			boardView.onMessage(event);
		}

		if (event.type == Event_Type.BEGIN_TURN) {
			this.setTour(this.getTour()+1);
			Toast.makeText(this.c, "Turn " + this.getTour() +" begins", Toast.LENGTH_SHORT).show();
			boardView.onMessage(event);
		}

		if (event.type == Event_Type.END_TURN) {
			Toast.makeText(this.c, "Turn " + this.getTour() +" ends", Toast.LENGTH_SHORT).show();
			boardView.onMessage(event);
		}

		if (event.type == Event_Type.END_GAME) {
			Toast.makeText(this.c, "Game ends", Toast.LENGTH_SHORT).show();
			boardView.onMessage(event);

		}
	}

}
