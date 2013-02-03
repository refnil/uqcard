package com.refnil.uqcard.library

import scala.actors.Actor
import scala.collection.mutable.Set;

class Player(val serveur: User[Message]) extends User[Message] with Listenable[String,Player]{

  var cpt = 6
  
  def tell(s:String) = this ! Trans(serveur,Talk(s))
  
  protected def getSender = this
  
  def init() = serveur ! Connect
  def receivedMessage(m: Message) = m match {
    case Talk(mess) =>
      messageListener(mess)
      println("Player:",mess)
      cpt = cpt - 1
      if (cpt < 1) {
        serveur ! Disconnect
        exit()
      }
    case Trans(a,m) => a ! m
    case Close => 
      closeListener()
      exit()
  }
  def receivedElse(a: AnyRef) = messageListener("Player closing")
  
  

}