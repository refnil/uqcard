package com.refnil.uqcard;

public class CreatureCards extends Card 
{
	private int attack, defence, health;

	public CreatureCards(int cost, String name,int attack, int defence, int health) {
		super(cost, name);
		this.attack = attack;
		this.defence = defence;
		this.health = health;
		// TODO Auto-generated constructor stub
	}

	

}
