package com.leaf.scala.akka
import  akka.actor.Actor._

object Akka1 {
  class Person(name:String){
    val namex = name;
  }

  def main(args: Array[String]): Unit = {
    /*val myActor = actor{
      receive{
        case msg:String => println("字符串："+msg)
        case msg:Int => println("整形："+msg)
        case msg:Person => println("Person："+msg.namex)
      }
    }
    myActor ! new Person("zhang");*/
  }
}
