package com.leaf.spark

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object Akka1 {
  def main(args: Array[String]): Unit = {
    //参数配置
    val conf = new java.util.HashMap[String,Object]()
    val list = new java.util.ArrayList[String]()
    list.add("akka.remote.netty.tcp")
    conf.put("akka.remote.enabled-transports", list)  //参数是个集合
    conf.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider")
    conf.put("akka.remote.netty.tcp.hostname", "192.168.1.42")
    conf.put("akka.remote.netty.tcp.port", "44443")

    val sys = ActorSystem("myAkkaClientSys", ConfigFactory.parseMap(conf))
    val actorSystem= sys.actorSelection("akka.tcp://myAkkaServerSys@192.168.1.42:44444/user/actor4")
    for(i <- 0 to 10){
      actorSystem ! "xiaohua"
    }
  }

}
