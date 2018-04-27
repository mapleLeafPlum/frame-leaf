package com.leaf.spark

import org.apache.spark.{SparkConf, SparkContext}

import Array.{ofDim, _}

object TestSpark {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf();
    conf.setAppName("test");
    val sparkContent=new SparkContext(conf);
    var siz=args(1).toInt;
    if(siz==None){
      siz=100000;
    }
    val source=getArray(siz);
    var listRdd=sparkContent.parallelize(source,2)
    var resultRdd=listRdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_);

    resultRdd.saveAsTextFile(args(0))

    sparkContent.stop()

  }

  def getSource():Array[Array[String]]={
    val base=List("spark","hadoop","storm","kafka","flink","hbase","hive","netty","cdh","hdp","linux");
    var doubleArray= ofDim[String](100,base.length);
    for(j <- 0 until 100){
      for(i<- 0 until base.length  ){
        var index=(math.random*base.length).toInt;
        doubleArray(j)(i)=base(index);
      }
    }
    doubleArray
  }

  def getArray(arrayLength:Int):Array[String]={
    val base=List("spark","hadoop","storm","kafka","flink","hbase","hive","netty","cdh","hdp","linux");
    var array=new Array[String](arrayLength);
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
