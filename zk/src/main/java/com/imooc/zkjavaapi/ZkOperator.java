package com.imooc.zkjavaapi;


import com.imooc.zkjavaapi.callback.DeleteCallback;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 演示对节点的操作
 */
public class ZkOperator implements Watcher {

    public static final String SERVER_PATH = "127.0.0.1:2181";
    public static final Integer TIMEOUT = 5000;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        /**
         * 客户端和服务端他们是异步连接的，连接成功后，客户端能收到消息（watcher通知）
         */
        ZooKeeper zk = new ZooKeeper(SERVER_PATH, TIMEOUT, new ZkOperator());
        System.out.println("客户端开始连接zk服务器了");
        System.out.println(zk.getState());
        Thread.sleep(2000);

        /**
         * path:创建的路径
         * data:存储的数据
         * acl:权限，开放
         * createModel:永久，临时，顺序
         */
        //zk.create("/imooc-create-node","imooc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //zk.setData("/imooc-create-node", "imooc-2".getBytes(), 0);
        //byte[] data = zk.getData("/imooc-create-node", null, null);
        //System.out.println(new String(data));
        //zk.delete("/imooc-create-node",0);


        String ctx = "删除成功";
        zk.delete("/imooc-create-node",3,new DeleteCallback(),ctx);

    }


    public void process(WatchedEvent watchedEvent) {

    }
}
