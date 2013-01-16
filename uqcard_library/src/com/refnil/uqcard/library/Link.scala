package com.refnil.uqcard.library

object Link {
  def getLinkedLink() = {
    val l1 = new Link
    val l2 = new Link
    
    l1 setLinked l2
    l2 setLinked l1
    (l1,l2)
  }
}

class Link private() {
  var otherLink:Link = null
  
  private def setLinked(l:Link) = {
    otherLink = l
  }
  
  def receive(m:Message) = {}
  def send(m:Message) = otherLink receive m

}