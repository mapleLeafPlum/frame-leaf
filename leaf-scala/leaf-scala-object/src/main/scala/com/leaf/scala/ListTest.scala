package com.leaf.scala

import java.util.concurrent.ForkJoinPool

import scala.collection.mutable.ListBuffer


object ListTest {
  def main(args: Array[String]): Unit = {
    tet
  }

  def tet(): Unit ={
    val squares = List.tabulate(6)(n => n * n)


    var array=Array(1,2,3,4,5)
    var map=Map();
    squares.copyToArray(array)

    println(array)
    for (i<-array){
      println(i)
    }
    var readable=squares.reverse
    for (i<-readable){
      println(i)
    }

    val testList=List(2,3,4)
    println(5+:testList)
    println(testList.+:(11))
    println(testList.:+(10))
    println(testList.::(30))
    println(testList.map(_+10))
    //println(testList.flatMap(_))
    //println(testList.toMap(_))




    ;;
    var listMutable=ListBuffer(1,2,3,3);
    listMutable.+=(6)
    listMutable.+=:(10)

    println(listMutable)


    var stringList=List("a b v c","e f g","h i j k")

    var flatList=stringList.flatMap(_.split(" "))
    println(flatList)

    var trimList=stringList.flatMap(_.trim)
    println(trimList)

    var makKey=List("a","b","c")
    var makVal=List("1","2","3")
    var mapList=makKey.zip(makVal).toMap//.toMap
    for (i <- mapList.keys){
      println(i)
    }
    for (i <- mapList.values){
      println(i)
    }

    val hostPort = ("localhost", 80)

    println(hostPort._1,hostPort._2)

    val partionList=List(1,2,3,4,5)
    var partisonResult=partionList.partition(_%2==0)
    println(partisonResult._1,partisonResult._2)
    //var mapPartion=partionList.toMap
    //println(mapPartion)

    println(partionList.reduce(_+_))
    var strList=List("net","java","php")
    println(strList.reduce(_+" "+_))
    println(strList.flatMap(_.trim()))


    val startTime = System.currentTimeMillis();  //开始毫秒数
    val list = List(1,2,3,4)
    list.par.reduce{
      (x,y) => println("x:${x},y:${y}" + Thread.currentThread().getName); //当前处理线程名称
        x+y
    }
    println(list.groupBy(x=>x))
    val stopTime = System.currentTimeMillis() - startTime
    println(stopTime-startTime)

    val m = List(("北京",1),("上海",3),("天津",2), ("北京",2), ("北京",1), ("上海",4), ("北京",2))
    val obj=m.groupBy { x => x._1}
    for(key <- obj.keys){
       println(key,obj.get(key))
    }
    println(obj.mapValues(list => list.map(x => x._2).reduce(_+_)))

    obj.mapValues( list => list.map(x => x._2).reduce((x,y) => x+y))

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


   val mapva=Map("c"->2,"b"->3)
   println( mapva.mapValues(_+10))

   var lisd= List(("hadoop",1), ("hadoop",1));
   println( lisd.map(_._2));
  }

}
