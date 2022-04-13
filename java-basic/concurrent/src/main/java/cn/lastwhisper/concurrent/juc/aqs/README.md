1. ThreadNoSafe 线程不安全
2. ThreadSafe 通过互斥保证线程安全
3. mylock1 手动实现Lock，通过synchronized和wait、notify
4. mylock2 手动实现Lock，通过aqs