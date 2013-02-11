package com.refnil.uqcard.library.message;

import java.io.Serializable;

public abstract class UqcardMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4496947639996702674L;

	public <T extends UqcardMessage> T get() {
		T ret = null;
		try {
			ret = (T) this;
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
		return ret;
	}

	public <T extends UqcardMessage> boolean as(T out) {
		out = this.get();
		return out != null;
	}

}
