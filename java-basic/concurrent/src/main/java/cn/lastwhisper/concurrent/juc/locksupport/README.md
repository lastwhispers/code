## 一、线程阻塞唤醒
方式1:  使用Object中的wait()方法让线程等待， 使用Object中的notify()方法唤醒线程
方式2:  使用JUC包中Condition的await()方法让线程等待，使用signal()方法唤醒线程
方式3:  LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程

## 二、优缺点
传统的synchronized和Lock实现等待唤醒通知的约束
1. 线程先要获得并持有锁，必须在锁块（synchronized或lock）中
2. 必须要先等待后唤醒，线程才能够被唤醒


