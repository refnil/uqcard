package com.refnil.uqcard.library

import scala.collection.mutable.Map

class ServeurProxy(lc: LinkConnections) extends User[Message] {
  val map: Map[User[Message], Link] = Map()

  def init() = {}

  def receivedMessage(m: Message) = {
    print("Serveur proxy: ")
    println(m)
    m match {
      case Trans(u, mess) =>
        u ! mess
      case _ =>
        sender match {
          case u: User[Message] =>
            map.get(u) match {
              case Some(l) => l.send(m)
              case None =>
                val l = lc.newLink(this, u)
                map += ((u, l))
                l.send(m)
            }

        }
    }
  }

  def receivedElse(a: AnyRef) {}

}