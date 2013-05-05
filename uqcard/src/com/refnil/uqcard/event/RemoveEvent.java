package com.refnil.uqcard.event;

public class RemoveEvent extends Event{
	
	private static final long serialVersionUID = -1482376972867671786L;
	final int uid;
	final int position;
	
	public RemoveEvent(int carduid,int position) {
		super(Event_Type.REMOVE_CARD);
		uid = carduid;
		this.position = position;
	}
	
	public int getuid()
	{
		return uid;
	}
	
	public int getPosition()
	{
		return position;
	}
}
