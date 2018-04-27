package com.leaf.scala.demo


/**
 * 函数 - 函数直接量形式声明函数
 * 格式	：
 *	(参数列表) => {函数体}
 * 注意 函数直接量 通常 不是用来直接调用的 而是用在 函数值 和 高阶函数的场景下
 */
object Demo06 {
  def main(args: Array[String]): Unit = {
//   //--基本函数直接量写法
//  (num1:Int,num2:Int)=>{num1+num2}
//  
//  //--如果函数体只有一行 则包裹函数体的大括号可以省略
//  (num1:Int,num2:Int)=>num1+num2
//  
//  //--如果函数直接量的的参数的类型 可以自动推断得到 则函数参数列表中类型可以省写
//  //(num1,num2)=>num1+num2//此处会报错 因为没有上下文可以推断参数类型 在后续学习高阶函数时 可以见到这种省略方式
//  
//  //--如果函数参数列表中参数只有一个 则在不产生歧义的情况下 包裹参数列表的小括号可以省写
//  (str:String)=>{print(str toUpperCase )}
//  //str:String=>print(str toUpperCase )//此处会报错 因为可能产生歧义 后续会见到这种用法

    //--函数直接量 通常 不是用来直接调用的 而是用在 函数值 和 高阶函数的场景下
    //--虽然目前函数值的用法 和 高阶函数的用法还没有学过 但是简单写个例子作为参考：
    val mx = (num1:Int,num2:Int)=>{num1+num2}
    val result = mx(2,3)
    println(result)
  }
}