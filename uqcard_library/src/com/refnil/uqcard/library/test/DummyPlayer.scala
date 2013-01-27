package com.refnil.uqcard.library.test

import com.refnil.uqcard.library.PlayerListener
import com.refnil.uqcard.library.Player

class DummyPlayer extends PlayerListener {

  def listened(p: Player, s: String): Unit = {
    println("DUMMY: ",s)
  }

}