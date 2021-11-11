
```properties

-Xms100m -Xmx100m -XX:+HeapDumpOnOutOfMemoryError

java.lang.OutOfMemoryError: GC overhead limit exceeded
Dumping heap to java_pid7565.hprof ...
Heap dump file created [163563855 bytes in 0.990 secs]
Exception in thread "main" *** java.lang.instrument ASSERTION FAILED ***: "!errorOutstanding" with message can't create name string at JPLISAgent.c line: 807
[INFO ] 2021-07-13 14:54:06.416 cn.cunchang.threadpool.ThreadPoolOOMTest lambda$printStats$4 81 [pool-2-thread-1] Pool Size: 1
java.lang.OutOfMemoryError: GC overhead limit exceeded
at java.util.concurrent.LinkedBlockingQueue.offer(LinkedBlockingQueue.java:416)
at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1371)
at cn.cunchang.threadpool.ThreadPoolOOMTest.newFixedThreadPool_oom(ThreadPoolOOMTest.java:37)
at cn.cunchang.threadpool.ThreadPoolOOMTest.main(ThreadPoolOOMTest.java:21)

```



