package com.leaf.elasticsearch.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ConnectWatcher implements Watcher {

    protected ZooKeeper zooKeeper;
    private String hosts;
    private Integer session_timeout;
    private Integer connect_timeout=3000;

    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void connect(String hosts, int session_timeout) throws Exception {
        this.hosts = hosts;
        this.session_timeout = session_timeout;

        if (zooKeeper == null || !zooKeeper.getState().isAlive()) {
            zooKeeper = new ZooKeeper(hosts, session_timeout, this);
            connectedSignal.await();
        }
    }

    public void reconnect() throws Exception {
        if (zooKeeper == null) {
            return;
        }
        if (!zooKeeper.getState().isAlive()) {
            zooKeeper = new ZooKeeper(hosts, session_timeout, this);
        }
    }

    public void close() throws Exception {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

    public ZooKeeper.States getZookeeperStatus() throws Exception {
        if (zooKeeper != null) {
            return zooKeeper.getState();
        }
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.toString());
        System.out.println("process");
        if (watchedEvent.getState().equals(Event.KeeperState.Expired)) {
            System.out.println("-----------------------Expired");
            if (zooKeeper != null) {
                System.out.println(zooKeeper.getState().isAlive());
                System.out.println(zooKeeper.getState().isConnected());
                System.out.println(zooKeeper.getState().toString());
            } else {
                System.out.println("zookeeper is null");
            }
        } else if (watchedEvent.getState().equals(Event.KeeperState.AuthFailed)) {
            System.out.println("-----------------------AuthFailed");
        } else if (watchedEvent.getState().equals(Event.KeeperState.Disconnected)) {
            System.out.println("-----------------------Disconnected");
            if (zooKeeper != null) {
                System.out.println(zooKeeper.getState().isAlive());
                System.out.println(zooKeeper.getState().isConnected());
                System.out.println(zooKeeper.getState().toString());
            } else {
                System.out.println("zookeeper is null");
            }
        } else if (watchedEvent.getState().equals(Event.KeeperState.SyncConnected)) {
            System.out.println("-----------------------SyncConnected");
            connectedSignal.countDown();
            if (zooKeeper != null) {
                System.out.println(zooKeeper.getState().isAlive());
                System.out.println(zooKeeper.getState().isConnected());
                System.out.println(zooKeeper.getState().toString());
            } else {
                System.out.println("zookeeper is null");
            }
        } else if (watchedEvent.getState().equals(Event.KeeperState.ConnectedReadOnly)) {
            System.out.println("-----------------------ConnectedReadOnly");
            if (zooKeeper != null) {
                System.out.println(zooKeeper.getState().isAlive());
                System.out.println(zooKeeper.getState().isConnected());
                System.out.println(zooKeeper.getState().toString());
            } else {
                System.out.println("zookeeper is null");
            }
        } else if (watchedEvent.getState().equals(Event.KeeperState.SaslAuthenticated)) {
            System.out.println("-----------------------SaslAuthenticated");
        } else if (watchedEvent.getState().equals(Event.KeeperState.NoSyncConnected)) {
            System.out.println("-----------------------NoSyncConnected");
        } else if (watchedEvent.getState().equals(Event.KeeperState.Unknown)) {
            System.out.println("-----------------------Unknown");
            if (zooKeeper != null) {
                System.out.println(zooKeeper.getState().isAlive());
                System.out.println(zooKeeper.getState().isConnected());
                System.out.println(zooKeeper.getState().toString());
            } else {
                System.out.println("zookeeper is null");
            }
        }
    }
}
