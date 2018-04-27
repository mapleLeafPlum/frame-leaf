package com.leaf.scala.test

class Config(age:Int,name:String,address:String) {
/*  private var age:Int=_;
  private var name:String=_;
  private var address:String=_;*/

/*  def setName(name:String):Unit={
    name=name;
  }

  def setAge(age:Int):Unit={
    this.age=age;
  }

  def setAddress(address:String):Unit={
    this.address=address;
  }*/

  /*def this()=this(10,"","")
  def this(age:Int,name:String)=this(age,name,"")
  def this(age:Int)=this(age,"")
*/
  def this()=this(10,"","")
  def this(age:Int,name:String)=this(age,name,"")
  def this(age:Int)=this(age,"")



  def getName=this.name;
  def getAge=this.age;
  def getAddress=this.address;

}
