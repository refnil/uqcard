package com.refnil.uqcard.library

sealed abstract class Message
case object Connect extends Message
case object Disconnect extends Message
case object Close extends Message
case class Ask(mess:String) extends Message
case class Talk(mess:String) extends Message
case class Trans(m:Message) extends Message

