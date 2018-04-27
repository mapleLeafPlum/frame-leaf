package com.leaf.scala

object ForTest {

  def main(args: Array[String]): Unit = {
    for1();
  }

  def for1(): Unit ={
    var a = 0;
    // for 循环
    for( a <- 1 to 10){
      println( "to Value of a: " + a );
    }

    // for 循环
    for( a <- 1 until 10){
      println( "until Value of a: " + a );
    }

    var b = 0;
    // for 循环
    for( a <- 1 to 3; b <- 1 to 3){
      println( "Value of a: " + a );
      println( "Value of b: " + b );
    }
    println("double -----------------------------")
    for( a <- 1 to 3){
      println( "Value of a: " + a );
      for( b <- 1 to 3){
        println( "Value of b: " + b );
      }
    }
    println("double -----------------------------")
    for( a <- 1 to 3){
      for( b <- 1 to 3){
        println( "Value of a: " + a );
        println( "Value of b: " + b );
      }
    }

    println("double -----------------------------")
    val numList = List(1,2,3,4,5,6);
    // for 循环
    for( a <- numList ){
      println( "list Value of a: " + a );
    }
    println("double -----------------------------")
    // for 循环
    for( a <- numList
         if a != 3; if a < 8 ){
      println( "Value of a: " + a );
    }

    println("double -----------------------------")
    // for 循环
    var retVal = for{ a <- numList
                      if a != 3; if a < 8
    }yield a
    println(retVal)
    // 输出返回值
    for( a <- retVal){
      println( "Value of a: " + a );
    }

  }

}
