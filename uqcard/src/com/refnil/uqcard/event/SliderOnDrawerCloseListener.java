package com.refnil.uqcard.event;

import android.widget.Gallery;

import com.refnil.uqcard.view.SemiClosedSlidingDrawer.OnDrawerCloseListener;

public class SliderOnDrawerCloseListener implements OnDrawerCloseListener {

	private Gallery gallery;

	public SliderOnDrawerCloseListener(Gallery g) {
		gallery = g;
	}

	public void onDrawerClosed() {
		gallery.setScaleY((float) 0.9);
		gallery.setScaleX((float) 0.9);
		gallery.setTranslationY(-7);

	}
}
