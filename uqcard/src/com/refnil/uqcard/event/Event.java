package com.refnil.uqcard.event;

import java.io.Serializable;


public abstract class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8632370455495023669L;
	public final Event_Type type;
	public int id;

	public Event(Event_Type t) {
		type = t;
	}
}