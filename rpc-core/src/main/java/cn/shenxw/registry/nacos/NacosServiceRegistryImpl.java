package cn.shenxw.registry.nacos;

import cn.shenxw.enums.RpcErrorMessageEnum;
import cn.shenxw.exception.RpcException;
import cn.shenxw.registry.ServiceRegistry;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author shenxiangwei
 * @date 2021/6/9 11:15 上午
 */
@Slf4j
public class NacosServiceRegistryImpl implements ServiceRegistry {

    private static final String SERVER_ADDR = "127.0.0.1:8848";

    private static final NamingService namingService;

    static {
        try {
            //连接Nacos创建命名服务
            namingService = NamingFactory.createNamingService(SERVER_ADDR);
        }catch (NacosException e){
            log.error("连接Nacos时有错误发生：",e);
            throw new RpcException(RpcErrorMessageEnum.CLIENT_CONNECT_SERVER_FAILURE);
        }
    }

    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        try {
            //向Nacos注册服务
            namingService.registerInstance(rpcServiceName, inetSocketAddress.getHostName(), inetSocketAddress.getPort());
        }catch (NacosException e) {
            log.error("注册服务时有错误发生" + e);
            throw new RpcException(RpcErrorMessageEnum.REGISTRY_SERVICE_FAIL);
        }
    }
}
