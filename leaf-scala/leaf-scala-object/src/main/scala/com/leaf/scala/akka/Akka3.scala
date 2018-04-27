package com.leaf.scala.akka

import java.util._

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Akka3 {

  class Actor4 extends Actor {
    def receive = {
      case msg:String =>println("Actor2:"+msg)
      case msg:Student =>println("Actor2:"+msg.namex)
    }
  }

  class Student(name: String) extends Serializable {
    val namex = name
  }


  def main(args: Array[String]): Unit = {
    val conf = new HashMap[String, Object]()

    val list = new ArrayList[String]()
    list.add("akka.remote.netty.tcp")
    conf.put("akka.remote.enabled-transports", list) //参数是个集合
    conf.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider")
    conf.put("akka.remote.netty.tcp.hostname", "192.168.1.42")
    conf.put("akka.remote.netty.tcp.port", "44444")

    val sys = ActorSystem("myAkkaServerSys", ConfigFactory.parseMap(conf));
    sys.actorOf(Props[Actor4],"actor4")
  }
}
