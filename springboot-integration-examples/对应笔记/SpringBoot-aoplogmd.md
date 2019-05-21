> **该项目源码地址：https://github.com/ggb2312/springboot-integration-examples（其中包含SpringBoot和其他常用技术的整合，配套源码以及笔记。基于最新的 SpringBoot2.1+，欢迎各位 Star）**

# 1. 开发前准备

## 1.1 前置知识
- java基础自定义注解、反射
- Spring aop
- SpringBoot简单基础知识即可

## 1.2 环境参数
- 开发工具：IDEA
- 基础环境：Maven+JDK8
- 所用技术：SpringBoot、lombok、mybatisplus、**Spring aop**
- SpringBoot版本：2.1.4

## 1.3 涉及知识点
- 自定义注解、 反射
- spring aop 环绕通知

  

# 2. aop日志实现

AOP（Aspect Oriented Programming）是一个大话题，这里不做介绍，直接使用。
**实现效果：**用户在浏览器操作web页面，对应的操作会被记录到数据库中。
**实现思路：**自定义一个注解，将注解加到某个方法上，使用aop环绕通知代理带有注解的方法，在环绕前进行日志准备，执行完方法后进行日志入库。
**项目结构：**
![项目结构](https://upload-images.jianshu.io/upload_images/5336514-c965fd1d513dedec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



## 2.1 log表

```sql
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operateor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operateType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operateDate` datetime(0) NULL DEFAULT NULL,
  `operateResult` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```



## 2.2 pojo

log表对应的实体类，使用了lombok的`@Data`注解，也可以使用get、set代替，使用了mybatisplus，所以配置了@TableName等注解，如果使用mybatis或者其他的orm，可以把注解拿掉。

```java
package cn.lastwhisper.springbootaop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("log")
public class Log {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("operateor")
    private String operateor;

    @TableField("operateType")
    private String operatetype;

    @TableField("operateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatedate;

    @TableField("operatereSult")
    private String operateresult;

    @TableField("ip")
    private String ip;
}
```



## 2.3 controller

该Controller用于接收用户的请求，并对用户操作进行记录。

```java
package cn.lastwhisper.springbootaop.controller;
import cn.lastwhisper.springbootaop.core.annotation.LogAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lastwhisper
 * @desc
 * @email gaojun56@163.com
 */
@RestController
public class UserController {
    /**
     * @desc 这里为了方便直接在controller上进行aop日志记录，也可以放在service上。
     * @author lastwhisper
     * @Param 
     * @return
     */
    @LogAnno(operateType = "添加用户")
    @RequestMapping(value = "/user/add")
    public void add() {
        System.out.println("向数据库中添加用户!!");
    }
}
```



## 2.4 mapper

该mapper用于对log表进行操作

```java
package cn.lastwhisper.springbootaop.mapper;

import cn.lastwhisper.springbootaop.pojo.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @desc
 * 
 * @author lastwhisper
 * @email gaojun56@163.com
 */
public interface LogMapper extends BaseMapper<Log> {
}

```



## 2.5 core

### 2.5.1 日志注解

该注解用于标识需要被环绕通知进行日志操作的方法

```java
package cn.lastwhisper.springbootaop.core.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 *
 * @author lastwhisper
 */
@Order(20)
@Target(ElementType.METHOD) // 方法注解
@Retention(RetentionPolicy.RUNTIME) // 运行时可见
public @interface LogAnno {
    String operateType();// 记录日志的操作类型
}
```

### 2.5.2 aop环绕通知类

对带有LogAnno注解的方法进行环绕通知日志记录。

`@Order(3)`注解一定要带上，**标记支持AspectJ的切面排序**。

```java
package cn.lastwhisper.springbootaop.core.aop;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;

import cn.lastwhisper.springbootaop.core.annotation.LogAnno;
import cn.lastwhisper.springbootaop.core.common.HttpContextUtil;
import cn.lastwhisper.springbootaop.mapper.LogMapper;
import cn.lastwhisper.springbootaop.pojo.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP实现日志
 * 
 * @author 最后的轻语_dd43
 * 
 */
@Order(3)
@Component
@Aspect
public class LogAopAspect {
	// 日志mapper，这里省事少写了service
	@Autowired
	private LogMapper logMapper;

	/**
	 * 环绕通知记录日志通过注解匹配到需要增加日志功能的方法
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(cn.lastwhisper.springbootaop.core.annotation.LogAnno)")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// 1.方法执行前的处理，相当于前置通知
		// 获取方法签名
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		// 获取方法
		Method method = methodSignature.getMethod();
		// 获取方法上面的注解
		LogAnno logAnno = method.getAnnotation(LogAnno.class);
		// 获取操作描述的属性值
		String operateType = logAnno.operateType();
		// 创建一个日志对象(准备记录日志)
		Log log = new Log();
		log.setOperatetype(operateType);// 操作说明

		// 设置操作人，从session中获取，这里简化了一下，写死了。
		log.setOperateor("lastwhisper");
		String ip = HttpContextUtil.getIpAddress();
		log.setIp(ip);
		Object result = null;
		try {
			// 让代理方法执行
			result = pjp.proceed();
			// 2.相当于后置通知(方法成功执行之后走这里)
			log.setOperateresult("正常");// 设置操作结果
		} catch (SQLException e) {
			// 3.相当于异常通知部分
			log.setOperateresult("失败");// 设置操作结果
		} finally {
			// 4.相当于最终通知
			log.setOperatedate(new Date());// 设置操作日期
			logMapper.insert(log);// 添加日志记录
		}
		return result;
	}
	
}
```

### 2.5.3 common

用于获取用户的ip

```java
package cn.lastwhisper.springbootaop.core.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc
 * 
 * @author lastwhisper
 * @email gaojun56@163.com
 */
public class HttpContextUtil {
    public static HttpServletRequest getRequest() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    /**
     * 获取IP地址的方法
     *
     * @param request 传一个request对象下来
     * @return
     */
    public static String getIpAddress() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
```

## 2.6 最终配置

配置application.properties文件

```properties
spring.application.name = lastwhisper-aoplog
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/wxlogin?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=root

```

配置springboot启动类SpringbootaopApplication

```java
package cn.lastwhisper.springbootaop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.lastwhisper.springbootaop.mapper") //设置mapper接口的扫描包
@SpringBootApplication
public class SpringbootaopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootaopApplication.class, args);
    }

}

```

至此一个完整的SpringBoot AOP日志系统基本成型。

# 3. 测试

启动`SpringbootaopApplication`的main方法。访问 http://localhost:8080/user/add

会在idea的控制台看到 ``

![console](https://upload-images.jianshu.io/upload_images/5336514-f8f34db34104ff59.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后再看一下数据库，保存了日志信息。
![aop 日志](https://upload-images.jianshu.io/upload_images/5336514-7239a28ae9997af1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



如果是springmvc项目没有生效，应该是自定义aop与事务aop顺序问题，需要在配置文件中配置`order="200"`。如果配置文件配置order无效，建议不要使用配置文件事务，使用注解事务。