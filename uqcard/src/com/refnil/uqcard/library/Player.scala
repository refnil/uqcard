package com.refnil.uqcard.library

import scala.actors.Actor
import scala.collection.mutable.Set;
import com.refnil.uqcard.Event
import com.refnil.uqcard.Board

class Player(val serveur: User[Message]) extends User[Message] with Listenable[String] {

  var board: Board= new Board();

  def tell(s: String) = this ! Trans(serveur, Talk(s))

  def sendEvent(e: Event) = sendToServeur(RequestServer(e))
  def connect(name: String) = sendToServeur(ConnectPlayer(name))

  private def sendToServeur(m: Message) = this ! Trans(serveur, m)

  protected def init() = {}

  protected def receivedMessage(m: Message) = m match {
    case Trans(a, m) => a ! m
    case EventMessage(e) => board.receiveEvent(e)
    case YouAre(i) => board.setPlayerID(i)
    case ConnectedPlayer(name:String) => messageListener(name + " has connected.")
    case Talk(mess) => messageListener(mess)

    case Close() =>
      closeListener()
      exit()
  }
  def receivedElse(a: AnyRef) = {}
}