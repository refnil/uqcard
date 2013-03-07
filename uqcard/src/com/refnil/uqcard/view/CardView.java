package com.refnil.uqcard.view;

import com.refnil.uqcard.R;
import com.refnil.uqcard.data.Card;

import android.content.Context;

//import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class CardView extends RelativeLayout {

	private int imageBackground;
	private Card card;
	private LayoutInflater inflater;
	private View layoutCard;

	public CardView(Context c, AttributeSet atrs) {
		super(c, atrs);

	}

	public CardView(Context c, int cardId) {
		super(c);
		// a faire apres la bd
		/*
		 * TypedArray ta = c.obtainStyledAttributes(R.styleable.Gallery1);
		 * imageBackground =
		 * ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground,
		 * 1);
		 * 
		 * inflater = (LayoutInflater)c.getSystemService
		 * (Context.LAYOUT_INFLATER_SERVICE); layoutCard =
		 * inflater.inflate(R.layout.layout_card, null);
		 * 
		 * ((TextView)
		 * layoutCard.findViewById(R.id.cost)).setText(Integer.toString
		 * (card.getCost())); ((TextView)
		 * layoutCard.findViewById(R.id.name)).setText(card.getName());
		 * ((TextView)
		 * layoutCard.findViewById(R.id.description)).setText(card.getDescription
		 * ()); ((TextView)
		 * layoutCard.findViewById(R.id.flavor)).setText(card.getFlavor());
		 */

	}

	public CardView(Context c, Card card) {
		super(c);
		this.card = card;

		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutCard = inflater.inflate(R.layout.layout_card, null);

		((TextView) layoutCard.findViewById(R.id.cost)).setText(Integer
				.toString(card.getCost()));
		((TextView) layoutCard.findViewById(R.id.name)).setText(card.getName());
		((TextView) layoutCard.findViewById(R.id.description)).setText(card
				.getDescription());
		((TextView) layoutCard.findViewById(R.id.flavor)).setText(card
				.getFlavor());

	}

	public CardView(Context c, String name, String description, String flavor,
			int cost) {
		super(c);
		card = new Card(name, description, flavor, cost);

		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutCard = inflater.inflate(R.layout.layout_card, null);

		((TextView) layoutCard.findViewById(R.id.cost)).setText(Integer
				.toString(card.getCost()));
		((TextView) layoutCard.findViewById(R.id.name)).setText(card.getName());
		((TextView) layoutCard.findViewById(R.id.description)).setText(card
				.getDescription());
		((TextView) layoutCard.findViewById(R.id.flavor)).setText(card
				.getFlavor());

	}

	public Card getCard() {
		return this.card;
	}
	
	public void setCard(Card card){
		this.card = card;
	}

	public View getCardView(Context c, int width, int height) {

		WindowManager wm = (WindowManager) c
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		layoutCard.setLayoutParams(new LayoutParams(size.x, size.y));

		return layoutCard;
	}

	public ImageView getCardImageView(Context c, int width, int height) {

		layoutCard.setLayoutParams(new LayoutParams(width, height));

		View v = this.getCardView(c, 150, 120);

		Bitmap viewCapture = loadBitmapFromView(v);
		ImageView iv = new ImageView(c);

		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		iv.setLayoutParams(new Gallery.LayoutParams(150, 120));
		iv.setBackgroundResource(imageBackground);
		iv.setImageBitmap(viewCapture);

		return iv;
	}

	public Bitmap loadBitmapFromView(View v) {
		Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width,
				v.getLayoutParams().height, Bitmap.Config.RGB_565);
		Canvas c = new Canvas(b);
		v.measure(MeasureSpec.makeMeasureSpec(v.getLayoutParams().width,
				MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
				v.getLayoutParams().height, MeasureSpec.EXACTLY));
		v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
		v.draw(c);
		return b;
	}

}


