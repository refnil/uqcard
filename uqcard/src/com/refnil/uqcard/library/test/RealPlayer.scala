package com.refnil.uqcard.library.test

import com.refnil.uqcard.library.Listener
import com.refnil.uqcard.library.PlayerListener
import com.refnil.uqcard.library.Player

class RealPlayer(p: Player) extends Listener[String] {

  def onMessage(s: String): Unit = {
    println("Real player received:" , s);
    val mess = readLine
    p tell mess
  }
  def onClose() = {}

}