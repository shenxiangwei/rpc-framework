import cn.shenxw.api.Hello;
import cn.shenxw.api.HelloService;
import cn.shenxw.transport.socket.RpcClientProxy;

/**
 * create by shenxiangwei on 2021/5/16 上午 12:45
 */
public class RpcFrameworkSimpleMain {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 9999);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        String hello = helloService.hello(new Hello("111", "222"));
        System.out.println(hello);
    }
}
