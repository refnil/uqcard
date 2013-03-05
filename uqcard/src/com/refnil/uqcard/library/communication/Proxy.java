package com.refnil.uqcard.library.communication;

import android.os.Messenger;

import com.refnil.uqcard.library.message.UqcardMessage;

public interface Proxy {
	void receive(Messenger to, UqcardMessage um);
}
