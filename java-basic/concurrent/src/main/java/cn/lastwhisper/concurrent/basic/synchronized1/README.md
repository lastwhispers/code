# synchronize原理

使用jol-core

1. T0_ObjectSize；无锁、可偏向锁
2. T0_BasicLock；偏向锁-》轻量级锁
3. T0_heavyWeightMonitor；自旋锁后未获得到锁，升级重量级锁，
4. Juc_PrintMarkWord；对象处在偏向锁，调用hashcode，会升级为轻量级锁，因为偏向锁无法记录hashcode。 
