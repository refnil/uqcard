package com.refnil.uqcard.library

import scala.actors.Actor
import scala.collection.mutable.Map

class Link(id: Int, lc: LinkConnections, user: User[Message], forward: User[Message]) {
  print("New Link: ")
  println(id,lc,user,forward)
  def receive(m: Message) = {
    user ! Trans(forward, m)
  }
  def send(m: Message) = lc send IdMessage(id, m)
}