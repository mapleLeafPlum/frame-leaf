package com.leaf.storm.topology;

import com.leaf.storm.bolt.CountBolt;
import com.leaf.storm.bolt.CountMapBolt;
import com.leaf.storm.bolt.KafkaDataBolt;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;

import java.util.Properties;


public class KafkaTopology {

    public static void main(String[] args) {
        try {
            TopologyBuilder builder = new TopologyBuilder();
            String bootstraps="hadoop200:9092,hadoop201:9092,hadoop202:9092";
            String toipic="leaf";
            Properties props = new Properties();
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "stormkafka");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

            KafkaSpoutConfig.Builder<String,String> build=KafkaSpoutConfig.builder(bootstraps,toipic);
            build.setProp(props);
            KafkaSpoutConfig config=new KafkaSpoutConfig(build);

            builder.setSpout("kafkaspout",  new KafkaSpout(config), 2);
            builder.setBolt("kafkabolt", new KafkaDataBolt(), 2).shuffleGrouping("kafkaspout");
            builder.setBolt("countmapbolt", new CountMapBolt(),2).shuffleGrouping("kafkabolt");

            Config conf = new Config();
            conf.setNumWorkers(2);
           // conf.setMaxSpoutPending(5000);
            StormSubmitter.submitTopology("kafkaTopology", conf, builder.createTopology());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
