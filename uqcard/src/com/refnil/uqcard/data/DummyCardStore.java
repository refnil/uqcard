package com.refnil.uqcard.data;

public class DummyCardStore implements CardStore{

	public CreatureCard getCard(int id) {
		// TODO Auto-generated method stub
		if(id == 0)
		{
			return  new CreatureCard("Carte", "Description","WOOSH", 2, 2, 0, 1);
		}
		else if(id == 1)
		{
			return new CreatureCard("Carte", "Description","WOOSH", 2, 2, 1, 2);
		}
		return null;
	}
}
