package com.refnil.uqcard.library

sealed abstract class Message extends Serializable
case class IdMessage(id:Int,m:Message) extends Message
case object Connect extends Message
case object Disconnect extends Message
case object Close extends Message
case object RequestServer extends Message
case object OKServer extends Message
case class Ask(mess:String) extends Message
case class Talk(mess:String) extends Message
case class Trans(u:User[Message],m:Message) extends Message

