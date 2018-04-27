package com.leaf.spark.streaming


import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingKafka {
  def main(args: Array[String]): Unit = {

    //创建streamingContext
    var conf=new SparkConf()
      .setAppName("sparkKafka");
    var ssc=new StreamingContext(conf,Seconds(2));
    //创建topic
    //var topic=Map{"test" -> 1}
    var topic=Array("spark");
    //指定zookeeper
    //创建消费者组
    var group="sparkConsumer"
    //消费者配置
    val kafkaParam = Map(
      "bootstrap.servers" -> "hadoop200:9092,hadoop201:9092,hadoop202:9092",//用于初始化链接到集群的地址
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      //用于标识这个消费者属于哪个消费团体
      "group.id" -> group,
      //如果没有初始化偏移量或者当前的偏移量不存在任何服务器上，可以使用这个配置属性
      //可以使用这个配置，latest自动重置偏移量为最新的偏移量
      "auto.offset.reset" -> "latest",
      //如果是true，则这个消费者的偏移量会在后台自动提交
      "enable.auto.commit" -> (true: java.lang.Boolean)
    );
    //for(i <- 0 to 5){
      //创建DStream，返回接收到的输入数据
      var stream=KafkaUtils.createDirectStream[String,String](ssc, LocationStrategies.PreferConsistent,ConsumerStrategies.Subscribe[String,String](topic,kafkaParam))
      //每一个stream都是一个ConsumerRecord
      println(Thread.currentThread().getName);
      stream.map(s =>(s.key(),s.value())).print();
    //}
    ssc.start();
    ssc.awaitTermination();
  }

}
