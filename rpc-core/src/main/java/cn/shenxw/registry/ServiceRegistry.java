package cn.shenxw.registry;

import cn.shenxw.extension.SPI;

import java.net.InetSocketAddress;

/**
 * 服务注册接口
 *
 * @author shenxiangwei
 * @date 2021/6/9 10:32 上午
 */
@SPI
public interface ServiceRegistry {
    /**
     * 向注册中心注册服务
     * @param rpcServiceName
     * @param inetSocketAddress
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);

}
