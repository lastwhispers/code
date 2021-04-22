参考链接：[http://blog.javaforge.net/post/55630869369/creating-class-proxies-with-javassist](http://blog.javaforge.net/post/55630869369/creating-class-proxies-with-javassist)
参考链接：[http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method](http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method)

最近在读 mybatis 源码的时候想研究一下 mybatis 的懒加载是怎么工作的。
基本的原理我是知道的，用了代理对象。但是代理对象又是怎么工作的，就不太清楚，我有自己的两种想法但不知道具体哪一种是对的。

### javassist 动态代理 demo
mybatis 框架用 javassist 为懒加载对象创建了代理对象。
下面是代码，完整工程可以在我的 github 下载：[demo](https://github.com/fengsmith/javassist-demo)

    public class ProxyFactoryExample {
          public void foo() throws Exception {
              System.out.println("Foo method executed.");
          }
      
        public static void main(String[] args) throws Throwable {
            ProxyFactory factory = new ProxyFactory(); // 代理工厂
            factory.setSuperclass(ProxyFactoryExample.class); // 设置父类
            MethodHandler tracingMethodHandler = new MethodHandler() {
                public Object invoke(Object self, Method thisMethod,
                                     Method proceed, Object[] args) throws Throwable {
                    return proceed.invoke(self, args);
                }
            };
            // 创建代理对象 第一个参数代表的是调用构造方法创建代理对象时所需的参数类型，在这里我们的构造方法不需要参数，所以传一个空的 class 类型数组，即: new Class[0] 。
            // 第二个参数代表的是具体的参数值，在这里我们需要参数，所以传一个空的 Object 类型数组，即: new Object[0]。第三个参数是 MethodHandler 对象，需要把代理对象和 MethodHandler 对象关联在一起。
            ProxyFactoryExample proxyObj = (ProxyFactoryExample) factory.create(
                    new Class[0], new Object[0], tracingMethodHandler);
    
            proxyObj.foo();
        }
      }  

### javassist 动态代理简单分析

代码很少，我简单的讲一下这段代码是干什么的。
ProxyFactory 是个工厂类，工厂类自然是创建对象的。ProxyFactory 创建的代理对象的所属类需要继承一个类，我们创建的这个代理对象的所属类继承自 ProxyFactoryExample 类。

在创建代理对象时需要关联一个 MethodHandler 对象。在代理对象上调用方法时，所有的方法都会转发给 MethodHandler 对象，由 MethodHandler 的 invoke 方法来统一处理。

下面，我们运行一下代码，结果是：
    
    Foo method executed.

### 利用 IntelliJ 工具剖析 javassist 动态代理
但是，代码是怎么工作的，还是有些迷糊。

下面，我们通过 IntelliJ 的调试功能来分析一下（顺便安利一下，IntelliJ 真是一款神器）：

![这里写图片描述](http://img.blog.csdn.net/20161018212803140)

通过截图我们可以看到 proxyObj 的类型是 ProxyFactoryExample_$$_jvst9eb_0 ，我们也可以通过 IntelliJ 的 Evaluate Code Fragment 工具来动态的执行代码，通过执行代码来观察 proxyObj 代理对象。

![这里写图片描述](http://img.blog.csdn.net/20161018213106239)

通过执行下面这行代码可以获取到代理类的父类：

    proxyObj.getClass().getSuperclass();// 获取代理类的父类

  ![这里写图片描述](http://img.blog.csdn.net/20161018213234185)  

从截图中可以看出代理类继承了 ProxyFactoryExample 类。

通过执行下面这行代码可以获取到代理类所实现的接口：
    
    proxyObj.getClass().getInterfaces();
    
![这里写图片描述](http://img.blog.csdn.net/20161018213547588)

    
由上面的截图可以看出：代理类实现了 ProxyObject 接口。

执行下面这行代码可以获取到代理类的所有属性：

    // 获取到 proxyObj 对象所属的类所定义的所有的属性（包括公有、私有以及任何访问权限的），但不包括其父类/接口定义的任何属性。详情见 Class.getDeclaredFields(); 方法的说明。
    proxyObj.getClass().getDeclaredFields(); 

![这里写图片描述](http://img.blog.csdn.net/20161018214033611)
    
#### 可以从上面的截图中看出该代理类共定义了 4 个成员变量：
  
- handler
- filter_signature
- serialVersionUID
- _methods_

下面这行代码可以获取到代理类所定义的所有方法：

    // 获取到 proxyObj 对象所属的类所定义的所有的方法（包括公有、私有以及任何访问权限的），但不包括其父类/接口定义的任何方法。详情见 Class.getDeclaredMethods(); 方法的说明。
    proxyObj.getClass().getDeclaredMethods();
![这里写图片描述](http://img.blog.csdn.net/20161018214128611)  
  
#### 从上面的截图中我们可以看出该代理类共定义了 15 个方法：
    
- getHandler
- finalize
- equals
- toString
- hashCode
- clone
- writeReplace
- foo
- setHandler
- _d0clone
- _d1equals
- _d2finalize
- _d3foo
- _d5hashCode
- _d9toString

其中 getHandler 和 setHandler 这两个方法是 ProxyObject 接口的两个方法，代理类又重写了这两个方法。
其中 finalize、equals、toString、hashCode、clone 这 5 个方法是 Object 类的 5 个方法，代理类重写了这 5 个方法。
其中 foo 方法是代理类的父类 ProxyFactoryExample 的方法，代理类也重写了这个方法。
writeReplace 方法不知道是干什么用的，好像 ProxyFactory 创建的每个代理类都有这个方法。
还剩下 6 个方法，这 6 个方法的面孔看起来很熟悉：

- _d0clone 像 clone
- _d1equals 像 equals
- _d2finalize 像 finalize
- _d3foo 像 foo
- _d5hashCode 像 hashCode
- _d9toString 像 toString

通过上面对代理对象的反射分析，我们大致的可以看出代理类是个什么样子了。

代理类继承了被代理的类（ProxyFactoryExample）并且重写了被代理类的所有方法，重写的方法的实现都是类似的，这些方法都调用了代理类所关联的 handler 对象的 invoke() 方法。所有的逻辑都由 invoke() 方法来控制，invoke() 方法像一个控制中心一样，根据自己的需求定制实现自己的逻辑。
在这里，我们是调用重写方法相对应的那个方法。重写方法相对应的那个方法又调用了父对象的签名完全相同的方法。

### 用静态代理来模拟 javassist 动态代理
上面的话有些绕，下面我用静态代理来模拟动态代理的调用行为：

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

### 用 IntelliJ 工具来验证 javassist 代理的工作原理

难道动态代理真的就像我上面说的那样？我最先开始也有这样的疑问，我原来对重写方法（foo）相关联的那个方法（_d3foo）是怎么实现的有疑问。我原来认为有两种情况都可以有相同的行为：

- 一种是上面的这种方法，直接调用父方法：

        public final void _d3foo()  throws Exception  {
             super.foo();
        }

- 另一种是把父类的 foo() 方法的实现拷贝过来:

        public final void _d3foo()  throws Exception  {
             System.out.println("Foo method executed.");
        }

上面的这两种方法执行后的行为都是一样的，我们可以想办法来验证 javassist 到底采用的是哪一种方法，至少有 3 种方法可以验证，在这里我只讲其中的一种。

    Thread.currentThread().getStackTrace(); // 可以获取到堆栈信息，通过堆栈信息可以知道方法的执行过程

在这里，我在 foo() 方法上打个断点，利用 IntelliJ 强大的调试功能来查看方法的执行过程：

![这里写图片描述](http://img.blog.csdn.net/20161018214245567)

从图上可以看出共有 9 次方法调用，中间的几次调用是 Java 反射调用，我们不用管它。我们关心的调用过程是：

ProxyFactoryExample.main()->
ProxyFactoryExample_$$_jvst9eb_0.foo()->
ProxyFactoryExample $ 1.invoke()->
反射调用->
ProxyFactoryExample _$ $_ jvst9eb_0._d3foo()->
ProxyFactoryExample.foo()

其实就是 main()-> foo()-> invoke() ->反射调用 -> _d3foo() -> foo()

注意，上面的第一个 foo() 方法是代理类的（子类），第二个 foo() 方法是被代理类的（父类）。

通过上面的代码、截图和分析大家应该对 Java 动态代理有了个清晰的认识。

注意：我上面的分析都是原理的分析，实现肯定不是这样的，javassist 最终操作的是字节码，而我上面是利用 java 代码来模拟分析的，但原理应该是类似的。

### JDK 动态代理和 javassist 动态代理
上面是对 javassist 创建的动态代理的分析，JDK 自带的动态代理的执行过程和这个是很类似的。
之前我已经写过一篇关于 Java 动态代理的文章:[代理模式和 Java 动态代理](http://blog.csdn.net/shfqbluestone/article/details/51452116) ，对 JDK 自带的动态代理不熟悉的同学可以先看一下我前面的博客。


#### 我总结下二者的异同点：

- JDK 动态代理需要实现 0 个或多个接口，实现 0 个接口时传一个空的数组，不会报错，但在实际中应该不会出现这种场景。
- javassist 动态代理可以继承一个类的同时还可以实现多个接口，当然一个类也不继承，一个接口也不实现，也不会报错，但应该不会存在这种使用场景。
- JDK 没法为一个类创建动态代理，只能为一个接口创建动态代理，而 javassist 动态代理则可以。
- JDK 动态代理类实现了接口中的所有方法并把这些方法的调用都转发到了 InvocationHandler 的 invoke 方法，这点和 javassist 创建的动态代理类是相似的，javassist 创建的动态代理类也实现了父类/接口的所有的方法，并把对这些方法的调用转发到 MethodHandler 的 invoke() 方法，由 invoke() 方法来统一处理。

- javassist 创建的动态代理类还新增了一些父类/接口方法的关联方法，例如上例中 _d3foo() 方法就是 foo() 方法的关联方法，但在 JDK 创建的动态代理类中是没有这些关联方法的。因为前者的关联方法的目的是调用父类的相应的方法，而后者 JDK 创建的动态代理实现的是接口，接口中只是声明了方法签名，而没有方法的实现，所以自然也就不会有关联方法。
- JDK 动态代理中的 InvocationHandler 接口和 javassist 动态代理中的 MethodHandler 是相似的，两者的作用相似，两者还都有 invoke() 方法。不过这两者的 invoke() 方法的参数不一样。
        
        // InvocationHandler 的 invoke 方法
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
        
        // MethodHandler 的 invoke 方法
        public bject invoke(Object proxy, Method method, Method proceedMethod, Object[] args) throws Throwable;
  可以看出 MethodHandler 的 invoke 方法要比 InvocationHandler 的 invoke 方法多一个参数，多了的这个参数是 proceedMethod ，这个参数是 javassist 中的关联方法，在 JDK 动态代理类中是没有关联方法的。其余几个参数都是一样的，作用也是相同的。proxy 是代理对象，InvocationHandler 中的 method 是父接口的 method ，MethodHandler 中的 method 是父类/接口的方法， args 是调用其他方法所需要的参数。
  
大家可以在 github 上下载我的代码:[https://github.com/fengsmith/javassist-demo](https://github.com/fengsmith/javassist-demo)用我上面分析 javassist 代理的方法来分析 JDK 动态代理，我就不在这儿再次分析了，写一篇博客花费的时间太多了。分析的方法是一样的：利用 IntelliJ 断点调试、Evaluate Code Fragment（动态执行代码的工具）、Thread.currentThread().getStackTrace();、Java 反射等方法在程序运行时来剖析动态代理类。

希望我的博客对大家理解动态代理有帮助，如果在阅读博客的过程中有什么疑问可以留言，如果我的博客帮助到了大家，大家也可以留言告诉我。

最后我再安利一下：IntelliJ 是一款 Java 开发神器。