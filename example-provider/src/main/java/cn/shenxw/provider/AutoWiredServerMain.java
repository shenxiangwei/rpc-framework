package cn.shenxw.provider;

import cn.shenxw.annotation.RpcScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shenxiangwei
 * @date 2021/6/7 5:03 下午
 */
@RpcScan(basePackage = {"cn.shenxw.*"})
public class AutoWiredServerMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoWiredServerMain.class);

    }
}
