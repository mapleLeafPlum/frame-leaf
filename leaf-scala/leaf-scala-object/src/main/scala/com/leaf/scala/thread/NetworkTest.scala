package com.leaf.scala.thread

object NetworkTest {
  def main(args: Array[String]): Unit = {
    println("staring.........")
    (new NetworkService(8125,3)).run
    println("end.........")
  }
}
