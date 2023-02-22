package cn.cunchang.spring;

import org.springframework.util.StopWatch;

import java.util.Arrays;

public class StopWatchTest {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("串行多任务");

        stopWatch.start("任务1");
        Thread.sleep(100 * 1);
        stopWatch.stop();

        stopWatch.start("任务2");
        Thread.sleep(100 * 2);
        stopWatch.stop();

        stopWatch.start("任务3");
        Thread.sleep(100 * 3);
        stopWatch.stop();

        stopWatch.setKeepTaskList(true); //是否构建TaskInfo信息
        Arrays.stream(stopWatch.getTaskInfo()).forEach(sw ->
                System.out.println("TaskInfo："+sw.getTaskName()+" TimeMillis："+
                        sw.getTimeMillis()+" TimeSeconds："+sw.getTimeSeconds()));
        // 在start()方法和stop()方法间时，isRunning()返回true
        System.out.println("isRunning："+stopWatch.isRunning());
        System.out.println("prettyPrint：\n"+stopWatch.prettyPrint());//打印详细信息
        System.out.println("shortSummary：\n"+stopWatch.shortSummary());//打印简要信息
    }
}


