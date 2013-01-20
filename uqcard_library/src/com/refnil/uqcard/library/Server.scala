package com.refnil.uqcard.library

import scala.actors.Actor
import scala.actors.OutputChannel
import scala.collection.mutable.LinkedList

class Server extends User[Message] {
  var players: List[User[Message]] = List()
  
  def init() = {}

  def receivedMessage(m: Message) = m match {
    case Connect =>
      players = sender match {
        case p: User[Message] => p :: players
        case _ => players
      }
      println(sender)
    case Disconnect =>
      players = sender match {
        case p: Player => players.filter(x => p != x)
        case _ => players
      }
      println("Disconnect")
    case Talk(mess) =>
      players.foreach(x => x ! Talk(mess))
    case Close =>
      players foreach (x => x ! Close)
      exit()
  }

  def receivedElse(a: AnyRef) = println("Received other shit")
}