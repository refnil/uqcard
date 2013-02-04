package com.refnil.uqcard.library

sealed abstract class Message extends Serializable
case class IdMessage(id:Int,m:Message) extends Message //For communication trough proxy

case class Connect() extends Message 
case class Disconnect() extends Message
case class Close() extends Message
case class RequestServer() extends Message
case class OKServer() extends Message
case class Ask(mess:String) extends Message
case class Talk(mess:String) extends Message
case class Trans(u:User[Message],m:Message) extends Message

