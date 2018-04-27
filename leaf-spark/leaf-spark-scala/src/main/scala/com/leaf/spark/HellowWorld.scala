package com.leaf.spark

import org.apache.spark.{SparkConf, SparkContext}

object HellowWorld {
  def main(args: Array[String]): Unit = {

    val inputPath =args(0);
    val outPath=args(1);
    // Should be some file on your system
    val conf = new SparkConf().setAppName("hellow")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(inputPath)
    val result=logData.flatMap(_.split("---")).map((_,1)).reduceByKey(_+_);
    var seqs=Seq("a","b","c");
    var seqResult=sc.makeRDD(seqs,10);


    result.saveAsTextFile(outPath);

    sc.stop()
  }
}
