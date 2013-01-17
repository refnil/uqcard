package com.refnil.uqcard.library

import scala.actors.Actor
import scala.collection.mutable.Map

object Link {
  def getLinkedLink() = {
    val l1 = new Link
    val l2 = new Link
    
    l1 setLinked l2
    l2 setLinked l1
    (l1,l2)
  }
}

class Link private() {
  private var otherLink:Link = null
  private def setLinked(l:Link) = {
    otherLink = l
  }
  
  val map = Map[Int,Actor]()
  
  def receive(m:Message) = {}
  def send(f:Actor,m:Message) = otherLink receive m

}