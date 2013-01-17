package com.refnil.uqcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout
{
	private int cost;
	private String name;
	private String description;
	private String flavor;
	
	public Card(Context c,int cardId)
	{
		super(c);
		// a faire apres la bd
	}
	
	public Card(Context c,String name, String description,String flavor,int cost)
	{
		super(c);
		this.name = name;
		this.description = description;
		this.flavor = flavor;
		this.cost = cost;
		
	}
	
	public View getCardView(Context c)
	{
		LayoutInflater inflater = (LayoutInflater)c.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		View layoutCard = inflater.inflate(R.layout.layout_card, null);
		
		((TextView) layoutCard.findViewById(R.id.cost)).setText(Integer.toString(cost));
		((TextView) layoutCard.findViewById(R.id.name)).setText(name);
		((TextView) layoutCard.findViewById(R.id.description)).setText(description);
		((TextView) layoutCard.findViewById(R.id.flavor)).setText(flavor);
		return layoutCard;
	}
	
}
