package com.refnil.uqcard.library.test

import com.refnil.uqcard.library.Listener
import com.refnil.uqcard.library.PlayerListener
import com.refnil.uqcard.library.Player

class DummyPlayer extends Listener[String] {

  def onMessage(m:String) = {
    print("Dummy received:")
    println(m)
  }
  def onClose() = {}

}