package com.refnil.uqcard.library.test

import com.refnil.uqcard.library.PlayerListener
import com.refnil.uqcard.library.Player

class RealPlayer extends PlayerListener {

  def listened(p: Player, s: String): Unit = {
    println("Real player received:" , s);
    val mess = readLine
    p fromListener mess
  }

}