package com.leaf.scala.demo


/**
 * 常量和变量
 * 	var声明变量
 * 	val声明常量
 * 	后面跟上 变量或常量 名 
 * 	再跟上冒号和类型声明
 * 	而类型声明可以省略 scala会自动推断类型
 */
object Demo02 {
  def main(args: Array[String]): Unit = {
    //声明变量
    var name1:String = "zhang"
    var name2 = "zhang"
    name2 = "li"
    
    //声明常量
    val name3:String = "wang"
    val name4 = "li"
    //name4 = "zhao"
    
    //特殊用法
    val str1 = "abc\r\nd\t\"ef"
    val str2 = """abc
      d  "ef"""
    print(str2)
  }
}