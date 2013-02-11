package com.refnil.uqcard;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {

	private int cost;
	private String name;
	private String description;
	private String flavor;

	private long id;
	private byte[] image;
	public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
		public Card createFromParcel(Parcel source) {
			return new Card(source);
		}

		public Card[] newArray(int size) {
			return new Card[size];
		}
	};

	Card(long id, String name, String description, String flavor, int cost,byte[] image) {

		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setFlavor(flavor);
		this.setCost(cost);
		this.setImage(image);
	}

	Card(String name, String description, String flavor, int cost) {
		this.setName(name);
		this.setDescription(description);
		this.setFlavor(flavor);
		this.setCost(cost);
	}

	Card(final Card card) {
		this(card.getName(), card.getDescription(), card.getFlavor(), card
				.getCost());
	}

	public Card(Parcel source) {
		byte[] temp = null;
		this.setId(source.readLong());
		this.setName(source.readString());
		this.setDescription(source.readString());
		this.setFlavor(source.readString());
		this.setCost(source.readInt());
		source.readByteArray(temp);
		this.setImage(temp);
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


	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.get_Id());
		dest.writeString(this.getName());
		dest.writeString(this.getDescription());
		dest.writeString(this.getFlavor());
		dest.writeInt(this.getCost());
		dest.writeByteArray(this.getImage());
	}
}