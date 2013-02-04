package com.refnil.uqcard.library

import com.refnil.uqcard.Event

sealed abstract class Message extends Serializable
case class IdMessage(id:Int,m:Message) extends Message //For communication trough proxy

case class ConnectPlayer(name:String) extends Message
case class YouAre(id:Int) extends Message
case class ConnectedPlayer(name: String) extends Message
case class DisconnectPlayer() extends Message
case class DisconnectedPlayer(name:String) extends Message

case class Close() extends Message

case class RequestServer(e:Event) extends Message
case class EventMessage(e:Event) extends Message

case class OKServer() extends Message
case class Ask(mess:String) extends Message
case class Talk(mess:String) extends Message

case class Trans(u:User[Message],m:Message) extends Message

