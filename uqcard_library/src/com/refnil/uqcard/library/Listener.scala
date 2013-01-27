package com.refnil.uqcard.library

trait Listener[T,P] {
  
  def onMessage(p:P,m:T)
  def onClose(p:P)

}