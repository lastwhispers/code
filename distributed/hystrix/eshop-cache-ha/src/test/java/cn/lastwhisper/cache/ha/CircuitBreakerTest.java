package cn.lastwhisper.cache.ha;


import cn.lastwhisper.cache.ha.http.HttpClientUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CircuitBreakerTest {

    @Test
    public void testCircuitBreaker() throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo?productId=1");
            System.out.println("第" + (i + 1) + "次正常请求，断路器状态：close，结果为：" + response);
        }
        for (int i = 15; i < 40; i++) {
            String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo?productId=-1");
            System.out.println("第" + (i + 1) + "次异常请求，断路器状态：close，结果为：" + response);
        }
        System.out.println("等待5秒钟，requestVolumeThreshold和errorThresholdPercentage都满足断路器状态置为open");
        Thread.sleep(5000);
        /*
         * 等待了5s后，
         * 	请求滑动窗口满足	request num=40>requestVolumeThreshold=30
         *	异常请求比例 errorThresholdPercentage = 25/40=62.5%>40%
         *	requestVolumeThreshold和errorThresholdPercentage都满足断路器状态置为open
         */
        for (int i = 40; i < 50; i++) {
            if (i % 2 == 0) {
                String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo?productId=1");
                System.out.println("第" + (i + 1) + "次短路正常请求，断路器状态：open，结果为：" + response);
            } else {
                String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo?productId=-1");
                System.out.println("第" + (i + 1) + "次短路异常请求，断路器状态：open，结果为：" + response);
            }

        }
        // 统计单位，有一个时间窗口的，我们必须要等到那个时间窗口过了以后，才会说，hystrix看一下最近的这个时间窗口
        // 比如说，最近的10秒内，有多少条数据，其中异常的数据有没有到一定的比例
        // 如果到了一定的比例，那么才会去短路
        System.out.println("等待5秒钟，断路器状态：holf-open，尝试正常请求");
        Thread.sleep(3001);
        for (int i = 50; i < 60; i++) {
			String response = HttpClientUtils.sendGetRequest("http://localhost:8081/getProductInfo?productId=1");
        	if(i==50){
				System.out.println("第" + (i + 1) + "次正常请求，断路器状态：holf-open，结果为：" + response);
			}else {
				System.out.println("第" + (i + 1) + "次正常请求，断路器状态：open，结果为：" + response);
			}

        }
    }

}
