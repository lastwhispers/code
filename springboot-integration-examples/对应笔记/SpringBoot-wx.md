> **该项目源码地址：https://github.com/ggb2312/springboot-integration-examples（其中包含SpringBoot和其他常用技术的整合，配套源码以及笔记。基于最新的 SpringBoot2.1+，欢迎各位 Star）**

# 1. 开发前准备

## 1.1 前置知识
- java基础
- SpringBoot简单基础知识

## 1.2 环境参数
- 开发工具：IDEA
- 基础环境：Maven+JDK8
- 所用技术：SpringBoot、lombok、mybatisplus、微信小程序
- SpringBoot版本：2.1.4

## 1.3 涉及知识点
- 微信小程序登录流程

# 2. 微信小程序登录流程
微信小程序登录流程涉及到三个角色：**小程序、开发者服务器、微信服务器**

三者交互步骤如下：

>第一步：`小程序`通过wx.login()获取code。
>第二步：`小程序`通过wx.request()发送code到开发者服务器。
>第三步：`开发者服务器`接收小程序发送的code，并携带appid、appsecret（这两个需要到微信小程序后台查看）、code发送到`微信服务器`。
>第四步：`微信服务器`接收开发者服务器发送的appid、appsecret、code进行校验。校验通过后向`开发者服务器`发送session_key、openid。
>第五步：`开发者服务器`自己生成一个skey（自定义登录状态）与openid、session_key进行关联，并存到数据库中（mysql、redis等）。
>第六步：`开发者服务器`返回生成skey（自定义登录状态）到小程序。
>第七步：`小程序`存储skey（自定义登录状态）到本地。
>第八步：`小程序`通过wx.request()发起业务请求到`开发者服务器`，同时携带skey（自定义登录状态）。
>第九步：`开发者服务器`接收`小程序`发送的skey（自定义登录状态），查询skey在数据库中是否有对应的openid、session_key。
>第十步：`开发者服务器`返回业务数据到`小程序`。



登录流程时序如下：



![登录流程时序](https://upload-images.jianshu.io/upload_images/5336514-96d351a21eb8cbad.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



本文实现了前七个步骤，因为微信小程序登录的核心就是前七个步骤。



>第一步：`小程序`通过wx.login()获取code。
>第二步：`小程序`通过wx.request()发送code到开发者服务器。
>第三步：`开发者服务器`接收小程序发送的code，并携带appid、appsecret（这两个需要到微信小程序后台查看）、code发送到`微信服务器。`
>第四步：`微信服务器`接收开发者服务器发送的appid、appsecret、code进行校验。校验通过后向`开发者服务器`发送session_key、openid。
>第五步：`开发者服务器`自己生成一个skey（自定义登录状态）与openid、session_key进行关联，并存到数据库中（mysql、redis等）。
>第六步：`开发者服务器`返回生成skey（自定义登录状态）到小程序。
>第七步：`小程序`存储skey（自定义登录状态）到本地。



![本文介绍的流程](https://upload-images.jianshu.io/upload_images/5336514-fe6e3cb43c775db0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



# 3. 开发者服务器

项目结构：

![项目结构](https://upload-images.jianshu.io/upload_images/5336514-e0568415537890ba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 3.1 初始配置

（1）pom.xml配置

```xml
<!-- http请求工具包依赖 -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.2</version>
        </dependency>

        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcprov-jdk16</artifactId>
            <version>1.46</version>
        </dependency>

        <!--base64加密解密-->
        <!--shiro依赖和缓存-->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>1.4.0</version>
            <exclusions>
                <exclusion>
                    <artifactId>slf4j-api</artifactId>
                    <groupId>org.slf4j</groupId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.47</version>
        </dependency>

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
```

（2）application.properties

```properties
spring.application.name = lastwhisper-wxlogin
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/wxlogin?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
```

## 3.2 小程序用户表

创建一个用户表存储用户的openid等数据。

```sql
CREATE TABLE `user`  (
  `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'open_id',
  `skey` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'skey',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_visit_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `session_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'session_key',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint(11) NULL DEFAULT NULL COMMENT '性别',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网名',
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信用户信息' ROW_FORMAT = Dynamic;

```

## 3.3 pojo



```java
package cn.lastwhisper.springbootwx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lastwhisper
 * @desc
 * @email gaojun56@163.com
 */
@Data
@TableName("user")
public class User {
    private static final long serialVersionUID = 1L;
    /**
     * open_id
     */
    @TableId(value = "open_id",type = IdType.INPUT)
    private String openId;
    /**
     * skey
     */
    private String skey;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 最后登录时间
     */
    @TableField("last_visit_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastVisitTime;
    /**
     * session_key
     */
    @TableField("session_key")
    private String sessionKey;
    /**
     * 市
     */
    @TableField("city")
    private String city;
    /**
     * 省
     */
    @TableField("province")
    private String province;
    /**
     * 国
     */
    @TableField("country")
    private String country;
    /**
     * 头像
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;
    /**
     * 网名
     */
    @TableField("nick_name")
    private String nickName;

}

```

## 3.4 common

封装一些工具类

（1）GlobalResult

```java
package cn.lastwhisper.springbootwx.common;

/**
 * @Description: 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 */
public class GlobalResult {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;
    
    private String ok;	// 不使用

    public static GlobalResult build(Integer status, String msg, Object data) {
        return new GlobalResult(status, msg, data);
    }

    public static GlobalResult ok(Object data) {
        return new GlobalResult(data);
    }

    public static GlobalResult ok() {
        return new GlobalResult(null);
    }
    
    public static GlobalResult errorMsg(String msg) {
        return new GlobalResult(500, msg, null);
    }
    
    public static GlobalResult errorMap(Object data) {
        return new GlobalResult(501, "error", data);
    }
    
    public static GlobalResult errorTokenMsg(String msg) {
        return new GlobalResult(502, msg, null);
    }
    
    public static GlobalResult errorException(String msg) {
        return new GlobalResult(555, msg, null);
    }

    public GlobalResult() {

    }

    public GlobalResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public GlobalResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
```

（2）HttpClientUtil

```java
package cn.lastwhisper.springbootwx.common;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }
}
```

（3）WechatUtil

```java
package cn.lastwhisper.springbootwx.common;/**
 * Create by eval on 2019/3/20
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.codec.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WechatUtil
 * @Description TODO
 * @Author eval
 * @Date 9:44 2019/3/20
 * @Version 1.0
 */
public class WechatUtil {
    public static JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        // https://mp.weixin.qq.com/wxopen/devprofile?action=get_profile&token=164113089&lang=zh_CN
        //小程序appId
        requestUrlParam.put("appid", "小程序appId");
        //小程序secret
        requestUrlParam.put("secret", "小程序secret");
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
        }
        return null;
    }
}

```

## 3.5 mapper



```java
package cn.lastwhisper.springbootwx.mapper;

import cn.lastwhisper.springbootwx.pojo.User;
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

配置SpringBoot扫描mapper

```java
package cn.lastwhisper.springbootwx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.lastwhisper.springbootwx.mapper") //设置mapper接口的扫描包
@SpringBootApplication
public class SpringbootwxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootwxApplication.class, args);
    }

}

```

## 3.6 controller

用于接收用户请求，校验签名，并生成skey，存储skey、openid等数据

```java
package cn.lastwhisper.springbootwx.controller;

import cn.lastwhisper.springbootwx.common.GlobalResult;
import cn.lastwhisper.springbootwx.mapper.UserMapper;
import cn.lastwhisper.springbootwx.pojo.User;
import cn.lastwhisper.springbootwx.common.WechatUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;

/**
 * @author lastwhisper
 * @desc
 * @email gaojun56@163.com
 */
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 微信用户登录详情
     */
    @PostMapping("wx/login")
    @ResponseBody
    public GlobalResult user_login(@RequestParam(value = "code", required = false) String code,
                                   @RequestParam(value = "rawData", required = false) String rawData,
                                   @RequestParam(value = "signature", required = false) String signature,
                                   @RequestParam(value = "encrypteData", required = false) String encrypteData,
                                   @RequestParam(value = "iv", required = false) String iv) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return GlobalResult.build(500, "签名校验失败", null);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        User user = this.userMapper.selectById(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");

            user = new User();
            user.setOpenId(openid);
            user.setSkey(skey);
            user.setCreateTime(new Date());
            user.setLastVisitTime(new Date());
            user.setSessionKey(sessionKey);
            user.setCity(city);
            user.setProvince(province);
            user.setCountry(country);
            user.setAvatarUrl(avatarUrl);
            user.setGender(Integer.parseInt(gender));
            user.setNickName(nickName);

            this.userMapper.insert(user);
        } else {
            // 已存在，更新用户登录时间
            user.setLastVisitTime(new Date());
            // 重新设置会话skey
            user.setSkey(skey);
            this.userMapper.updateById(user);
        }
        //encrypteData比rowData多了appid和openid
        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        GlobalResult result = GlobalResult.build(200, null, skey);
        return result;
    }
}

```


# 4. 微信小程序

项目结构：

![项目结构](https://upload-images.jianshu.io/upload_images/5336514-5d79f5e8685c1451.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 4.1 初始配置

![初始配置](https://upload-images.jianshu.io/upload_images/5336514-f3c3d1b092e2bc43.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



## 4.2 me.wxml

```html
<view class="container">
  <!-- 登录组件 https://developers.weixin.qq.com/miniprogram/dev/api/wx.getUserInfo.html -->  
  <button wx:if="{{!hasUserInfo}}" open-type="getUserInfo" bind:getuserinfo="onGetUserInfo">授权登录</button>
  <!-- 登录后使用open-data -->
  <view class="avatar-container avatar-position">
      <image src="{{userInfo.avatarUrl}}" wx:if="{{hasUserInfo}}" class="avatar" />
      <open-data wx:if="{{hasUserInfo}}" type="userNickName"></open-data>
  </view>
</view>
```

## 4.3 me.wxss

无

## 4.4 me.json

```javascript
{
  
}
```



## 4.5 me.js

```javascript
// pages/me/me.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasUserInfo: false,
    userInfo: null
  },

  onLoad: function() {
    // 页面加载时使用用户授权逻辑，弹出确认的框  
    this.userAuthorized()
  },
  
  userAuthorized() {
    wx.getSetting({
      success: data => {
        if (data.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: data => {
              this.setData({
                hasUserInfo: true,
                userInfo: data.userInfo
              })
            }
          })
        } else {
          this.setData({
            hasUserInfo: false
          })
        }
      }
    })
  },

  onGetUserInfo(e) {
    const userInfo = e.detail.userInfo
    if (userInfo) {
      // 1. 小程序通过wx.login()获取code
      wx.login({
        success: function(login_res) {
          //获取用户信息
          wx.getUserInfo({
            success: function(info_res) {
              // 2. 小程序通过wx.request()发送code到开发者服务器
              wx.request({
                url: 'http://localhost:8080/wx/login',
                method: 'POST',
                header: {
                  'content-type': 'application/x-www-form-urlencoded'
                },
                data: {
                  code: login_res.code, //临时登录凭证
                  rawData: info_res.rawData, //用户非敏感信息
                  signature: info_res.signature, //签名
                  encrypteData: info_res.encryptedData, //用户敏感信息
                  iv: info_res.iv //解密算法的向量
                },
                success: function(res) {
                  if (res.data.status == 200) {
                    // 7.小程序存储skey（自定义登录状态）到本地
                    wx.setStorageSync('userInfo', userInfo);
                    wx.setStorageSync('skey', res.data.data);
                  } else{
                    console.log('服务器异常');
                  }
                },
                fail: function(error) {
                  //调用服务端登录接口失败
                  console.log(error);
                }
              })
            }
          })
        }
      })
      this.setData({
        hasUserInfo: true,
        userInfo: userInfo
      })
    }
  }

})
```

## 4.6 app.json

设置app.json的pages

```javascript
{
  "pages":[
    "pages/me/me"
  ],
  "window":{
    "backgroundTextStyle":"light",
    "navigationBarBackgroundColor": "#fff",
    "navigationBarTitleText": "WeChat",
    "navigationBarTextStyle":"black"
  },
  "debug":true
}
```



# 5. 测试

启动开发者服务器，启动SpringBoot的main方法。

打开微信小程序开发者工具

![清空缓存](https://upload-images.jianshu.io/upload_images/5336514-6390ebbe06fd2966.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击授权登录，并允许。

![授权登录](https://upload-images.jianshu.io/upload_images/5336514-f91936c2c2506ac1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

登录成功

![登录成功](https://upload-images.jianshu.io/upload_images/5336514-b2cbb72779e75f98.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

查看数据库，openid、skey以及用户信息等存入了数据库。

![用户信息入库](https://upload-images.jianshu.io/upload_images/5336514-640be1be797a4121.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

同时微信小程序将skey等存储到本地，每次发起请求时都可以携带上。

![skey存储本地](https://upload-images.jianshu.io/upload_images/5336514-c37c3231a752219c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)