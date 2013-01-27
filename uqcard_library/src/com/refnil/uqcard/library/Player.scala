package com.refnil.uqcard.library

import scala.actors.Actor
import scala.collection.mutable.Set;

class Player(serveur: User[Message]) extends User[Message] {
  
  val listeners:Set[PlayerListener] = Set()

  var cpt = 6
  
  def init() = serveur ! Connect
  
  def inscription(pl:PlayerListener) = listeners.add(pl)
  private def tellToListener(s:String) = listeners.foreach(x => x.listened(this,s))
  def fromListener(s:String) = this ! Trans(serveur,Talk(s))

  def receivedMessage(m: Message) = m match {
    case Talk(mess) =>
      cpt = cpt - 1
      if (cpt < 1) {
        serveur ! Disconnect
        exit()
      }
    case Close => 
      println("Player closing")
      exit()
  }

  def receivedElse(a: AnyRef) = println("Received other shit player")
  
  

}