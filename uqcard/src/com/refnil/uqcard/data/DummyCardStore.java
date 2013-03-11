package com.refnil.uqcard.data;

public class DummyCardStore implements CardStore{

	public Card getCard(int id,CardType type) {
		// TODO Auto-generated method stub
		if(type == CardType.CARD)
		{
			return new Card(id,"Carte","Description","WOOSH",2, new byte[] {});
		}
		else if(type == CardType.CREATURE)
		{
			return new CreatureCard("Carte", "Description","WOOSH", 2, 2, 1, 2);
		}
		return null;
	}
}
