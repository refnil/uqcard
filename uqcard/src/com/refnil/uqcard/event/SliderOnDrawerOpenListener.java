package com.refnil.uqcard.event;

import android.widget.Gallery;

import com.refnil.uqcard.view.SemiClosedSlidingDrawer.OnDrawerOpenListener;

public class SliderOnDrawerOpenListener implements OnDrawerOpenListener {

	Gallery gallery;

	public SliderOnDrawerOpenListener(Gallery g) {
		gallery = g;
	}

	public void onDrawerOpened() {
		gallery.setScaleY((float) 2);
		gallery.setScaleX((float) 1.5);
		gallery.setTranslationY(55);

	}

}
