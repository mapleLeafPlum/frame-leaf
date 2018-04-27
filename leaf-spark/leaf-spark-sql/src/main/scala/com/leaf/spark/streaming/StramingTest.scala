package com.leaf.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object StramingTest {
  def main(args: Array[String]): Unit = {
    val sparkConf=new SparkConf().setAppName("streaming");

    val ssc=new StreamingContext(sparkConf,Seconds(1));

    val lines=ssc.socketTextStream(args(0),args(1).toInt);
    // Split each line into words
    val words = lines.flatMap(_.split(" "))

    import org.apache.spark.streaming.StreamingContext._ // not necessary since Spark 1.3
    // Count each word in each batch
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)

    // Print the first ten elements of each RDD generated in this DStream to the console
    wordCounts.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate
  }

}
