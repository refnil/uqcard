package com.refnil.uqcard;

import android.content.Context;
import android.widget.RelativeLayout;

public class Card extends RelativeLayout
{

	private int cost;
	private String name;
	private String description;
	private String flavor;
	private long id;
	private byte[] image;

	Card(String name, String description, String flavor, int cost)
	{
		this.name = name;
		this.description = description;
		this.flavor = flavor;
		this.cost = cost;
	}

	Card(final Card card)
	{
		this(card.getName(),card.getDescription(),card.getFlavor(),card.getCost());
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