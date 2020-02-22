package cn.lastwhisper.concurrent.example.future;

public class Main {
    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();
        Data request = futureClient.request("cone on");
        System.out.println("数据发送成功");  //主线程
        System.out.println("主线程继续干自己的");
        String result = request.getRequest();
        System.out.println("主线程去获取结果" + result);
    }
}