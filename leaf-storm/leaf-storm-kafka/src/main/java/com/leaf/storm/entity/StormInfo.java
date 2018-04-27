package com.leaf.storm.entity;

public class StormInfo {

    private Integer taskId;
    private String streamId;
    private String component;
    private String source;
    private String messageId;
    private Integer worker;
    private String stormId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getWorker() {
        return worker;
    }

    public void setWorker(Integer worker) {
        this.worker = worker;
    }

    public String getStormId() {
        return stormId;
    }

    public void setStormId(String stormId) {
        this.stormId = stormId;
    }

    @Override
    public String toString() {
        return "{" +
                "taskId=" + taskId +
                ",streamId='" + streamId + '\'' +
                ",component='" + component + '\'' +
                ",source='" + source + '\'' +
                ",messageId='" + messageId + '\'' +
                ",worker=" + worker +
                ",stormId='" + stormId + '\'' +
                '}';
    }
}
