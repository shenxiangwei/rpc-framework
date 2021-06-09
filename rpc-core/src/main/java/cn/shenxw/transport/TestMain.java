package cn.shenxw.transport;

import cn.shenxw.transport.socket.RpcSocketServer;

/**
 * create by shenxiangwei on 2021/5/15 下午 11:09
 */
public class TestMain {
    public static void main(String[] args) {
        RpcSocketServer server = new RpcSocketServer();
        server.start();
    }
}
