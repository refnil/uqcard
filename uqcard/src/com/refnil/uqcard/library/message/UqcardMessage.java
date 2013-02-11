package com.refnil.uqcard.library.message;

import java.io.Serializable;

import android.util.Log;

public abstract class UqcardMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4496947639996702674L;
	final private static String TAG = "UqcardMessage";

	@SuppressWarnings("unchecked")
	public <T extends UqcardMessage> T get() {
		long l = T.serialVersionUID;
		long l2= this.serialVersionUID;
		T ret = (T) this;
		Log.i(TAG,ret.getClass() + " = " +this.getClass());
		Log.i(TAG,l + " = " +l2);

		ret = ret.getClass().equals(this.getClass()) ? ret : null;

		return ret;
	}

	/*
	 * public <T extends UqcardMessage> boolean as(T out) { out = this.get();
	 * return out != null; }
	 */

}
