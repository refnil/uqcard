package com.refnil.uqcard.view;

import com.refnil.uqcard.R;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FullCardFragment extends Fragment{
	private CardView cardDisplayed = null;
	
	
	public FullCardFragment()
	{
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_full_card,
		        container, false);
		if(getCardView() != null)
		{
			ImageView iv = (ImageView)view.findViewById(R.id.ImageFullScreen);
			Bitmap bm = getCardView().loadBitmapFromView(getCardView());
			iv.setImageBitmap(bm);
			return view;
		}
		return view;
	}
	
	public void setCardView(CardView c)
	{
		this.cardDisplayed = c;
	}
	
	public CardView getCardView()
	{
		return this.cardDisplayed;
	}
}
