package com.refnil.uqcard.board;

import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.refnil.uqcard.Card;
import com.refnil.uqcard.Event;
import com.refnil.uqcard.Event_Type;

public class Board {
	private Context c;
	private int phase;
	private int tour;
	private List<Card> opponentHandCards;
	private List<Card> playerHandCards;
	private List<Card> opponentBoardCards;
	private List<Card> playerBoardCards;
	private Card selectedCardOnBoard;
	
	public Board(Context c)
	{
		this.c = c;
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
	
	public void addOpponentHandCard(Card card)
	{
		this.opponentHandCards.add(card);
	}
	
	public void deleteOpponentHandCard(Card card)
	{
		this.opponentHandCards.remove(card);
	}
	
	public List<Card> getPlayerHandCards() {
		return playerHandCards;
	}
	public void setPlayerHandCards(List<Card> playerCards) {
		this.playerHandCards = playerCards;
	}
	
	public void addPlayerHandCard(Card card)
	{
		this.playerHandCards.add(card);
	}
	
	public void deletePlayerHandCard(Card card)
	{
		this.playerHandCards.remove(card);
	}
	
	public List<Card> getOpponentBoardCards() {
		return opponentBoardCards;
	}
	public void setOpponentBoardCards(List<Card> opponentBoardCards) {
		this.opponentBoardCards = opponentBoardCards;
	}
	
	public void addOpponentBoardCard(Card card)
	{
		this.opponentBoardCards.add(card);
	}
	
	public void deleteOpponentBoardCard(Card card)
	{
		this.opponentBoardCards.remove(card);
	}
	
	public List<Card> getPlayerBoardCards() {
		return playerBoardCards;
	}
	public void setPlayerBoardCards(List<Card> playerBoardCards) {
		this.playerBoardCards = playerBoardCards;
	}
	
	public void addPlayerBoardCard(Card card)
	{
		this.playerBoardCards.add(card);
	}
	
	public void deletePlayerBoardCard(Card card)
	{
		this.playerBoardCards.remove(card);
	}
	
	public Card getSelectedCardOnBoard() {
		return selectedCardOnBoard;
	}

	public void setSelectedCardOnBoard(Card selectedCardOnBoard) {
		this.selectedCardOnBoard = selectedCardOnBoard;
	}

	public void receiveEvent(Event event)
	{
		if(event.type ==  Event_Type.BEGIN_GAME)
		{
			Toast.makeText(this.c, "Game begins", Toast.LENGTH_SHORT).show();
		}
	
		
		if(event.type ==  Event_Type.BEGIN_TURN)
		{
			Toast.makeText(this.c, "Turn begins", Toast.LENGTH_SHORT).show();
		}
		
		if(event.type ==  Event_Type.END_TURN)
		{
			Toast.makeText(this.c, "Turn ends", Toast.LENGTH_SHORT).show();
		}
		
		if(event.type ==  Event_Type.END_GAME)
		{	
			Toast.makeText(this.c, "Game ends", Toast.LENGTH_SHORT).show();
		}
	}
	
}
