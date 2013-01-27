package com.refnil.uqcard.library

object LinkConn{
  def apply(s:Server): (LinkConnections,LinkConnections)={
    val lc1 = new LinkConn(s)
    val lc2 = new LinkConn()
    lc1.lc = lc2
    lc2.lc = lc1
    (lc1,lc2)
  }
}

class LinkConn private(s:Server = null) extends LinkConnections(s) {
  
  private var lc: LinkConn = null

  def start(): Unit = {}

  def send(m: Message): Unit = {
    lc.receive(m)
  }

}