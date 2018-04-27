package com.leaf.scala.thread

object TestThread {
  def main(args: Array[String]): Unit = {
    var cal=new Thread(new Runnable {
      override def run(): Unit = {
        println("hello world")
      }
    }).start()

  }
}
