package com.refnil.uqcard;

import android.content.Context;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout
{
	private long id;
	private int cost;
	private String name;
	private String description;
	private String flavor;
	private byte[] image;

	// Pas nécessairement un constructeur ?
	public Card(Context c,int cardId)
	{
		super(c);
		// a faire apres la bd
	}

	public Card(Context c,long cardId,String name, String description,String flavor,int cost,byte[] image)
	{
		super(c);
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setFlavor(flavor);
		this.setCost(cost);
		this.setImage(image);
	}

	public long get_Id() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}