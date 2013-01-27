package com.refnil.uqcard.library

import com.refnil.uqcard.library.Test.DummyPlayer
import com.refnil.uqcard.library.Test.RealPlayer

object mainTest {
  def shishi() = "RATON2"
  def main(args: Array[String]): Unit = {
    val s = new Server()
    s start
    
    val (l1,l2) = LinkConn(s)
    
    val p1 = new Player(l1.server)
    val p2 = new Player(l2.server)
    
    p1.start()
    p2.start()
    
    val dp = new DummyPlayer()
    p2.inscription(dp)
    
    val rp = new RealPlayer()
    p1.inscription(rp)
    
    p1 ! Talk("Start")
  }

}