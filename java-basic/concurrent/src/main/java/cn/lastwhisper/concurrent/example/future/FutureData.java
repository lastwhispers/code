package cn.lastwhisper.concurrent.example.future;

//当有线程想要获取RealData时候，程序会被阻塞。等到RealData被注入才会使用getReal()方法
public class FutureData extends Data {

    private boolean isReady = false;
    private RealData realData;

    //读取data数据
    public synchronized void setRealData(RealData realData) {
        //读取结果
        if (isReady) {  //true 说明已经获取到结果了 如果获取到则直接返回结果
            System.out.println("FLAG：" + isReady);
        }
        //如果flag是false，没有获取到数据，传递realData对象
        this.realData = realData;
        isReady = true;  //获取到执行结果 改为true
        notifyAll();//唤醒
    }


    @Override
    public synchronized String getRequest() {
        while (!isReady) {  //如果false 一直等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getRequest();
    }


}