package com.refnil.uqcard;

public class CreatureCard extends Card {
	
	private int atk,def,hp;

	CreatureCard(String name, String description, String flavor, int cost,int atk, int def, int hp) {
		super(name, description, flavor, cost);
		this.atk = atk;
		this.def = def;
		this.hp = hp;
		// TODO Auto-generated constructor stub
	}
	
	CreatureCard(Card card, int def, int atk, int hp)
	{
		super(card);
		this.atk = atk;
		this.def = def;
		this.hp = hp;
	}

}

