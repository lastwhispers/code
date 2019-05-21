> **该项目源码地址：https://github.com/ggb2312/springboot-integration-examples（其中包含SpringBoot和其他常用技术的整合，配套源码以及笔记。基于最新的 SpringBoot2.1+，欢迎各位 Star）**

# 1. 开发前准备

## 1.1 前置知识
- java基础
- SpringBoot简单基础知识


## 1.2 环境参数
- 开发工具：IDEA
- 基础环境：Maven+JDK8
- 所用技术：SpringBoot、lombok、MybatisPlus
- SpringBoot版本：2.1.4

## 1.3 涉及知识点
- MybatisPlus简介、特性、架构
- MybatisPlus快速入门
- MybatisPlus的基本CRUD 
- MybatisPlus的高级查询：like查询、条件查询、分页查询等
  
# 2. MybatisPlus入门

## 2.1 简介

[MyBatis-Plus](https://github.com/baomidou/mybatis-plus)（简称 MP）是一个 [MyBatis](http://www.mybatis.org/mybatis-3/) 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

## 2.2 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer2005、SQLServer 等多种数据库
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 XML 热加载**：Mapper 对应的 XML 支持热加载，对于简单的 CRUD 操作，甚至可以无 XML 启动
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **支持关键词自动转义**：支持数据库关键词（order、key......）自动转义，还可自定义关键词
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作
- **内置 Sql 注入剥离器**：支持 Sql 注入剥离，有效预防 Sql 注入攻击

## 2.3 架构
![MybatisPlus架构](https://upload-images.jianshu.io/upload_images/5336514-de256f59d2e18cf3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 2.4 快速入门
项目结构：
![项目结构](https://upload-images.jianshu.io/upload_images/5336514-93f1ecb4766522a5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.4.1 创建表

```sql
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

### 2.4.2 创建工程以及导入依赖

[SpringBoot项目新建](https://www.jianshu.com/p/c6f4fbd1e492)

```xml
<dependencies>
        <!--简化代码的工具包-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!--mybatis-plus的springboot支持-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.1.1</version>
        </dependency>
        <!--mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        
    </dependencies>
```

### 2.4.3 编写application.properties文件 

```properties
spring.application.name = lastwhisper-mybatis-plus
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/mybatisplus?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
```

### 2.4.4 创建User对象 

```java
package cn.lastwhisper.springbootmybatisplus.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @desc
 * 
 * @author lastwhisper
 * @email gaojun56@163.com
 */
@Data
@ToString
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

```

### 2.4.5 编写UserMapper

```java
package cn.lastwhisper.springbootmybatisplus.mapper;

import cn.lastwhisper.springbootmybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * @desc
 * 
 * @author lastwhisper
 * @email gaojun56@163.com
 */
public interface UserMapper extends BaseMapper<User> {
}
```

### 2.4.6 设置Mapper接口的包扫描

修改SpringBoot启动类

```java
package cn.lastwhisper.springbootmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.lastwhisper.springbootmybatisplus.mapper") //设置mapper接口的扫描包
@SpringBootApplication
public class SpringbootmybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatisplusApplication.class, args);
    }

}

```

### 2.4.7 编写测试单元

```java
package cn.lastwhisper.springbootmybatisplus;

import cn.lastwhisper.springbootmybatisplus.mapper.UserMapper;
import cn.lastwhisper.springbootmybatisplus.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootmybatisplusApplicationTests {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}

```
![测试结果](https://upload-images.jianshu.io/upload_images/5336514-64faf3f0a913d511.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



## 2.5 BaseMapper

在MybatisPlus中，BaseMapper中定义了一些常用的CRUD方法，当我们自定义的Mapper接口继承BaseMapper后即可拥有了这些方法。 

> 需要说明的是：这些方法仅适合单表操作。 

BaseMapper接口的代码如下

```java
/**
 * Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 * <p>这个 Mapper 支持 id 泛型</p>
 *
 * @author hubin
 * @since 2016-01-23
 */
public interface BaseMapper<T> extends Mapper<T> {

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    int insert(T entity);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(Serializable id);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     */
    int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 根据 ID 修改
     *
     * @param entity 实体对象
     */
    int updateById(@Param(Constants.ENTITY) T entity);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T selectById(Serializable id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     * <p>注意： 只返回第一个字段的值</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作类
     */
    IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}

```



### 2.5.1 like查询

```java
@Test
    public void testselectByLike() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(new User());
        // 查询名字中包含"o"的用户
        queryWrapper.like("name", "o");

        List<User> users = this.userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
```
测试结果：

![like查询](https://upload-images.jianshu.io/upload_images/5336514-5628a2b4fca702c7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.5.2 条件查询
```java
// 条件查询
    @Test
    public void testselectByLe(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(new User());
        //查询年龄小于等于20的用户
        queryWrapper.le("age",20);

        List<User> users = this.userMapper.selectList(queryWrapper);
        for (User user:users) {
            System.out.println(user);
        }
    }
```

测试结果：

![条件查询](https://upload-images.jianshu.io/upload_images/5336514-cf18cb54eb560256.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

更多查看：http://mp.baomidou.com/guide/wrapper.html#abstractwrapper

### 2.5.3 插入数据
数据库主键自增：  @TableId(value = "ID", type = IdType.AUTO)
数据库主键自己维护：@TableId(value = "open_id",type = IdType.INPUT)

设置主键自增长
```java
@Data
@ToString
public class User {
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

设置表主键自增

![主键自增](https://upload-images.jianshu.io/upload_images/5336514-9b01af648b413e8f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

测试代码

```java
//插入数据
    @Test
    public void testSave(){
        User user = new User();
        user.setAge(21);
        user.setEmail("gaojun56@163.com");
        user.setName("gaojun");
        int count = this.userMapper.insert(user);
        System.out.println("新增数据成功! count => " + count);
    }
```
测试结果：

![测试结果](https://upload-images.jianshu.io/upload_images/5336514-147f70abd8225e68.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

数据库：

![测试结果](https://upload-images.jianshu.io/upload_images/5336514-5e1f34cb0f368f80.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.5.4 删除数据
```java
//删除数据
    @Test
    public void testDelete(){
        this.userMapper.deleteById(6L);
        System.out.println("删除成功!");
    }
```
测试结果：

![测试结果](https://upload-images.jianshu.io/upload_images/5336514-e1479c0a8b5e7c02.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.5.5 修改数据
根据id修改数据，修改不为null的字段
```java
//修改数据
@Test
    public void testUpdate(){
        User user = new User();
        user.setId(5L);
        user.setName("gaojun");
        user.setEmail("gaojun56@163.com");
        this.userMapper.updateById(user);
        System.out.println("修改成功!");
    }
```
测试结果：

![测试结果](https://upload-images.jianshu.io/upload_images/5336514-c1db5140e574d5f5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![测试结果](https://upload-images.jianshu.io/upload_images/5336514-843fd6e20cdedafa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.5.6 分页查询

首先在SpringBoot启动类中配置分页插件

```java
@MapperScan("cn.lastwhisper.springbootmybatisplus.mapper") //设置mapper接口的扫描包
@SpringBootApplication
public class SpringbootmybatisplusApplication {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatisplusApplication.class, args);
    }

}

```

测试类：

```java
//分页查询
    @Test
    public void testselectPage() {
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = this.userMapper.selectPage(page, null);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        List<User> records = userIPage.getRecords();
        for (User user : records) {
            System.out.println(user);
        }
    }
```

测试结果：
![测试结果](https://upload-images.jianshu.io/upload_images/5336514-2da133a78d36d3a9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



## 2.6 配置

虽然在MybatisPlus中可以实现零配置，但是有些时候需要我们自定义一些配置，就需要使用Mybatis原生的一些配置文件方式了。

 application.properties： 

```properties
# 指定全局配置文件
mybatis-plus.config-location = classpath:mybatis-config.xml
# 指定mapper.xml文件
mybatis-plus.mapper-locations = classpath*:mybatis/*.xml
```

更多配置：https://mp.baomidou.com/guide/config.html