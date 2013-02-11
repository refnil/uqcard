package com.refnil.uqcard.library;

import com.refnil.uqcard.library.message.UqcardMessage;

public interface Proxy {
	void receive(UqcardMessage um);
}
