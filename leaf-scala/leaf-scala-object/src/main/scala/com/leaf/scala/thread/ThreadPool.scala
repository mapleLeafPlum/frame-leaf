package com.leaf.scala.thread

import java.util.concurrent.{ExecutorService, Executors}

object ThreadPool {
  def main(args: Array[String]): Unit = {
    val pool: ExecutorService = Executors.newFixedThreadPool(5)
    for (i <- 0 until 10){
      pool.execute(new PrintThrea)
    }
    pool.shutdown();
  }
}

class PrintThrea extends Runnable{
  override def run() = {
    Thread.sleep(200)
    println("thread:"+Thread.currentThread().getName)
  }
}
