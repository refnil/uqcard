package com.refnil.uqcard.library;

import java.io.Serializable;

import com.refnil.uqcard.library.message.UqcardMessage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Bundle;
import android.util.Log;
import android.os.RemoteException;

abstract public class AbstractActor {

	final private static String TAG = "AbstractActor";
	final private static String BUNDLE_KEY = "UQCARDMESSAGE";
	private Messenger messenger;

	private AbstractActorHandler aah;

	public AbstractActor(Looper looper) {
		aah = new AbstractActorHandler(looper);
		messenger = new Messenger(aah);
	}

	protected abstract void handleUqcardMessage(Messenger sender,
			UqcardMessage um);

	protected void sendTo(Messenger mess, UqcardMessage um)
			throws RemoteException {
		Message m = Message.obtain();
		m.replyTo = messenger;
		Bundle b = new Bundle();
		b.putSerializable(BUNDLE_KEY, um);
		m.setData(b);
		mess.send(m);
	}

	public Messenger getMessenger() {
		return messenger;
	}
	
	public void close(){
		aah.getLooper().quit();
	}

	private final class AbstractActorHandler extends Handler {

		public AbstractActorHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			Serializable o = msg.getData().getSerializable(BUNDLE_KEY);
			if (o != null) {
				//Log.i(TAG,o.toString());
				handleUqcardMessage(msg.replyTo, (UqcardMessage) o);
			} else {
				Log.i(TAG, "received something else");
			}
		}
	};

}
