package com.refnil.uqcard;

public class DummyCardStore implements CardStore{

	public Card getCard(int id) {
		// TODO Auto-generated method stub
		return new Card(id,"Carte","Description","WOOSH",2, new byte[] {});
	}
}
