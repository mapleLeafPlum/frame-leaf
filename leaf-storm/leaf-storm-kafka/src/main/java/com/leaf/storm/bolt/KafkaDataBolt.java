package com.leaf.storm.bolt;

import com.leaf.storm.entity.StormInfo;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class KafkaDataBolt extends BaseRichBolt {
    private OutputCollector outputCollector;
    private FileWriter fileWriter;
    private TopologyContext topologyContext;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector=outputCollector;
        this.topologyContext=topologyContext;
        try {
            File file=new File("/opt/test/kafkaDataBolt.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter=new FileWriter(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            StringBuffer sb=new StringBuffer();
            sb.append("files:").append(tuple.getFields().toString());
            sb.append(",values:").append(tuple.getValues().toString());
            sb.append("\r\n");
            sb.append("class:CountBolt");
            sb.append(",tuple:").append(tuple.getStringByField("would"));
            sb.append(",sourceStreamId:").append(tuple.getSourceStreamId());
            sb.append(",sourceComponent:").append(tuple.getSourceComponent());
            sb.append(",sourceTask:").append(tuple.getSourceTask());
            sb.append("\r\n");
            sb.append(getInfo(this.topologyContext)).append("\r\n");
            fileWriter.write(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        //outputCollector.emit("counts",new Values(tuple.getStringByField("would")));
        outputCollector.emit(new Values(tuple.getValues()));
    }

    @Override
    public void cleanup() {
        System.out.println("cleanup()");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //outputFieldsDeclarer.declareStream("counts",new Fields("map"));
        outputFieldsDeclarer.declare(new Fields("topic", "partition", "offset", "key", "value"));
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
