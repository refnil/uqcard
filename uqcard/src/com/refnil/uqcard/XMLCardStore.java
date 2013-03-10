package com.refnil.uqcard;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.SparseArray;

public class XMLCardStore implements CardStore {

	private SparseArray<Card> cardMap = new SparseArray<Card>();

	public XMLCardStore() {
	
	}

	public Card getCard(int id) {
		// TODO Auto-generated method stub
		return cardMap.get(id);
	}

	public void parse(int R_XML) throws XmlPullParserException, IOException {
		XmlResourceParser xrp = Resources.getSystem().getXml(R_XML);
		xrp.next();
		int eventType = xrp.getEventType();
		
		while (eventType != XmlPullParser.END_DOCUMENT) {
			
			if (eventType == XmlPullParser.START_DOCUMENT) {

			} else if (eventType == XmlPullParser.START_TAG) {

			} else if (eventType == XmlPullParser.END_TAG) {

			} else if (eventType == XmlPullParser.TEXT) {

			}
			eventType = xrp.next();
		}
	}

}
