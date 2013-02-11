package com.refnil.uqcard.library;

import android.os.Looper;
import android.os.Messenger;

import com.refnil.uqcard.library.message.UqcardMessage;

public class ProxyServer extends AbstractServer {

	public ProxyServer(Looper looper, LinkConnection linkConnection) {
		super(looper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleUqcardMessage(Messenger sender, UqcardMessage um) {
		// TODO Auto-generated method stub

	}

}
