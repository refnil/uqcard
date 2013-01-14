package com.refnil.uqcard;

import android.text.style.ImageSpan;
import android.widget.Gallery;
import android.widget.ImageView;

public class Card 
{
	private int cost;
	private String name;
	private String flavorText;
	private ImageView art;
	
	public Card(int cost, String name)
	{
		this.cost = cost;
		this.name = name;
		this.flavorText = "";
		this.art = new ImageView(null);
	}
	
}
