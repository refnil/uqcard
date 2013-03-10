package com.refnil.uqcard.event;

import com.refnil.uqcard.view.CardView;
import com.refnil.uqcard.view.ImageAdapter;
import com.refnil.uqcard.view.SemiClosedSlidingDrawer;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Gallery;

public class BoardOnTouchListener implements OnTouchListener {
	private float pointY;
	private SemiClosedSlidingDrawer slider;
	private Gallery gallery;
	private EventManager em;

	public BoardOnTouchListener(SemiClosedSlidingDrawer s, Gallery g, EventManager em) {
		slider = s;
		setGallery(g);
		setEm(em);
	}

	public boolean onTouch(View v, MotionEvent event) {
		float eventY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pointY = eventY;
			return true;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			if (eventY < pointY) {
				if (slider.isOpened()) {
					CardView ca = ((ImageAdapter) gallery.getAdapter())
							.getCardView(gallery.getSelectedItemPosition(),
								null, null);
					getEm().setSelectedCardHand(ca.getCard().getUid());
					slider.animateClose();
					pointY = 0;
				}
			}
			break;
		default:
			return false;
		}
		return false;
	}

	public Gallery getGallery() {
		return gallery;
	}

	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}

	public EventManager getEm() {
		return em;
	}

	public void setEm(EventManager em) {
		this.em = em;
	}

}
