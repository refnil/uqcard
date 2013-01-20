package com.refnil.uqcard.library

import scala.actors.Actor

trait User[T >: Message] extends Actor{
    def init()
	def receivedMessage(m : T)
	def receivedElse(a : AnyRef)
	
	def act() = {
      init()
	  loop {
	    react {
	      case m : T => receivedMessage(m)
	      case a : AnyRef => receivedElse(a)
	    }
	  }
	}
    
    
}