package cn.lastwhisper.concurrent.example.future;

//获取真实数据
public class RealData extends Data {

    private String requestData;

    public RealData(String requestData) {
        System.out.println("正在使用data进行网络请求，data" + requestData + "开始");
        try {
            //模拟执行业务逻辑耗时时间
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作执行完毕...获取结果");
        //获取返回结果
        this.requestData = "结果";
    }

    @Override
    public String getRequest() {
        return requestData;
    }

}