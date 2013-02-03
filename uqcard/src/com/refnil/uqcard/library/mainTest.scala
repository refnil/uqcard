package com.refnil.uqcard.library

import com.refnil.uqcard.library.test.DummyPlayer
import com.refnil.uqcard.library.test.RealPlayer

object mainTest {
  def shishi() = "RATON2"
  def main(args: Array[String]): Unit = {
    val s = new Server()
    s start
    
    val (l1,l2) = LinkConn(s)
    val (l3,l4) = LinkConn(s)
    
    val p1 = new Player(l2.server)
    val p2 = new Player(l4.server)
    
    p1.start()
    p2.start()
    
    val dp = new DummyPlayer()
    p2.subcribe(dp)
    
    val rp = new RealPlayer()
    p1.subcribe(rp)
    
    p1 ! Talk("Start")
  }

}