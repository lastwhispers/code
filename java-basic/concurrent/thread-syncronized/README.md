# synchronize使用


1. T0_MultiThreadAndUnsafe；

   多线程临界资源不安全

2. Juc_LockOnClass；

   synchronized加在静态方法，锁class。多个静态方法互斥

3. Juc_LockOnThisObject；

   synchronized加在成员方法，锁当前对象

4. Juc_LockOnObject

   synchronized加在对象，锁对象

# synchronize原理

使用jol-core

1. T0_ObjectSize；无锁、可偏向锁
2. T0_BasicLock；偏向锁-》轻量级锁
3. T0_heavyWeightMonitor；自旋锁后未获得到锁，升级重量级锁，
4. Juc_PrintMarkWord；对象处在偏向锁，调用hashcode，会升级为轻量级锁，因为偏向锁无法记录hashcode。





Juc_PrintMarkWord；打印markword

