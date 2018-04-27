package com.leaf.scala.thread

import java.net.{InetAddress, ServerSocket, Socket}
import java.util.concurrent.{ExecutorService, Executors}

class NetworkService(port: Int, poolSize: Int) extends Runnable {
  val serverSocket = new ServerSocket(port,1000)
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)

  def run() {
    while (true) {
      try{
        println("init.....")
        // This will block until a connection comes in.
        val socket = serverSocket.accept()
        pool.execute(new Handler(socket))
        println("commint......")
      } catch{
        case ex:Exception=>print(ex.getMessage)
      }finally {
        println("closing......")
          pool.shutdown()
      }
    }
  }
}

class Handler(socket: Socket) extends Runnable {
  def message = (Thread.currentThread.getName()).getBytes

  def run() {
    println("coneetion.......")
    var input=socket.getInputStream();
    var byte=Byte;

    var output=socket.getOutputStream;
    output.write(message)
    if (input!=null)  input.close();
    if (output!=null)  output.close();
  }
}

