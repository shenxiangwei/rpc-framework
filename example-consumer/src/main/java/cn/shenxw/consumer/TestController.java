package cn.shenxw.consumer;

import cn.shenxw.annotation.RpcReference;
import cn.shenxw.api.Hello;
import cn.shenxw.api.HelloService;
import org.springframework.stereotype.Component;

/**
 * @author shenxiangwei
 * @date 2021/6/7 4:58 下午
 */
@Component
public class TestController {
    @RpcReference
    private HelloService helloService;

    public void test() throws InterruptedException {
        String hello = this.helloService.hello(new Hello("111", "222"));
        //如需使用 assert 断言，需要在 VM options 添加参数：-ea
        assert "Hello description is 222".equals(hello);
        Thread.sleep(12000);
        for (int i = 0; i < 10; i++) {
            System.out.println(helloService.hello(new Hello("111", "222")));
        }
    }
}
