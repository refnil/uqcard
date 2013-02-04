package com.refnil.uqcard.library

trait Listener[T] {
  
  def onMessage(m:T)
  def onClose()

}