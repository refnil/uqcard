package com.refnil.uqcard.library

import scala.actors.Actor

class Player(serveur: User[Message]) extends User[Message] {

  var cpt = 6
  
  def init() = serveur ! Connect

  def receivedMessage(m: Message) = m match {
    case Talk(mess) =>
      println(mess)
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