package com.refnil.uqcard;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Gallery;

public class BoardOnTouchListener implements OnTouchListener {
	private float pointY;
	private SemiClosedSlidingDrawer slider;
	private Gallery gallery;

	public BoardOnTouchListener(SemiClosedSlidingDrawer s, Gallery g) {
		slider = s;
		gallery = g;
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
					// POUSSER À LEVENT MANAGER
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

}
