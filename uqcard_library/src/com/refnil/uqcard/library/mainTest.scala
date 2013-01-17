package com.refnil.uqcard.library

object mainTest {

  def main(args: Array[String]): Unit = {
    val serveur = new Server
    serveur start
    
    for(i <- 1 to 20)
    {
      new Player(serveur) start
    }
    
    serveur ! Talk("Test")
    serveur ! Talk("Test2")
    serveur ! Talk("Test3")
    serveur ! Talk("Test4")
    serveur ! Talk("Test5")
    serveur ! Talk("Test6")
    serveur ! Talk("Test7")
    readLine()
    
    serveur ! Close
    
    val (l1,l2) = Link.getLinkedLink
    println (l1)
    println (l2)
    
  }

}