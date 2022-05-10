package cn.lastwhisper.concurrent.UncaughtExceptionHandler;

public class NoCaughtThread {
    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new Task());
            thread.start();
        } catch (Exception e) {
            System.out.println("==Exception: " + e.getMessage());
        }
    }

}

