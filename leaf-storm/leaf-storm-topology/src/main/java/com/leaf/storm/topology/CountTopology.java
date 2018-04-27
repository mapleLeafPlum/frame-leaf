package com.leaf.storm.topology;

import com.leaf.storm.bolt.CountBolt;
import com.leaf.storm.bolt.CountMapBolt;
import com.leaf.storm.spout.CountSpout;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.grouping.ShuffleGrouping;
import org.apache.storm.testing.TestGlobalCount;
import org.apache.storm.testing.TestWordCounter;
import org.apache.storm.testing.TestWordSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;


public class CountTopology {

    public static void main(String[] args) {
        try {
            TopologyBuilder builder = new TopologyBuilder();

            builder.setSpout("countspout", new CountSpout(), 4);
            builder.setBolt("countbolt", new CountBolt(), 4).shuffleGrouping("countspout");
            builder.setBolt("countmapbolt", new CountMapBolt(),4).shuffleGrouping("countbolt");

            Config conf = new Config();
            conf.setNumWorkers(4);
           // conf.setMaxSpoutPending(5000);
            StormSubmitter.submitTopology("countTopology", conf, builder.createTopology());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
