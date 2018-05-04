package com.leaf.redis.utils;

import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;

import java.util.ArrayList;
import java.util.List;

public class RedisClusterConfigCustomer extends RedisClusterConfiguration{
	
	 private String redisClusterNodes;
	 private Integer redisMaxRedirects;
	
	public RedisClusterConfigCustomer(String redisClusterNodes, Integer redisMaxRedirects) {
		this.redisClusterNodes=redisClusterNodes;
		this.redisMaxRedirects=redisMaxRedirects;
	}
	
	public RedisClusterConfigCustomer() {}
    
	public Integer getRedisMaxRedirects() {
		return redisMaxRedirects;
	}

	public void setRedisMaxRedirects(Integer redisMaxRedirects) {
		setMaxRedirects(redisMaxRedirects);
		this.redisMaxRedirects = redisMaxRedirects;
	}

	public String getRedisClusterNodes() {
	    return redisClusterNodes;
	}

	public void setRedisClusterNodes(String redisClusterNodes){
		
		String hosts[]=redisClusterNodes.split(",");
		List<RedisNode> nodes=new ArrayList<RedisNode>();
		for (String host : hosts) {
			String hostPort[]=host.split(":");
			RedisNode node=new RedisNode(hostPort[0],Integer.parseInt(hostPort[1]));
			nodes.add(node);
		}
		setClusterNodes(nodes);
		this.redisClusterNodes = redisClusterNodes;
	}
    
}
