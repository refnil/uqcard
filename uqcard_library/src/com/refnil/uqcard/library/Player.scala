package com.refnil.uqcard.library

import scala.actors.Actor

class Player(serveur: Server) extends Actor {
  
  var cpt = 6

  def act() = {
    serveur ! Connect
    loop {
      react {
        case Talk(mess) =>  
    	println(mess)
    	cpt=cpt-1
    	if(cpt<1) {
    	   serveur ! Disconnect
    	   exit()
    	} 
      }
    }
  }
  
  
}