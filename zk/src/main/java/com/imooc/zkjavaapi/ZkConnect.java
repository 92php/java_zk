package com.imooc.zkjavaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 连接到zk服务端，打印连接状态
 */
public class ZkConnect implements Watcher {

    public static final String SERVER_PATH = "127.0.0.1:2181";
    public static final Integer TIMEOUT = 5000;

    public static void main(String[] args) throws IOException, InterruptedException {
        /**
         * 客户端和服务端他们是异步连接的，连接成功后，客户端能收到消息（watcher通知）
         */
        ZooKeeper zk = new ZooKeeper(SERVER_PATH, TIMEOUT, new ZkConnect());
        System.out.println("客户端开始连接zk服务器了");
        System.out.println(zk.getState());
        Thread.sleep(2000);
        System.out.println(zk.getState());
    }


    public void process(WatchedEvent watchedEvent) {
        System.out.println("收到了通知" + watchedEvent);
    }
}
