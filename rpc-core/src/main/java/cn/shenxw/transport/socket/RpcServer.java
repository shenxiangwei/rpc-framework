package cn.shenxw.transport.socket;

import cn.shenxw.config.RpcServiceConfig;
import cn.shenxw.extension.ExtensionLoader;
import cn.shenxw.registry.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * create by shenxiangwei on 2021/5/16 上午 12:40
 */
@Component
public class RpcServer {
    private ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    public RpcServer() {
        // 线程池参数
        int corePoolSize = 10;
        int maximumPoolSizeSize = 100;
        long keepAliveTime = 1;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        this.threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSizeSize, keepAliveTime, TimeUnit.MINUTES, workQueue, threadFactory);
    }

    /**
     * 服务端主动注册服务
     */
    public void register(Object service, int port, RpcServiceConfig rpcServiceConfig) {
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("server starts...");

            ServiceRegistry nacos = ExtensionLoader.getExtensionLoader(ServiceRegistry.class).getExtension("nacos");
            String host = InetAddress.getLocalHost().getHostAddress();
            //向nacos注册服务
            nacos.registerService(rpcServiceConfig.getRpcServiceName(),new InetSocketAddress(host,9999));
            logger.info("register {} to nacos",rpcServiceConfig.getRpcServiceName());

            Socket socket;
            while ((socket = server.accept()) != null) {
                logger.info("client connected");
                threadPool.execute(new WorkerThread(socket, service));
            }


        } catch (IOException e) {
            logger.error("occur IOException:", e);
        }
    }


}