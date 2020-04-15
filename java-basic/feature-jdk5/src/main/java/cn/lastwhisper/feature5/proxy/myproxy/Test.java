package cn.lastwhisper.feature5.proxy.myproxy;

public class Test {
    public static void main(String[] args) throws Throwable {
        Man man = new LiuWang();
        MyHandler myHandler = new MyHandler(man);
        Man proxyMan = (Man) MyProxy.newProxyInstance(
                new MyClassLoader(
                        "D:\\code\\GitRepository\\JavaNotes\\java-basic\\jdk5\\src\\main\\java\\cn\\lastwhisper\\jdk5\\feature\\proxy\\myproxy"
                ,"cn.lastwhisper.jdk5.feature.proxy.myproxy")
                ,Man.class, myHandler);


        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObj();
    }
}
