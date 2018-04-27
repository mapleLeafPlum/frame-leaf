package com.leaf.scala.akka

import akka.actor.{Actor, ActorSystem, Props}

object Akka2 {
  class Actor1 extends Actor {
    def receive = {
      case msg:String =>{
        println("Actor1:"+msg)
        val b = context.actorOf(Props[Actor2]);
        b ! "我来回复你的消息来"
      }
    }
  }
  class Actor2 extends Actor {
    def receive = {
      case msg:String =>println("Actor2:"+msg)
    }
  }

  def main(args: Array[String]): Unit = {
    val sys = ActorSystem("sys");
    val a = sys.actorOf(Props[Actor1])
    a ! "你好，Actor1~~我要给你发送消息来"
  }

}
