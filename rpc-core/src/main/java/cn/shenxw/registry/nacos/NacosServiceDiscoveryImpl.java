package cn.shenxw.registry.nacos;

import cn.shenxw.dto.RpcRequest;
import cn.shenxw.enums.RpcErrorMessageEnum;
import cn.shenxw.exception.RpcException;
import cn.shenxw.registry.ServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * nacos服务注册实现
 *
 * @author shenxiangwei
 * @date 2021/6/9 10:57 上午
 */
@Slf4j
public class NacosServiceDiscoveryImpl implements ServiceDiscovery {

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
    public InetSocketAddress lookupService(RpcRequest rpcRequest) {
        try {
            //利用列表获取某个服务的所有提供者
            List<Instance> instances = namingService.getAllInstances(rpcRequest.getInterfaceName());
            Instance instance = instances.get(0);
            return new InetSocketAddress(instance.getIp(), instance.getPort());
        }catch (NacosException e) {
            log.error("获取服务时有错误发生" + e);
        }
        return null;
    }
}
