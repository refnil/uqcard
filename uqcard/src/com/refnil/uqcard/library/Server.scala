package com.refnil.uqcard.library

import scala.actors.Actor
import scala.actors.OutputChannel
import scala.collection.mutable.Set
import com.refnil.uqcard.Event

class Server extends User[Message] with Listener[Event]{
  
  private var players:Set[(Int,User[Message],String)] = Set()
  private var numberOfPlayer:Int = 0
  
  private val board = new Board()
  private var started = false
  
  //From user[Message]
  def init() = {}

  def receivedMessage(m: Message) = m match {
    case Trans(p,m) => p ! m
    case ConnectPlayer(name) =>
      sender match {
        case p: User[Message] => if(numberOfPlayer<2) {
          numberOfPlayer += 1
          players += Tuple3(numberOfPlayer,p,name)
          p ! YouAre(numberOfPlayer)
          players foreach(x => x._2 ! ConnectedPlayer(name))
        }   
        case _ => 
      }
      println(sender)
    case DisconnectPlayer() =>
      sender match {
        case p: User[Message] => players filter(x => x._2 != p )
        case _ => 
      }
      println("Disconnect")
    case Talk(mess) =>
      players.foreach(x => x._2 ! Talk(mess))
    case Close() =>
      players foreach (x => x._2 ! Close)
      exit()
  }

  def receivedElse(a: AnyRef) = println("Received other shit")
  
  def onMessage(e:Event) = {
    players foreach(x => this ! Trans(x._2,EventMessage(e)))
  }
  
  def onClose() = {
    
  }
}