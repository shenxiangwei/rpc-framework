package cn.shenxw.provider;

import cn.shenxw.api.HelloService;
import cn.shenxw.transport.socket.RpcServer;

/**
 * create by shenxiangwei on 2021/5/16 上午 12:43
 */
public class RpcFrameworkSimpleMain {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
//        rpcServer.register(helloService, 9999);
    }






}
