

# java
## 一、String基本特性：
- StringTest1——体现String的不可变性
- StringTest2、GenerateString——StringTableSize参数对字符串常量池的影响
## 二、String的内存分配：
- StringTest3——jdk6在永久代，jdk78在堆中

# java1
## 三、String的基本操作：
- StringTest4——通过debug查看jvm中字符串的数量，来验证字符串常量池的存在
- Memory——运行时内存结构
## 四、字符串拼接操作：
- StringTest5——编译器优化、常量池等

# java2
## 五、intern()的使用
- intern()的使用 jdk6 vs jdk7：【StringIntern】，扩展【StringIntern1】
- intern()的练习1：【StringExer1、StringExer2】
- 面试题 new string()创建了几个对象：【StringNewTest】
- intern()的效率测试：【StringIntern2】——节省内存，同时减少cpu运算

# java3
## 六、StringTable的垃圾回收
- StringGCTest
