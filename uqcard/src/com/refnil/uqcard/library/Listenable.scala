package com.refnil.uqcard.library

import scala.collection.mutable.Set

trait Listenable[T,P] {
  private val listeners:Set[Listener[T,P]] = Set()
  
  def subcribe(l: Listener[T,P]) = listeners add l
  def unsuscribe(l: Listener[T,P]) = listeners remove l
  protected def getSender():P
  protected def messageListener(m:T) = listeners.foreach(x => x.onMessage(getSender,m))
  protected def closeListener() = listeners.foreach(x => x.onClose(getSender))
}