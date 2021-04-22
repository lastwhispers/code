package cn.lastwhisper.proxy.myproxy;

public class Test {

    public static void main(String[] args) throws Throwable {
        Man man = new LiuWang();
        MyHandler myHandler = new MyHandler(man);
        Man proxyMan = (Man) MyProxy.newProxyInstance(
                new MyClassLoader("/Users/cunchang/workspace/github/code/java-basic/feature-jdk5/src/main/java/cn/lastwhisper/feature5/proxy/myproxy",
                        "cn.lastwhisper.feature5.proxy.myproxy")
                ,Man.class, myHandler);

        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObj();
    }

}
