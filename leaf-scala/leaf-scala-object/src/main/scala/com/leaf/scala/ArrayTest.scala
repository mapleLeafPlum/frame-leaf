package com.leaf.scala

import scala.Array._


object ArrayTest {
  def main(args: Array[String]): Unit = {
    test;
  }

  def test(): Unit = {
    //var newarray:Array[String]=new Array[String](10);
    var newarray = new Array[String](10);

    for (i <- 0 until 10) {
      newarray(i) = i + "100";
    }

    var ps = Array("12", "33", "ee");

    for (i <- 0 until ps.length) {
      println(ps(i));
    }


    for (i <- 0 until 10) {
      println(newarray(i));
    }


    var myList = Array(1.9, 2.9, 3.4, 3.5)

    // 输出所有数组元素
    for (x <- myList) {
      println(x)
    }

    // 计算数组所有元素的总和
    var total = 0.0;
    for (i <- 0 until myList.length) {
      total += myList(i);
    }
    println("总和为 " + total);

    // 查找数组中的最大元素
    var max = myList(0);
    for (i <- 1 until myList.length) {
      if (myList(i) > max) max = myList(i);
    }
    println("最大值为 " + max);

    println("最大值为------------------------------------------------ ");
    var myMatrix = ofDim[Int](3, 3)

    // 创建矩阵
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        myMatrix(i)(j) = j;
      }
    }

    // 打印二维阵列
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        print(" " + myMatrix(i)(j));
      }
      println();
    }
    println("最大值为------------------------------------------------ ");
    var myList1 = Array(1.9, 2.9, 3.4, 3.5)
    var myList2 = Array(8.9, 7.9, 0.4, 1.5)

    var myList3 = concat(myList1, myList2)

    // 输出所有数组元素
    for (x <- myList3) {
      println(x)
    }

    println("最大值为------------------------------------------------ ");

    var myList5 = range(10, 20, 2)
    var myList4 = range(10, 20)

    // 输出所有数组元素
    for (x <- myList5) {
      print(" " + x)
    }
    println()
    for (x <- myList4) {
      print(" " + x)
    }
    println("最大值为------------------------------------------------ ");
    var myList6 = Array(1.9, 2.9, 3.4, 3.5)
    var myList7 = Array(8.9, 7.9, 0.4, 1.5)
    myList6.update(0, 233.11);
    for (x <- myList6) {
      print(" " + x)
    }
    println("最大值为------------------------------------------------ ");
    var ts = fill(23)(32);
    for (x <- ts) {
      print(" " + x)
    }
    println("最大值为------------------------------------------------ ");

    val t = (4, 3, 2, 1)
    val sum = t._1 + t._2 + t._3 + t._4
    println("元素之和为: " + sum)

    val t5 = Tuple5(4, 3, 2, 1, "天数");

    println(t5)

    t5.productIterator.foreach(i => println(i));
    t5.productIterator.foreach { i => println(i) };
    println(t5.toString())

    val myMap: Map[String, String] = Map("key1" -> "value1","key2" -> "value3")
    val value1: Option[String] = myMap.get("key1")
    val value2: Option[String] = myMap.get("key2")

    println(value1) // Some("value1")
    println(value2) // None
    println(myMap.get("key3")) // None

    val a:Option[Int] = Some(5)
    val b:Option[Int] = None

    println("a.getOrElse(0): " + a.getOrElse(0) )
    println("b.getOrElse(10): " + b.getOrElse("ddd") )

    println("a.isEmpty: " + a.isEmpty )
    println("b.isEmpty: " + b.isEmpty )
    value2.foreach{(i=>println(i))}


    val it = Iterator("Baidu", "Google", "Runoob", "Taobao")

    while (it.hasNext){
      println(it.next())
    }

    println(it.contains("Baidu"));

    println(it.map[String](i=>i+"fuck"));



  }

}
