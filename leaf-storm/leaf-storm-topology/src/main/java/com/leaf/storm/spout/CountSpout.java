package com.leaf.storm.spout;


import com.leaf.storm.entity.StormInfo;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Random;

public class CountSpout extends BaseRichSpout {
    private SpoutOutputCollector spoutOutputCollector;
    private FileWriter fileWriter;
    private TopologyContext topologyContext;
    private String[] words={"hadoop","hbase","solr","elasticsearch","hive","spark","storm","kafka","netty"};
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector=spoutOutputCollector;
        this.topologyContext=topologyContext;
        try {
            File file=new File("/opt/test/countspout.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter=new FileWriter(file);
        }catch (Exception e){
           // e.printStackTrace();
        }
    }

    @Override
    public void nextTuple() {
        Random random=new Random();
        int index=random.nextInt(words.length);
        String word=words[index];
        try {
            fileWriter.write("\r\n"+word+"---"+getInfo(this.topologyContext));
        }catch ( Exception e){

        }
        //spoutOutputCollector.emit("count",new Values(word));
        spoutOutputCollector.emit(new Values(word));
        ack(word);
        Utils.sleep(1000);
    }

    @Override
    public void activate() {
        System.out.println("deactivate()");
    }

    @Override
    public void deactivate() {
        System.out.println("deactivate()");
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("ack()");
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("fail()");
    }

    @Override
    public void close() {
        System.out.println("close()");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //outputFieldsDeclarer.declareStream("count",new Fields("would"));
        outputFieldsDeclarer.declare(new Fields("would"));
    }

    private String getInfo(TopologyContext topology){
        StormInfo info=new StormInfo();
        info.setStormId(topology.getStormId());
        info.setTaskId(topology.getThisTaskId());
        info.setComponent(topology.getThisComponentId());
        info.setStreamId(topology.getThisStreams().toString());
        info.setWorker(topology.getThisWorkerPort());
       return info.toString();
    }
}

