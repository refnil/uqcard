package com.refnil.uqcard.library

import scala.actors.Actor

trait User[T >: Message] extends Actor{
    protected def init()
	protected def receivedMessage(m : T)
	protected def receivedElse(a : AnyRef)
	
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