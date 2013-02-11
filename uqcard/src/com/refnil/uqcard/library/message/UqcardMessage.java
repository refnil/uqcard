package com.refnil.uqcard.library.message;

import java.io.Serializable;

public abstract class UqcardMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4496947639996702674L;

	public static <T extends UqcardMessage> T get(UqcardMessage o) {
		T ret = null;

		try {
			ret = (T) o;
		} finally {}
		return ret;
	}
	

}
