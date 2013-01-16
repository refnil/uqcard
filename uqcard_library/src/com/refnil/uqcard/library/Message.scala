package com.refnil.uqcard.library

abstract class Message
case object Connect extends Message
case object Disconnect extends Message
case object Quit extends Message
case class Talk(mess:String) extends Message
case class Trans(m:Message) extends Message

