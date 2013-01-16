package com.refnil.uqcard;

import android.widget.Gallery;
import android.widget.ImageView;

public class Card extends ImageView
{
	private int cost;
	private String name;
	private String description;
	private String flavorText;
	private ImageView art;
	private ImageView imageDescription;
	private ImageView imageName;
	
	public Card(int cost, String name)
	{
		super(null);
		this.cost = cost;
		this.name = name;
		this.flavorText = "";
		this.imageDescription = (ImageView) findViewById(R.id.imageView1);
		
	}
	
	
	
}
