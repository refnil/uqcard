package com.refnil.uqcard.data;


public class CreatureCard extends Card {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5650896118503012325L;
	private int atk,def,hp;

	CreatureCard(String name, String description, String flavor, int cost,int atk, int def, int hp) {
		super(name, description, flavor, cost);
		this.setAtk(atk);
		this.setDef(def);
		this.setHp(hp);
		// TODO Auto-generated constructor stub
	}
	
	CreatureCard(Card card, int def, int atk, int hp)
	{
		super(card);
		this.setAtk(atk);
		this.setDef(def);
		this.setHp(hp);
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

}

