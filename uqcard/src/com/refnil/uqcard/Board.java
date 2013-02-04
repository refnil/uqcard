package com.refnil.uqcard;

import java.util.List;
import java.util.Stack;

import com.refnil.uqcard.library.ListenableClass;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Board extends ListenableClass<Event>{
	
	private final static String TAG = "Board";
	
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

	public AbstractBoard() {

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
			Log.i(TAG, "Game begins");
			messageListener(event);
		}

		if (event.type == Event_Type.BEGIN_TURN) {
			this.setTour(this.getTour()+1);
			Log.i(TAG, "Turn " + this.getTour() +" begins");
			messageListener(event);
		}

		if (event.type == Event_Type.END_TURN) {
			Log.i(TAG, "Turn " + this.getTour() +" ends");
			messageListener(event);
		}

		if (event.type == Event_Type.END_GAME) {
			Log.i(TAG, "Game ends");
			messageListener(event);
		}
	}

}
