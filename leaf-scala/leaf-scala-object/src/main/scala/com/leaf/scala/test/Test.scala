package com.leaf.scala.test

object Test {
  def main(args: Array[String]): Unit = {

    println(Utils.home);
    println(Utils.getAddress);

    var config=new Config(100,"home","address")

    var utils=new Utils(config);

    println(utils.getAddress);

  }

}
