package com.leaf.scala.demo


/**
 * 函数用法 - 成员函数
 * 将函数用作类或单例对象的成员
 */
class Person_07{
  val name = "张三"
  def say(){
    println(name + "在哈哈哈哈哈的说。。。")
  }
  def eat(){
    println(name + "在么么么么么的吃。。。")
  }
}

object Demo07 {
  def main(args: Array[String]): Unit = {
    val p1 = new Person_07();
    println(p1.name)
    p1.eat();
    p1.say();
  }
}