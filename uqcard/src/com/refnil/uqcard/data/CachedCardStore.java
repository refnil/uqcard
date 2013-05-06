package com.refnil.uqcard.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.SparseArray;

public class CachedCardStore implements CardStore {

	private SparseArray<CreatureCard> cardMap = new SparseArray<CreatureCard>();

	private static CachedCardStore store;

	public static void initStore(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String total = "";
		String temp;

		while ((temp = br.readLine()) != null)
			total += temp + "\n";

		store = new CachedCardStore(total);

	}

	public static CachedCardStore getStore() throws CachedStoreNotInitialised {
		if (store == null)
			throw new CachedStoreNotInitialised();
		return store;
	}
	
	public static CachedCardStore initAndGet(InputStream is) throws IOException{
		if(store == null)
			initStore(is);
		return store;
	}

	private CachedCardStore(String s) {

		String[] cardsString = s.split("\n");

		for (String cardString : cardsString) {
			String[] attr = cardString.split(";");
			switch (attr[0].charAt(0)) {
			case 'C':
			case 'c':
				cardMap.append(Integer.valueOf(attr[1]), new CreatureCard(
						attr[2], attr[3], attr[4], Integer.valueOf(attr[5]),
						Integer.valueOf(attr[6]), Integer.valueOf(attr[7]),
						Integer.valueOf(attr[8])));
				break;

			}
		}
	}

	public CreatureCard getCard(int id) {
		// TODO Auto-generated method stub
		return cardMap.get(id);
	}

	public static class CachedStoreNotInitialised extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -924768031744302683L;

	}

}
