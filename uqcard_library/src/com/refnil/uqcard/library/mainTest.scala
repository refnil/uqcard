package com.refnil.uqcard.library

object mainTest {
  def shishi() = "RATON2"
  def main(args: Array[String]): Unit = {
    val s = new Server

    val p = new Player(s)

    s start ()
    p start ()
    s ! Talk("Lol")

    val (lc1, lc2) = LinkConn(s)

    val sp = lc2.server
    sp start

    val p2 = new Player(lc2.server)
    p2 start ()
    s ! Talk("HEY")
    readLine
    s ! Talk("HEY2")

    s ! Close
  }

}