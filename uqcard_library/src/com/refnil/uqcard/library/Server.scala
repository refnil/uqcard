package com.refnil.uqcard.library

import scala.actors.Actor
import scala.actors.OutputChannel
import scala.collection.mutable.LinkedList

class Server extends Actor {
  var players:List[Player] = List()
  
  def act(){
    loop{
      react{
        case Connect => 
          players = sender match {
            case p:Player => p::players
            case _ => players
          }
          println(sender)
        case Disconnect =>
          players = sender match {
            case p:Player => players.filter(x => p != x)
            case _ => players
          }
          println("Disconnect")
        case Talk(mess) =>
          players.foreach(x => x ! Talk(mess))
        case Close => 
          players foreach (x => x ! Close)
          exit()
      }
    }
  }

}