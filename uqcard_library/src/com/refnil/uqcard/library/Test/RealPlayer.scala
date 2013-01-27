package com.refnil.uqcard.library.Test

import com.refnil.uqcard.library.PlayerListener
import com.refnil.uqcard.library.Player

class RealPlayer extends PlayerListener {

  def listened(p: Player, s: String): Unit = {
    println("Real player received:" , s);
    val mess = readLine
    p fromListener mess
  }

}