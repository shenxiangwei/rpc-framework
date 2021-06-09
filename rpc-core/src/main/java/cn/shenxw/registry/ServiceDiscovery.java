package cn.shenxw.registry;

import cn.shenxw.dto.RpcRequest;
import cn.shenxw.extension.SPI;

import java.net.InetSocketAddress;

/**
 * 客户端发现服务
 *
 * @author shenxiangwei
 * @date 2021/6/9 10:33 上午
 */
@SPI
public interface ServiceDiscovery {
    /**
     * lookup service by rpcServiceName
     *
     * @param rpcRequest rpc service pojo
     * @return service address
     */
    InetSocketAddress lookupService(RpcRequest rpcRequest);
}
