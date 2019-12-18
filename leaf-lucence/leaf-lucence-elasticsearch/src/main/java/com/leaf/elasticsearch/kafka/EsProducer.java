package com.leaf.elasticsearch.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.calendar.CalendarUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Date: 2018/4/27 18:31
 * @Auther: nothing
 */
public class EsProducer {
    private final static Logger LOG = LoggerFactory.getLogger(EsProducer.class);
    private final KafkaConsumer<String, String> consumer;
    private ExecutorService executorService;
    private static int counter = 0;

    public EsProducer() {
        String root = System.getProperty("user.dir") + "/conf/";

        Properties props = new Properties();
        props.put("bootstrap.servers","job.content.reader.parameter.bootstrapServers");
        props.put("group.id", "job.content.reader.parameter.groupid");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("job.content.reader.parameter.topic"));
    }

    public void execute() {
        executorService = Executors.newFixedThreadPool(6);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            if (null != records) {
                executorService.submit(new KafkaConsumerThread(records, consumer));
            }
        }
    }

    public void shutdown() {
        try {
            if (consumer != null) {
                consumer.close();
            }
            if (executorService != null) {
                executorService.shutdown();
            }
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                LOG.error("Shutdown kafka consumer thread timeout.");
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    class KafkaConsumerThread implements Runnable {

        private ConsumerRecords<String, String> records;

        public KafkaConsumerThread(ConsumerRecords<String, String> records, KafkaConsumer<String, String> consumer) {
            this.records = records;
        }

        @Override
        public void run() {
            String index = "index";
            String type = "type";
            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, String> record : partitionRecords) {

                    List<Map<String, Object>> list = new ArrayList<>();
                    Map<String, Object> map = new HashMap<>();

                    if (counter < 10) {
                        LOG.info("Index : " + index);
                        counter++;
                    }

                   /* for (String key : json.keySet()) {
                        if ("_uid".equals(key)) {
                            map.put("uid", json.get(key));
                        } else {
                            map.put(key, json.get(key));
                        }
                        list.add(map);
                    }*/

                    EsUtils.write2Es(index, type, list);
                }
            }
        }

    }
}
