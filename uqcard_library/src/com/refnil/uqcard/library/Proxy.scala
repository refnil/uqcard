package com.refnil.uqcard.library

import scala.actors.Actor

trait Proxy{
  
  def init()
  def receivedMessage(m : Message) = {}
  def receivedElse(a : AnyRef)
  
  

}