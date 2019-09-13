package cn.lastwhisper.springboot.dubbo.provider.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * @author lastwhisper
 */
public class MyFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("服务调用之前");
        Result invoke = invoker.invoke(invocation);
        System.out.println("服务调用之后");
        return invoke;

    }
}
