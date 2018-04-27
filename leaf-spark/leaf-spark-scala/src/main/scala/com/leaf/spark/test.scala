package com.leaf.spark

object test {
  def main(args: Array[String]): Unit = {
    /* val lissts=List("spark","hadoop","storm","kafka","flink","hbase","hive","netty","cdh","hdp","linux");
    for(j <- 0 to 100){
      for(i<- 0 until lissts.length  ){
         var index=(math.random*lissts.length).toInt;
         print(lissts(index)+"---")
      }
      println()
    }*/
println(12323)
    var array=getArray
    for(i <- array){
      println(i)
    }

  }

  def getArray():Array[String]={
    val base=List("spark","hadoop","storm","kafka","flink","hbase","hive","netty","cdh","hdp","linux");
    var array=new Array[String](1000);
    var context:String="";
    for(j <- 0 until array.length){
      for(i<- 0 until base.length  ){
        var index=(math.random*base.length).toInt;
        context=context+(base(index)+" ");
      }
      array(j)=context;
      context="";
    }
    array
  }

}
