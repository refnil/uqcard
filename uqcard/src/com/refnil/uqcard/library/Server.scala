package com.refnil.uqcard.library

import scala.actors.Actor
import scala.actors.OutputChannel
import scala.collection.mutable.LinkedList
import com.refnil.uqcard.library.logic.Board

class Server extends User[Message] {
  
  private var connected: List[User[Message]] = List()
  
  private val board = new Board
  private var started = false
  
  //From user[Message]
  def init() = {}

  def receivedMessage(m: Message) = m match {
    case Connect() =>
      connected = sender match {
        case p: User[Message] => p :: connected
        case _ => connected
      }
      println(sender)
    case Disconnect() =>
      connected = sender match {
        case p: Player => connected.filter(x => p != x)
        case _ => connected
      }
      println("Disconnect")
    case Talk(mess) =>
      connected.foreach(x => x ! Talk(mess))
    case Close() =>
      connected foreach (x => x ! Close)
      exit()
  }

  def receivedElse(a: AnyRef) = println("Received other shit")
}