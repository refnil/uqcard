package com.refnil.uqcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CardsDataSource {

	// Database fields
	private Context c;
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] cardsColumns = { MySQLiteHelper.CARDS_ID,
			MySQLiteHelper.CARDS_NAME, MySQLiteHelper.CARDS_DESCRIPTION,
			MySQLiteHelper.CARDS_FLAVOR, MySQLiteHelper.CARDS_COST,
			MySQLiteHelper.CARDS_IMAGE };
	private String[] creatureColumns = { MySQLiteHelper.CREATURECARDS_IDCARD,
			MySQLiteHelper.CREATURECARDS_ATTACK,
			MySQLiteHelper.CREATURECARDS_DEFENSE,
			MySQLiteHelper.CREATURECARDS_HEALTH };

	public CardsDataSource(Context context) {
		setC(context);
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Card createCard(Card myCard) {
		ContentValues newCard = new ContentValues();
		newCard.put(MySQLiteHelper.CARDS_NAME, myCard.getName());
		newCard.put(MySQLiteHelper.CARDS_DESCRIPTION, myCard.getDescription());
		newCard.put(MySQLiteHelper.CARDS_FLAVOR, myCard.getFlavor());
		newCard.put(MySQLiteHelper.CARDS_COST, myCard.getCost());
		newCard.put(MySQLiteHelper.CARDS_IMAGE, myCard.getImage());
		long insertId = database.insert(MySQLiteHelper.TABLE_CARDS, null,
				newCard);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CARDS,
				cardsColumns, MySQLiteHelper.CARDS_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		myCard.setId(cursor.getLong(MySQLiteHelper.CARDS_NUM_ID));
		cursor.close();
		if (myCard instanceof CreatureCard) {
			newCard = new ContentValues();
			newCard.put(MySQLiteHelper.CREATURECARDS_IDCARD, myCard.get_Id());
			newCard.put(MySQLiteHelper.CREATURECARDS_ATTACK,
					((CreatureCard) myCard).getAttack());
			newCard.put(MySQLiteHelper.CREATURECARDS_DEFENSE,
					((CreatureCard) myCard).getDefense());
			newCard.put(MySQLiteHelper.CREATURECARDS_HEALTH,
					((CreatureCard) myCard).getHealth());
			insertId = database.insert(MySQLiteHelper.TABLE_CREATURECARDS,
					null, newCard);
		}
		return myCard;
	}

	public void deleteComment(Card myCard) {
		long id = myCard.get_Id();
		database.delete(MySQLiteHelper.TABLE_CARDS, MySQLiteHelper.CARDS_ID
				+ " = " + id, null);
		if (myCard instanceof CreatureCard)
			database.delete(MySQLiteHelper.TABLE_CREATURECARDS,
					MySQLiteHelper.CREATURECARDS_IDCARD + " = " + id, null);
	}

	public void changeCard(Card newCard) {
		long id = newCard.get_Id();
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.CARDS_NAME, newCard.getName());
		values.put(MySQLiteHelper.CARDS_DESCRIPTION, newCard.getDescription());
		values.put(MySQLiteHelper.CARDS_FLAVOR, newCard.getFlavor());
		values.put(MySQLiteHelper.CARDS_COST, newCard.getCost());
		values.put(MySQLiteHelper.CARDS_IMAGE, newCard.getImage());

		database.update(MySQLiteHelper.TABLE_CARDS, values,
				MySQLiteHelper.CARDS_ID + " = " + id, null);

		if (newCard instanceof CreatureCard) {
			values.clear();
			values.put(MySQLiteHelper.CREATURECARDS_ATTACK,
					((CreatureCard) newCard).getAttack());
			values.put(MySQLiteHelper.CREATURECARDS_DEFENSE,
					((CreatureCard) newCard).getDefense());
			values.put(MySQLiteHelper.CREATURECARDS_HEALTH,
					((CreatureCard) newCard).getHealth());
			database.update(MySQLiteHelper.TABLE_CREATURECARDS, values,
					MySQLiteHelper.CREATURECARDS_IDCARD + " = " + id, null);
		}
	}

	public Card getCard(int id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CARDS,
				cardsColumns, MySQLiteHelper.CARDS_ID + " = " + id, null, null,
				null, null);
		Card myCard = cursorToCard(cursor);
		cursor.close();
		cursor = database.query(MySQLiteHelper.TABLE_CREATURECARDS,
				creatureColumns,
				MySQLiteHelper.CREATURECARDS_IDCARD + "=" + id, null, null,
				null, null);
		if (cursor.getCount() == 1) {
			cursor.moveToFirst();
			//FIXME
			/*CreatureCard aCard = new CreatureCard(c, myCard.get_Id(),
					myCard.getName(), myCard.getDescription(),
					myCard.getFlavor(), myCard.getCost(), myCard.getImage(),
					cursor.getInt(MySQLiteHelper.CREATURECARDS_NUM_ATTACK),
					cursor.getInt(MySQLiteHelper.CREATURECARDS_NUM_DEFENSE),
					cursor.getInt(MySQLiteHelper.CREATURECARDS_NUM_HEALTH));
			myCard = aCard;*/
		}
		return myCard;
	}

	private Card cursorToCard(Cursor cursor) {
		Card aCard = new Card(cursor.getLong(MySQLiteHelper.CARDS_NUM_ID),
				cursor.getString(MySQLiteHelper.CARDS_NUM_NAME),
				cursor.getString(MySQLiteHelper.CARDS_NUM_DESCRIPTION),
				cursor.getString(MySQLiteHelper.CARDS_NUM_FLAVOR),
				cursor.getInt(MySQLiteHelper.CARDS_NUM_COST),
				cursor.getBlob(MySQLiteHelper.CARDS_NUM_IMAGE));
		return aCard;
	}

	public Context getC() {
		return c;
	}

	public void setC(Context c) {
		this.c = c;
	}
}
