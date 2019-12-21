package cn.lastwhisper.cache.ha;


import cn.lastwhisper.cache.ha.http.HttpClientUtils;

public class RejectTest {

    private static class TestThread extends Thread {

        private int index;

        public TestThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo?productId=-2");
            System.out.println("第" + (index + 1) + "次请求，结果为：" + response);
        }

    }

    /**
     * 测试hystrix线程池的maxCoreSize和QueueSize
     */
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 25; i++) {
            new TestThread(i).start();
        }

    }

}
