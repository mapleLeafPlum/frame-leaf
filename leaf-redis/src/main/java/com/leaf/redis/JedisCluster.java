package com.leaf.redis;


import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

public class JedisCluster {
    String hosts="192.168.1.236:6381,192.168.1.236:6382,192.168.1.236:6383,192.168.1.236:6384,192.168.1.236:6385,192.168.1.236:6386";
    private redis.clients.jedis.JedisCluster cluster;

    public JedisCluster(){
        Set<HostAndPort> hostAndPortSet=new HashSet<HostAndPort>();
        for (String item:hosts.split(",")){
            String host=item.split(":")[0];
            int port=Integer.parseInt(item.split(":")[1]);
            HostAndPort h=new HostAndPort(host,port);
            hostAndPortSet.add(h);
        }
        cluster=new redis.clients.jedis.JedisCluster(hostAndPortSet);
    }

    public redis.clients.jedis.JedisCluster getCluster(){
        return cluster;
    }

    public static void main(String[] args) {
        JedisCluster client=new JedisCluster();
        redis.clients.jedis.JedisCluster cluster=client.getCluster();
        String result=  cluster.set("fuckjedis","fuckjedis");
        System.out.println(result);
        result=cluster.get("fuckjedis");
        result=cluster.get("testredis");
        System.out.println(result);
    }


}
