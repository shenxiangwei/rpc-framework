package cn.shenxw.provider;

import cn.shenxw.api.Hello;
import cn.shenxw.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by shenxiangwei on 2021/5/16 上午 12:30
 */
public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(Hello hello) {
        logger.info("HelloServiceImpl收到: {}.", hello.getMessage());
        String result = "Hello description is " + hello.getDescription();
        logger.info("HelloServiceImpl返回: {}.", result);
        return result;
    }
}
