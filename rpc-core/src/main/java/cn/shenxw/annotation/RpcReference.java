package cn.shenxw.annotation;

import java.lang.annotation.*;

/**
 * rpc引用注解,注入rpc服务实现类
 *
 * @author shenxiangwei
 * @date 2021/6/7 4:35 下午
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface RpcReference {

    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";
}
