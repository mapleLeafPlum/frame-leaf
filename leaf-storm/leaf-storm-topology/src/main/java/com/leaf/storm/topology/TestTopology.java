package com.leaf.storm.topology;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.testing.TestGlobalCount;
import org.apache.storm.testing.TestWordCounter;
import org.apache.storm.testing.TestWordSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;


public class TestTopology {

    public static void main(String[] args) {
        try {
            TopologyBuilder builder = new TopologyBuilder();

            builder.setSpout("1", new TestWordSpout(true), 5);
            builder.setSpout("2", new TestWordSpout(true), 3);
            builder.setBolt("3", new TestWordCounter(), 3)
                    .fieldsGrouping("1", new Fields("word"))
                    .fieldsGrouping("2", new Fields("word"));
            builder.setBolt("4", new TestGlobalCount())
                    .globalGrouping("1");

            Config conf = new Config();
            conf.setNumWorkers(4);
            conf.setMaxSpoutPending(5000);

            StormSubmitter.submitTopology("mytopology", conf, builder.createTopology());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
