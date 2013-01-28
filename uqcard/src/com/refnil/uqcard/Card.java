package com.refnil.uqcard;

public class Card
{
	private int cost;
	private String name;
	private String description;
	private String flavor;
	
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
	
	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getFlavor() {
		return flavor;
	}

}