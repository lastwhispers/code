# 线程不同状态对中断的反应

## RUNNABLE

线程在运行或具备运行条件只是在等待操作系统调度
案例：InterruptRunnableDemo； 
如果线程在RUNNABLE，interrupt()只是会设置线程的中断标志位，没有任何其它作用。  

## WAITING/TIMED_WAITING：

线程在等待某个条件或超时 
案例：InterruptWaitingDemo  

在这些状态时，对线程对象调用interrupt()会使得该线程抛出InterruptedException，抛出异常后，中断标志位会被清空(线程的中断标志位会由true重置为false，因为线程为了处理异常已经重新处于就绪状态。

捕获到InterruptedException，通常表示希望结束该线程，线程大概有两种处理方式：
1、向上传递该异常，这使得该方法也变成了一个可中断的方法，需要调用者进行处理
2、有些情况，不能向上传递异常，比如Thread的run方法，它的声明是固定的，不能抛出任何受检异常，这时，应该捕获异常，进行合适的清理操作，清理后，一般应该调用Thread的interrupt方法再次设置中断标志位，使得当前线程知道自己发生了中断。

## BLOCKED

线程在等待锁，试图进入同步块
案例：InterruptSynchronizedDemo
如果线程在等待锁，对线程对象调用interrupt()只是会设置线程的中断标志位，线程依然会处于BLOCKED状态，也就是说，interrupt()并不能使一个在等待锁的线程真正”中断”。

## NEW/TERMINATED

线程还未启动或已结束
如果线程尚未启动(NEW)，或者已经结束(TERMINATED)，则调用interrupt()对它没有任何效果，中断标志位也不会被设置。

## 特殊的RUNNABLE
案例：
- InterruptReadDemo 无法通过中断取消
- InterruptReadCancelDemo 
Java线程在进行io操作时，在java中的状态也是RUNNABLE

1. 实现java.nio.channels.InterruptibleChannel接口的通道是可中断的：如果某个线程在可中断通道上因调用某个阻塞的 I/O 操作（常见的操作一般有这些：serverSocketChannel. accept()、socketChannel.connect、socketChannel.open、socketChannel.read、socketChannel.write、fileChannel.read、fileChannel.write）而进入阻塞状态，而另一个线程又调用了该阻塞线程的 interrupt 方法，这将导致该通道被关闭，并且已阻塞线程接将会收到ClosedByInterruptException，并且设置已阻塞线程的中断状态。另外，如果已设置某个线程的中断状态并且它在通道上调用某个阻塞的 I/O 操作，则该通道将关闭并且该线程立即接收到 ClosedByInterruptException；并仍然设置其中断状态。
2. 如果线程阻塞于Selector调用，则线程的中断标志位会被设置，同时，阻塞的调用会立即返回。
3. InputStream的read调用，该操作是不可中断的，如果流中没有数据，read会阻塞 (但线程状态依然是RUNNABLE)，且不响应interrupt()，与synchronized类似，调用interrupt()只会设置线程的中断标志，同时由于read阻塞，当前线程也无法检测中断标志。



