package cn.shenxw.spring;

import cn.shenxw.annotation.RpcReference;
import cn.shenxw.annotation.RpcService;
import cn.shenxw.config.RpcServiceConfig;
import cn.shenxw.dto.RpcRequest;
import cn.shenxw.extension.ExtensionLoader;
import cn.shenxw.registry.ServiceDiscovery;
import cn.shenxw.registry.ServiceRegistry;
import cn.shenxw.transport.socket.RpcClientProxy;
import cn.shenxw.transport.socket.RpcServer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * call this method before creating the bean to see if the class is annotated
 *
 * @author shuang.kou
 * @createTime 2020年07月14日 16:42:00
 */
@Slf4j
@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private RpcServer rpcServer;

    public SpringBeanPostProcessor() {
        this.rpcServer = new RpcServer();
    }

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcService.class)) {
            log.info("[{}] is annotated with  [{}]", bean.getClass().getName(), RpcService.class.getCanonicalName());
            // get RpcService annotation
            RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);

            //构造rpc服务配置类
            RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                    .group(rpcService.group())
                    .version(rpcService.version())
                    .service(bean).build();

            rpcServer.register(bean,9999,rpcServiceConfig);

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            RpcReference rpcReference = declaredField.getAnnotation(RpcReference.class);
            if (rpcReference != null) {

                ServiceDiscovery nacos = ExtensionLoader.getExtensionLoader(ServiceDiscovery.class).getExtension("nacos");

                RpcRequest rpcRequest = RpcRequest.builder()
                        .interfaceName(declaredField.getType().getName())
                        .build();

                InetSocketAddress inetSocketAddress = nacos.lookupService(rpcRequest);
                RpcClientProxy rpcClientProxy =
                        new RpcClientProxy(inetSocketAddress.getHostString(), inetSocketAddress.getPort());
                Object clientProxy = rpcClientProxy.getProxy(declaredField.getType());
                declaredField.setAccessible(true);
                try {
                    declaredField.set(bean, clientProxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return bean;
    }
}
