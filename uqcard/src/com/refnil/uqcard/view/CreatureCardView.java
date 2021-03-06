package com.refnil.uqcard.view;

import com.refnil.uqcard.R;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class CreatureCardView extends CardView 
{
	private int attack, defence, health;

	public CreatureCardView(Context c,String name, String description,String flavor,int cost,int attack, int defence, int health)
	{
		super(c,name,description,flavor,cost);
		this.attack = attack;
		this.defence = defence;
		this.health = health;
	}

	public View getCardView(Context c)
	{
		View layoutCard = super.getCardView(c,150,120);
		
		((TextView) layoutCard.findViewById(R.id.atk)).setText(attack);
		((TextView) layoutCard.findViewById(R.id.def)).setText(defence);
		((TextView) layoutCard.findViewById(R.id.hp)).setText(health);
		
		return layoutCard;
	}

}
