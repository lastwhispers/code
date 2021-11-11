


# 垃圾回收相关算法
# java
## 一、垃圾标记阶段的算法之引用计数算法
- 引用计数法，循环引用——【RefCountGC】

## 二、垃圾标记阶段的算法之可达性分析算法
- GC Roots

## 三、对象的finalization机制
- finalize——【CanReliveObj】

## 四、MAT与JProfiler的GC Roots溯源
- 【GCRootsTest】使用工具查看gc roots。先使用jvisualvm进行dump文件，再使用mat进行分析
- 【HeapOOM】


# 垃圾回收相关概念
# java1
## 一、System.gc()的理解
- 【SystemGCTest】
- 【LocalVarGC】

## 二、内存溢出与内存泄露

## 三、Stop The World
- 【StopTheWorldDemo】

## 四、垃圾回收的并行与并发

# java2
强、软、弱、虚、finalize引用



