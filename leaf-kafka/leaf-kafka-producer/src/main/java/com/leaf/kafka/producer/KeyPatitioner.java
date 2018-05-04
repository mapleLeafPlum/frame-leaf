package com.leaf.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class KeyPatitioner implements Partitioner {


    public int partition(String topic, Object key, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        return Integer.parseInt(key.toString().split("_")[1])%numPartitions;
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
