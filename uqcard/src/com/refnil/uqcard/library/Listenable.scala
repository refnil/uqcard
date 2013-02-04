package com.refnil.uqcard.library

import scala.collection.mutable.Set

trait Listenable[T] {
  private val listeners:Set[Listener[T]] = Set()
  
  def subscribe(l: Listener[T]) = listeners add l
  def unsubscribe(l: Listener[T]) = listeners remove l
  protected def messageListener(m:T) = listeners.foreach(x => x.onMessage(m))
  protected def closeListener() = listeners.foreach(x => x.onClose())
}