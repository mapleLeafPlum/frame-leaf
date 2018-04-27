package com.leaf.scala.demo


import java.io.IOException
/**
 * 内置控制结构
 * scala中没有提供过多的控制结构 只有一些最基本的 因为 可以让用户自定义控制结构
 * 
 * 和java中基本类似
 * if -- 可以带有返回值
 * while -- 和java基本类似 不能带返回值 不推荐使用 用递归替代
 * for -- 和java基本类似 但是功能得到了大幅增强 本身不能带返回值 但是可以通过yield关键字 带会结果 所以可以放心用
 * try catch finnaly -- 和java基本类似 可以带返回值
 * match -- 和java的switch case语句基本类似 但是 可以带返回值 可以应用在更多类型上 不用手动break
 * break continue -- scala没有这两个关键字 如果需要 通过自己控制代码 或 用递归避免使用
 * 访问范围 -- 和java中有所不同
 */
object Dem04 {
  def main(args: Array[String]): Unit = {
    /**if判断*/
//    val num = 225;
//    val result = if(num < 10){
//      "呵呵"
//    }else if(num < 100){
//      "哈哈"
//    }else{
//      "嘻嘻"
//    }
//    println(result)
    
    /**while do..while 循环*/
//    var x = 0;
//    val result = while(x<10){
//      x += 1
//      print(x)
//    }
//    println(result)
    
//    def fx(x:Int):Int={
//      if(x<10)fx(x+1);
//      else return x;
//    }
//    val resultx = fx(0);
//    println(resultx)
    
    /**for 循环*/
    //--增强for循环
//    val list = List(1,3,5,7,9);
//    for(x <- list){
//      print(x*100)
//    }
    //--普通for循环
//    for(x <- 10 to 100){
//      println(x)
//    }
//    val list2 = List(2,4,6,8,10);
//    for(x <- 0 to list2.size - 1){
//      val n = list2(x);
//      print(n*100)
//    }
    //--for实现过滤
    //----案例 打印 1到100的偶数
//    for(x <- 1 to 100){
//      if(x % 2==0){
//        println(x)
//      }
//    }
//    for(x <- 1 to 100; if x%2==0){
//      println(x)
//    }
    //----案例 遍历0到100的偶数 过滤出其中大于30小于80的数
//    for(x <- 0 to 100){
//      if(x % 2==0){
//        if(x>30 && x<80){
//          print(x+" ") 
//        }
//      }
//    }
//    for(x <- 0 to 100;if x % 2 ==0;if x>30;if x<80)print(x+" ")
   
    //--for嵌套循环
    //----案例 实现九九乘法表
//    for(i <- 1 to 9){
//      for(j <- 1 to i){
//        print(i + "*" + j + "=" + i * j + " ")
//      } 
//      println();
//    }
    
//    for(i <- 1 to 9;j <- 1 to i)print(i + "*" + j + "=" + i * j +" ")
    
    //--for流间变量定义
    //----案例 实现九九乘法表 并 实现回车换行
//    for(i <- 1 to 9;j <- 1 to i; val str = if(i==j) "\r\n" else " " )print(i + "*" + j + "=" + i * j +str)

    //--for 利用返回值组成新的集合
//    val result = for(i <- 1 to 9;j <- 1 to i; val str = if(i==j) "\r\n" else " " ) yield {
//      i + "*" + j + "=" + i * j +str
//    }
//    println(result)
    
    /**try..catch..finnaly*/
//    val result = try{
//      val num = 1/0;
//      "没有异常"
//    } catch {
//      case e:NullPointerException =>"哈哈 这是一个空指针异常。。";
//      case e:IOException => "呵呵 这是一个IO异常。。";
//      case e:RuntimeException => "嘻嘻 这是一个运行时异常。。";
//      case e:Throwable => "拉拉 出问题了。。";
//    } finally {
//     "哼哼 finally最终执行。。。。"
//    }
//    println(result)
    
    /**match*/
//    val str = "c"
//    val result = str match {
//      case "a" => "哈哈 a"
//      case "b" => "哈哈 b"
//      case "c" => "哈哈 c"
//      case "d" => "哈哈 d"
//      case _=> "都不是"
//    }
//    println(result)
//  }
  
  /**continue 和 break*/
  //----案例将0到100的偶数进行累加 如果累加的值超过了50 就结束循环
//  var x = 0;
//  var sum = 0;
//  var flag = true;
//  while(x<100 && flag){
//    if(x%2==0){
//      sum += x;
//      if(sum>50){
//        flag = false;
//      }
//    }else{
//    }
//  }

//    def sumx(x:Int,sum:Int):Int={
//      if(x<100 && sum<50){
//        if(x%2==0){
//           sumx(x+1,sum+x)
//         }
//        else {
//          sumx(x+1,sum)
//        }
//      }else{
//        return sum
//      }
//    }
  
//    val result = sumx(0,0);
//    println(result)
    
    /**作用范围问题*/
    val name = "zhang";
    if(true){
    	val name = "li";
    }
    
  }
}
