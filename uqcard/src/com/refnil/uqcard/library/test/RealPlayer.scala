package com.refnil.uqcard.library.test

import com.refnil.uqcard.library.Listener
import com.refnil.uqcard.library.PlayerListener
import com.refnil.uqcard.library.Player

class RealPlayer extends Listener[String,Player] {

  def onMessage(p: Player, s: String): Unit = {
    println("Real player received:" , s);
    val mess = readLine
    p tell mess
  }
  def onClose(p:Player) = {}

}