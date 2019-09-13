package cn.lastwhisper.dubbo.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * @author lastwhisper
 */
public class MyFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        System.out.println("服务调用之前,方法名：" + invocation.getMethodName());
        //调用后续服务
        Result invoke = invoker.invoke(invocation);

        System.out.println("服务调用之后");

        return invoke;
    }
}
