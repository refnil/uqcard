package com.refnil.uqcard.library.Test

import com.refnil.uqcard.library.PlayerListener
import com.refnil.uqcard.library.Player

class DummyPlayer extends PlayerListener {

  def listened(p: Player, s: String): Unit = {
    println("DUMMY: ",s)
    p fromListener s
    
  }

}