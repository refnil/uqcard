package com.refnil.uqcard.library

object mainTest {

  def main(args: Array[String]): Unit = {
    val serveur = new Server
    serveur start
    
    for(i <- 1 to 1)
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
    
    serveur ! Quit
  }

}