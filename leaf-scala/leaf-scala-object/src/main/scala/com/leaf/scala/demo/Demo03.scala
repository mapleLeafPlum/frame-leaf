package com.leaf.scala.demo

/**
 * 操作符即方法 方法即操作符
 * scala中并没有真正意义上的操作符 所有的操作符本质上都是方法的调用
 * 而普通方法也可以像使用操作符那样去使用
 * 而这跟符合 面向对象的理念
 * 
 * 操作符分三种
 * 	后缀 操作符
 *  前缀操作符
 *  	前缀操作符比较特殊 能作为前缀操作符的只有 +-!~ 并且在翻译成对应方法时 要翻译成 unary_操作符 的形式
 *  中缀操作符
 * 
 * 操作符的优先级 即 方法的优先级
 * 	scala中操作符（方法）是有优先级的概念 
 *  优先级就看操作符（方法）的首字符 一下列表 越向上 优先级越高
 *	优先级相同时 从上到下 从左到右依次执行
 *	可以用括号改变执行顺序
 *	这种做法 即保证了四则运算的正确 也保证了方法按照正确的方式执行
 *	注意：以 : 结尾的方法 是有操作数 调用方法 传入左操作数 这个特性 后续学习集合时非常有用 
  		* / %
			+-
			:
			=!
			<>
			&
			^
			|
			所有字母
			所有赋值操作符
 * 
 */
object Demo03 {
  def main(args: Array[String]): Unit = {
	  val num1 = 2 + 3;
		val num2 = 2.+(3);
    println(num1)
    
    val str1 = "abcdef";
    val str2 = str1.concat("ghi");
    val str3 = str1 concat "ghi";
    println(str3,str2)
    val str4 = str1.substring(2, 5);
    val str5 = str1 substring (2,5)
    Char
    str1.sum
    println(str5,str4)
    val str6 = str1.toUpperCase();
    val str7 = str1 toUpperCase;
    println(str7)
    println(str1.sum)
    val num3 = -3;
    val num3x = 3.unary_-;
    val num4 = +4;
    val num4x = 4.unary_+;
    val num5 = ~9;
    val num5x = 9.unary_~;
    val flag1 = !true;
    val flag1x = true.unary_!;
    
    val num6 = 2 + 3 * 4;
    println(num5)
  }
}