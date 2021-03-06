package com.leaf.spark.socket

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingSocket {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(args(1).toInt))

    // Create a DStream that will connect to hostname:port, like localhost:9999
    val lines = ssc.socketTextStream(args(0), 9999)

    lines.print()
    // Split each line into words
    //val words = lines.flatMap(_.split(" "))

    import org.apache.spark.streaming.StreamingContext._ // not necessary since Spark 1.3
    // Count each word in each batch
    //val pairs = words.map(word => (word, 1))
    //val wordCounts = pairs.reduceByKey(_ + _)

    // Print the first ten elements of each RDD generated in this DStream to the console
    //wordCounts.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate

  }

}
