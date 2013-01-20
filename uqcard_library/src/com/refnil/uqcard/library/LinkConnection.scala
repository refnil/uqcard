package com.refnil.uqcard.library

import scala.collection.mutable.Map

abstract class LinkConnections(s: Server=null) {

  val map: Map[Int, Link] = Map()

  val server: User[Message] =
    if (s != null)
      s
    else
      new ServeurProxy(this)

  def start()
  def send(m: Message)
  
  def receive(mess: Message) = {
    mess match {
      case IdMessage(id, m) =>
        map.get(id) match {
          case Some(link) => link.receive(m)
          case None => 
            val p = PlayerProxy(s,this)
            val l = p.link()
            p start()
            l.receive(m)
        }
    }
  }
  
  def newLink(p: User[Message], forward:User[Message]): Link = {
    val id = map size
    val l = new Link(id, this, p, forward)
    map += ((id, l))
    return l
  }

}