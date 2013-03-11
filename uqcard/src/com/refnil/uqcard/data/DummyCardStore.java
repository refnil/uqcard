package com.refnil.uqcard.data;

public class DummyCardStore implements CardStore{

	public Card getCard(int id) {
		// TODO Auto-generated method stub
		if(id == 0)
		{
			return new Card(id,"Carte","Description","WOOSH",2, new byte[] {});
		}
		else if(id == 1)
		{
			return new CreatureCard("Carte", "Description","WOOSH", 2, 2, 1, 2);
		}
		return null;
	}
}
