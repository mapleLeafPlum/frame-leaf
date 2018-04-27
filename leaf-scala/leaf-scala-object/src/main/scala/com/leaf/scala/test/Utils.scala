package com.leaf.scala.test

class Utils(conf:Config) {

  def this()=this(new Config());
  def this(name:String,age:Int)={
    this(new Config(age,name,""))
  }
  def this(name:String)=this(name,10)


  var name:String=_;
  var attribute:String=_;

  def getName="werewr"
  def getAge=1000;
  def getAddress=conf.getAddress

}

object Utils{
   def getAddress="深圳";
   var home:String="当保安"
}
