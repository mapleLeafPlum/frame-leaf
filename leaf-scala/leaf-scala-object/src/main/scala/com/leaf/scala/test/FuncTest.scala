package com.leaf.scala.test

object FuncTest {


  /****************偏应用函数******************/
 /*
  部分应用（Partial application）
  偏应用函数
  你可以使用下划线“_”部分应用一个函数，结果将得到另一个函数。Scala使用下划线表示不同上下文中的不同事物，
  你通常可以把它看作是一个没有命名的神奇通配符。在{ _ + 2 }的上下文中，它代表一个匿名参数。你可以这样使用它：
  */
  def partialApplication()={
   var name="test"
   var partial=getMessage(name,_:Int)
   partial(10)
  }


  def getMessage(name:String,age:Int)={
    println(name,age)
  }

  /****************偏应用函数******************/

  /****************匿名函数******************/
  def hiddeFun(): Unit ={
    var result=hidden(1,2)
    println(result)
  }

  var hidden=(n:Int,m:Int)=>m+n;

  def add2 = new Function1[Int,Int]{
    def apply(x:Int):Int = x+1;
  }

  /****************匿名函数******************/


  /****************柯里化函数******************/

  /*
  柯里化函数
  有时会有这样的需求：允许别人一会在你的函数上应用一些参数，然后又应用另外的一些参数。
  例如一个乘法函数，在一个场景需要选择乘数，而另一个场景需要选择被乘数。*/

  def curryingFunc(): Unit ={

    println(currying(2)(6))
    //可以先用一个参数 用_代替令一个参数 直接调用
    var two =currying(3)_
    println(two(10))
    //把普通函数柯里化
    var ts=(commonF _).curried
    println(ts(1)(2))
    //
    println(commonF(3,3))
  }

  def currying(n:Int)(m:Int)={
    m+n
  }

  def commonF(n:Int,m:Int):Int=n+m


  /****************柯里化函数******************/


  /***************可变参数*******************/
  def changeParam(): Unit ={
    chage("温柔","大方")
  }

  def chage(arg:String*): Unit ={
    println(arg)
  }

  /***************可变参数*******************/

  /***************高阶函数*******************/

  def getHight(): Unit ={
    println(hightFunc(layout,"电视发射点发"));
    genericity(100,12)

    val list = List("aaa","bbb","ccc");
    list.foreach(fxx(_));
    println("1-----------------------------")
    list.foreach(println)
    println("2-----------------------------")
    list.foreach(x=>println(x))
    println("3-----------------------------")
    list.foreach(fuck(_));

    fxx("sdfds")
    println("4-----------------------------")
     println(f(2,3))
    println(fc(5,3))
  }

  def hightFunc(f:String=>String,v:String) ={
    f(v);
  }

  def layout[A](x: A)= "[" + x.getClass() + "]"

  val f = (x:Int,y:Int)=>{x+y}
  val fc = (_:Int) + (_:Int)

  //泛型参数类型
  def genericity[B,C](arg:B,a:C)={
    println(arg.getClass)
    println(a.getClass)
    if(arg.isInstanceOf[String]){
      println(arg.getClass)
    }
  }

  def fxx:(String)=>Unit={
    x => println(x)
  }

  def fuck:(String)=>Unit={
     println(_)
  }

  /***************高阶函数*******************/




  def main(args: Array[String]): Unit = {
    //partialApplication
    //hiddeFun
    //curryingFunc

    //changeParam
    getHight

    var arrs=Array.tabulate(10)(_+10)
    println(arrs)
    for (i <- arrs){
      println(i)
    }

  }

}
