package com.imooc.zkjavaapi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkGetNode implements Watcher {

    public static final String SERVER_PATH = "127.0.0.1:2181";
    public static final Integer TIMEOUT = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        /**
         * 客户端和服务端他们是异步连接的，连接成功后，客户端能收到消息（watcher通知）
         */
        ZooKeeper zk = new ZooKeeper(SERVER_PATH, TIMEOUT, new ZkGetNode());
        System.out.println("客户端开始连接zk服务器了");
        System.out.println(zk.getState());
        Thread.sleep(2000);
        System.out.println(zk.getState());


/*        Stat exists = zk.exists("/imooc", false);
        if(exists != null){
            System.out.println("节点版本为："+exists.getVersion());
        }else{
            System.out.println("节点不存在");
        }*/

        zk.getData("/imooc",true,null);
        countDownLatch.await();
    }


    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
            System.out.println("数据被改变");
            countDownLatch.countDown();
        }

        System.out.println("收到了通知" + watchedEvent);
    }
}
