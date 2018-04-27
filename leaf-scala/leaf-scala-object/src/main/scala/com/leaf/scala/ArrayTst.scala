package com.leaf.scala

object ArrayTst {
  def main(args: Array[String]): Unit = {

  }

  def simple(): Unit ={
    var tst="big data big tspark scala spark hadoop hadoop hive spark"
    var listTest=tst.split(" ").toList
    println("________________")
    println(listTest.groupBy(_.trim))
    println("________________")

    println(listTest)
    println(listTest.map((_,1)))
    println(listTest.map((_,1)).groupBy(_._1))
    println(listTest.map((_,1)).groupBy(_._1).mapValues(_.map(_._2)))
    println(listTest.map((_,1)).groupBy(_._1).mapValues(_.map(_._1)))
    println(listTest.map((_,1)).groupBy(_._1).mapValues(_.map(_._2).reduce(_+_)))
    println(listTest.map((_,1)).groupBy(_._1).mapValues(_.map(_._1).reduce(_+_)))

  }

  def complex(): Unit ={
    var tst="big data big tspark scala spark hadoop hadoop hive spark"
    var listTest=tst.split(" ").toList
    println("________________")
    println(listTest.groupBy(_.trim))
    println("________________")

    println(listTest)
    println(listTest.map((_,1)))
    println(listTest.map((_,1)).groupBy(_._1))
    println(listTest.map((_,1)).groupBy(_._1).mapValues(_.map(_._2)))
    println(listTest.map((_,1)).groupBy(_._1).mapValues(_.map(_._1)))
    println(listTest.map((_,1)).groupBy(_._1).mapValues(_.map(_._2).reduce(_+_)))
    println(listTest.map((_,1)).groupBy(_._1).mapValues(_.map(_._1).reduce(_+_)))
  }

  def getSource(): Unit ={
    var sb:StringBuilder=null;
    for(i <- 0 to 10000){
      sb.append("")
    }
  }


}
