package cn.shenxw.annotation;

import java.lang.annotation.*;

/**
 * rpc服务注解,
 * @author shenxiangwei
 * @date 2021/6/7 4:37 下午
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcService {
    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";
}
