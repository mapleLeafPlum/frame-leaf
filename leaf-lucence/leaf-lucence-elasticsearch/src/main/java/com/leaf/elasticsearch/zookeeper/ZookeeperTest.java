package com.leaf.elasticsearch.zookeeper;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;


public class ZookeeperTest extends ConnectWatcher{
    public static void main(String args[]) throws Exception{
        ZookeeperTest test=new ZookeeperTest();
        test.connect("no250",10000);



        test.getChilren(test.zooKeeper);
       // test.zooKeeper.delete("/zookeepertest/no256",-1);


        Stat exists=test.zooKeeper.exists("/zookeepertest/no256",false);
        System.out.println("exitst:"+exists);
        System.out.println("sleep start.............");
        Thread.sleep(1220);
        System.out.println("sleep end.............");
        String data="{\"ip\":\"no2675\"}";
        String result=test.zooKeeper.create("/zookeepertest/no256/no6",data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("------------------------------"+result);
        Stat stat=new Stat();
        byte[] datas= test.zooKeeper.getData("/zookeepertest/no256",false,stat);
        System.out.println(new String(datas,"utf-8"));

        test.close();
    }

    public void getChilren(ZooKeeper zooKeeper) throws  Exception{
        List<String> chilren= zooKeeper.getChildren("/zookeepertest/no256",false);
        Stat stat=new Stat();
        for (String item:chilren){
            System.out.print(item+":");
            byte[] datas= zooKeeper.getData("/zookeepertest/no256/"+item,false,stat);
            System.out.println(new String(datas,"utf-8"));
          /*  List<String> chilrens= zooKeeper.getChildren("/"+item,false);
            for (String t:chilren) {
                System.out.println(t);
            }*/
        }
    }


}
