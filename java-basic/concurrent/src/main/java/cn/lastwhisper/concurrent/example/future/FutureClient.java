package cn.lastwhisper.concurrent.example.future;

public class FutureClient {
    //用户请求时候就会调用这个方法
    public Data request(String requestData) {
        FutureData futureData = new FutureData();
        //开启一个线程
        new Thread(() -> {
            //会有阻塞  但是不影响到主线程
            RealData realData = new RealData("toov5"); //执行业务逻辑 然后返回结果
            futureData.setRealData(realData);  //把加载到的值（返回结果）设置给他

        }).start();

        return futureData;
    }

}