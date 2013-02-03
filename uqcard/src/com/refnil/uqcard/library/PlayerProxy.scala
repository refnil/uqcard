package com.refnil.uqcard.library

object PlayerProxy {
  def apply(s: Server, lc: LinkConnections): PlayerProxy = {
    val p = new PlayerProxy(s)
    val l = lc.newLink(p, s)
    p.l = l
    p
  }
}

class PlayerProxy private (val s: Server) extends User[Message] {
  private var l: Link = null

  def link(): Link = l

  def init() = {}

  def receivedMessage(m: Message) = {
    print("PlayerProxy: ")
    println(m)
    m match {
      case Trans(u, mess) =>
        u ! mess
      case _ =>
        println("Message to send")
        l.send(m)
    }
  }

  def receivedElse(a: AnyRef) = {println("ded")}

}