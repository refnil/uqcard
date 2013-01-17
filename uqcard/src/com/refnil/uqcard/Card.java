package com.refnil.uqcard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout
{
	private int cost;
	private String name;
	private String description;
	private String flavorText;
	private ImageView art;
	
	public Card(Context c,int cardId)
	{
		super(c);
		// a faire apres la bd
	}
	
	public Card(Context c,String name, String description,String flavor)
	{
		super(c);
		
	}

	public ImageView getArt() {
		return art;
	}

	public void setArt(ImageView art) {
		this.art = art;
		
	}
	
}
