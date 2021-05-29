package cn.shenxw;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * create by shenxiangwei on 2021/5/16 上午 12:11
 */
@Data
@Builder
public class RpcRequest implements Serializable {

    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;

}
