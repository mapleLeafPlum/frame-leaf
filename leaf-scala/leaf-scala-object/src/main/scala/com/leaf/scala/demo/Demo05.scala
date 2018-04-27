package com.leaf.scala.demo


/**
 * 函数 - 普通方式声明函数
 * scala的函数是为了实现特定功能将一段代码整合 得到的 代码的集合 可以在后续调用
 * 通过def修饰
 * 只有 private/protected/不写默认public 三种访问权限
 * 基本结构
 * 	[public/private/protected] def 函数名(参数列表):返回值声明 = {函数体}
 */
object Demo05 {
  def main(args: Array[String]): Unit = {
    val result = sumx1(2,3)
    println(result)
  }
  
  //--完整形式
  def sumx1(num1:Int,num2:Int):Int={
    return num1 + num2;
  }
  //--省略return 则最后一个表达式作为返回值
  def sumx2(num1:Int,num2:Int):Int={
		  num1 + num2;
  }
  //--省略返回值类型声明 大部分情况下都可以省略 scala自动推断类型
  def sumx3(num1:Int,num2:Int)={
		  num1 + num2;
  }
  //--省略大括号 在函数体只有一行的情况
  def sumx4(num1:Int,num2:Int)=num1 + num2;
  
  //--省略小括号 当 函数参数列表为空的情况
  def sumx5() = {
    100
  }  
  //--当省略到这一步骤时 发现 这个函数 和一个常量没什么却别 所以 常量可以理解为 一个 没有参数 具有固定返回值的函数 一个没有参数 没有使用外部变量 没有产生额外一项的函数 也可以理解为一个常量
  def sumx6 = 100;
  val sumx7 = 100;
  
  //--省略等号 如果函数没有返回值 则返回值类型的声明 和 等号可以一起省略
  def sumAndPrint1(num1:Int,num2:Int):Unit={
    val result = num1 + num2;
    println(result)
  }
  def sumAndPrint2(num1:Int,num2:Int){
		  val result = num1 + num2;
		  println(result)
  }
  
}