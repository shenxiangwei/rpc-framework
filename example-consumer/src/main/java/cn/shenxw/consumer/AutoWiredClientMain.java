package cn.shenxw.consumer;

import cn.shenxw.annotation.RpcScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shenxiangwei
 * @date 2021/6/7 4:51 下午
 */
@RpcScan(basePackage = {"cn.shenxw"})
public class AutoWiredClientMain {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoWiredClientMain.class);
        TestController helloController = (TestController) applicationContext.getBean("testController");
        helloController.test();
    }
}
