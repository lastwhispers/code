> 该项目源码地址：[https://github.com/ggb2312/JavaNotes/tree/master/design-pattern](https://github.com/ggb2312/JavaNotes/tree/master/design-pattern)（设计模式相关代码与笔记）

# 1. 定义

有一个工厂对象决定创建出哪一种产品类的实例

# 2.适用场景

- 工厂类负责创建的对象比较少
- 客户端（应用层）只知道传入工厂类的参数对于如何创建对象（逻辑）不关心

# 3. 代码实例

**背景：现在慕课网需要录制Java、Python等视频课程**

## 3.1 V1版本
（1）创建一个抽象类Video类，定义具体录制视频课程的公共接口

```java
/**
 * Create by eval on 2019/1/23
 */
public abstract class Video {
    public abstract void produce();
}
```
（2）创建具体的录制视频课程类
录制Java课程

```java
/**
 * Create by lastwhisper on 2019/1/23
 */
public class JavaVideo extends Video{
    @Override
    public void produce() {
        System.out.println("录制java课程");
    }
}
```
录制Python课程

```java
/**
 * Create by lastwhisper on 2019/1/23
 */
public class PythonVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制python课程");
    }
}
```
（3）测试

![代码结构](https://upload-images.jianshu.io/upload_images/5336514-beb0832f55727c5f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

创建应用层对视频进行录制 

```java
/**
 * Create by lastwhisper on 2019/1/23
 */
public class Test {
    public static void main(String[] args) {
        Video video = new JavaVideo();
        video.produce();

    }
}
```
测试结果：

![测试结果](https://upload-images.jianshu.io/upload_images/5336514-78a04f77c58cc488.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（4）总结
如果JavaVideo类（服务层）和Test（应用层）不在同一个包中，则需要引JavaVideo类的包，但是应用层不应该知道服务层的类，这里我们可以使用简单工厂改进。

## 3.2 V2版本

在V2版本使用简单工厂修改V1版本的问题，V1版本（1）（2）步骤不变。

（1）新建一个VideoFactory类
这个类根据type创建对应的录制视频对象

```java
/**
 * Create by lastwhisper on 2019/1/23
 */
public class VideoFactory {
    /**
     * 根据type，创建对象
     * @param type
     * @return
     */
    public static Video getVideo(String type){
        if("java".equalsIgnoreCase(type)){
            return new JavaVideo();
        }else if("python".equalsIgnoreCase(type)){
            return new PythonVideo();
        }
        return null;
    }
}
```
（2）修改Test（应用层）
使用工厂类创建对象

```java
/**
 * Create by lastwhisper on 2019/1/23
 */
public class Test {
    public static void main(String[] args) {
        Video video = VideoFactory.getVideo("java");
        video.produce();

    }
}
```

（3）类图
查看此时的类图

![V2版本类图](https://upload-images.jianshu.io/upload_images/5336514-2158b758a93383b4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

（4）总结
如果我们现在需要录制前端课程，就需要修改VideoFactory的getVideo方法，添加else if，违反了“开闭原则”。

## 3.3 V3版本

在V3版本使用java的反射修改V2版本违反“开闭原则”的问题
**（1）修改VideoFactory类**

```java
/**
 * Create by lastwhisper on 2019/1/23
 */
public class VideoFactory {
    /**
     * 根据Class字节码创建对象
     * @param c
     * @return
     */
    public static Video getVideo(Class c){
        Video video = null;
        try {
            video = (Video) Class.forName(c.getName()).newInstance();
            return video;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```
**（2）修改Test**

```java
/**
 * Create by lastwhisper on 2019/1/23
 */
public class Test {
    public static void main(String[] args) {
        Video video = VideoFactory.getVideo(JavaVideo.class);
        video.produce();

    }
}
```
测试运行

![运行结果](https://upload-images.jianshu.io/upload_images/5336514-d30f6ebda8aa5b95.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**（3）类图**

![类图](https://upload-images.jianshu.io/upload_images/5336514-30cb6d11e5c038be.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**（4）总结**

此时不管需要录制什么课程都不需要修改VideoFactory工厂类了。
但是我们的VideoFactory的getVideo方法是static方法，无法扩展，如果需要扩展则去掉static修饰。

# 4. 优点

- 将创建实例的工作与使用实例的工作分开，使用者不必关心类对象如何创建，实现了解耦；
- 把初始化实例时的工作放到工厂里进行，使代码更容易维护。 更符合面向对象的原则 & 面向接口编程，而不是面向实现编程。

# 5. 缺点

- 工厂类集中了所有实例（产品）的创建逻辑，一旦这个工厂不能正常工作，整个系统都会受到影响；
- 违背“开闭原则”，一旦添加新产品就不得不修改工厂类的逻辑，这样就会造成工厂逻辑过于复杂。 
- 简单工厂模式由于使用了静态工厂方法，静态方法不能被继承和重写，会造成工厂角色无法形成基于继承的等级结构。

# 6. 扩展——JDK1.7源码中的简单工厂

## 6.1 Calendar类中的简单工厂

在IDEA使用Ctrl+Shift+N查找Calendar.java类，Ctrl+F12查看方法列表中的getInstance()方法

```java
public static Calendar getInstance()
{
    Calendar cal = createCalendar(TimeZone.getDefaultRef(), Locale.getDefault(Locale.Category.FORMAT));
    cal.sharedZone = true;
    return cal;
}
```

进入createCalendar()方法，可以发现使用了简单工厂模式创建对象

```java
private static Calendar createCalendar(TimeZone zone,
                                       Locale aLocale)
{
    Calendar cal = null;

    String caltype = aLocale.getUnicodeLocaleType("ca");
    if (caltype == null) {
        // Calendar type is not specified.
        // If the specified locale is a Thai locale,
        // returns a BuddhistCalendar instance.
        if ("th".equals(aLocale.getLanguage())
                && ("TH".equals(aLocale.getCountry()))) {
            cal = new BuddhistCalendar(zone, aLocale);
        } else {
            cal = new GregorianCalendar(zone, aLocale);
        }
    } else if (caltype.equals("japanese")) {
        cal = new JapaneseImperialCalendar(zone, aLocale);
    } else if (caltype.equals("buddhist")) {
        cal = new BuddhistCalendar(zone, aLocale);
    } else {
        // Unsupported calendar type.
        // Use Gregorian calendar as a fallback.
        cal = new GregorianCalendar(zone, aLocale);
    }

    return cal;
}
```

在project的Extenrnal Libraries中有JDK1.7的依赖进入rt.jar->java->util->Calendar；选中Calendar，Ctrl+Alt+Shfit+U,选中Java class diagrams生成Calendar类相关的类图；Ctrl+Alt+B可以显示该类的实现类。通过UML类图方式我们可以更加轻松的学习他人的源码。

![Calendar类结构](https://upload-images.jianshu.io/upload_images/5336514-b0fd829a03d340aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 6.2 LoggerFactroy类中的简单工厂

Ctrl+N（enter class name）查找LoggerFactroy类，查看它的getLogger()方法。
该方法有两个重载，不过最终都是调用一个方法getLogger(String name)。

```java
public static Logger getLogger(String name) {
  ILoggerFactory iLoggerFactory = getILoggerFactory();
  return iLoggerFactory.getLogger(name);
}
public static Logger getLogger(Class clazz) {
  return getLogger(clazz.getName());
}
```

iLoggerFactory是一个接口，有多个实现类，查看LoggerContext实现
iLoggerFactory.getLogger(name)方法使用简单工厂创建对象，根据name创建不同的对象。

```java
public final Logger getLogger(final String name) {

  if (name == null) {
    throw new IllegalArgumentException("name argument cannot be null");
  }

  // if we are asking for the root logger, then let us return it without
  // wasting time
  if (Logger.ROOT_LOGGER_NAME.equalsIgnoreCase(name)) {
    return root;
  }

  int i = 0;
  Logger logger = root;

  // check if the desired logger exists, if it does, return it
  // without further ado.
  Logger childLogger = (Logger) loggerCache.get(name);
  // if we have the child, then let us return it without wasting time
  if (childLogger != null) {
    return childLogger;
  }

  // if the desired logger does not exist, them create all the loggers
  // in between as well (if they don't already exist)
  String childName;
  while (true) {
    int h = LoggerNameUtil.getSeparatorIndexOf(name, i);
    if (h == -1) {
      childName = name;
    } else {
      childName = name.substring(0, h);
    }
    // move i left of the last point
    i = h + 1;
    synchronized (logger) {
      childLogger = logger.getChildByName(childName);
      if (childLogger == null) {
        childLogger = logger.createChildByName(childName);
        loggerCache.put(childName, childLogger);
        incSize();
      }
    }
    logger = childLogger;
    if (h == -1) {
      return childLogger;
    }
  }
}
```