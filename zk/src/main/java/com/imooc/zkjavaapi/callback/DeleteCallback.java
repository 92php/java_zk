package com.imooc.zkjavaapi.callback;


import org.apache.zookeeper.AsyncCallback;

/**
 * 删除后运行的方法
 */
public class DeleteCallback implements AsyncCallback.VoidCallback {
    public void processResult(int i, String s, Object o) {
        System.out.println("删除节点" + s);
        System.out.println((String)o);
    }
}
