import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyObject;
import java.lang.reflect.Method;

/**
 * Created by shfq on 2016/10/17.
 */
public class ProxyFactoryExample_$$_jvst9eb_0 extends ProxyFactoryExample implements ProxyObject {
    private MethodHandler handler;
    public ProxyFactoryExample_$$_jvst9eb_0(MethodHandler handler) {
        this.handler = handler;
    }

    public static void main(String[] args) throws Throwable {
        MethodHandler tracingMethodHandler = new MethodHandler() {
            public Object invoke(Object self, Method thisMethod,
                                 Method proceed, Object[] args) throws Throwable {
                return proceed.invoke(self, args);
            }
        };

        ProxyFactoryExample proxyObj = new ProxyFactoryExample_$$_jvst9eb_0(tracingMethodHandler);
        proxyObj.foo();
    }

    @Override
    public final void foo() throws Exception {
        try {
            // 获取 ProxyFactoryExample 类的 foo() 方法
            Method superMethod = this.getClass().getMethod("foo");
            // 获取 ProxyFactoryExample_$$_jvst9eb_0 类的 _d3foo() 方法
            Method proceedMethod = this.getClass().getDeclaredMethod("_d3foo");
            handler.invoke(this, superMethod, proceedMethod, new Object[]{});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    public final void _d3foo()  throws Exception  {
        super.foo();
    }


    public void setHandler(MethodHandler methodHandler) {
        this.handler = methodHandler;

    }

    public MethodHandler getHandler() {
        return handler;
    }
    // 省略了其他方法，其他方法都是类似的
}
