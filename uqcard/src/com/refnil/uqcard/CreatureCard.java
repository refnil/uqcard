package com.refnil.uqcard;

import android.content.Context;

public class CreatureCard extends Card {
	private int attack, defense, health;

	public CreatureCard(Context c, long id, String name, String description,
			String flavor, int cost, byte[] image, int attack, int defense,
			int health) {
		super(id, name, description, flavor, cost, image);
		this.setAttack(attack);
		this.setDefense(defense);
		this.setHealth(health);
	}

	public int getAttack() {
		return this.attack;
	}

	void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return this.defense;
	}

	void setDefense(int defense) {
		this.defense = defense;
	}

	public int getHealth() {
		return this.health;
	}

	void setHealth(int health) {
		this.health = health;
	}
}