package com.refnil.uqcard.library

import scala.actors.Actor
import scala.actors.OutputChannel
import scala.collection.mutable.LinkedList

class Server extends Actor {
  var players:List[OutputChannel[Any]] = List()
  
  def act(){
    loop{
      react{
        case Connect => 
          players = sender::players
          println(sender)
        case Disconnect =>
          players = players.filter(x => sender != x)
          println("Disconnect")
        case Talk(mess) =>
          players.foreach(x => x ! Talk(mess))
        case Quit => exit()
      }
    }
  }

}