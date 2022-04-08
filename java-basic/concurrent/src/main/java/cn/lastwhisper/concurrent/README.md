# basic
1. state 包下；Java线程的状态
2. DeadLockDemo；死锁 demo
3. Daemon  
   Daemon线程是一种支持型线程，因为它主要被用作程序中后台调度以及支持性工作。这
   意味着，当一个Java虚拟机中不存在非Daemon线程的时候，Java虚拟机将会退出。可以通过调
   用Thread.setDaemon(true)将线程设置为Daemon线程。
4. Interrupted；中断
5. Deprecated；线程暂停、恢复和停止对应线程Thread的API就是suspend()、resume()和stop()，这些方法已经被废弃
6. Shutdown；安全地终止线程