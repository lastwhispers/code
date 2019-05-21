> **该项目源码地址：https://github.com/ggb2312/springboot-integration-examples（其中包含SpringBoot和其他常用技术的整合，配套源码以及笔记。基于最新的 SpringBoot2.1+，欢迎各位 Star）**

# 1. 开发前准备

## 1.1 前置知识
- java基础
- SpringBoot简单基础知识

## 1.2 环境参数
- 开发工具：IDEA
- 基础环境：Maven+JDK8
- 所用技术：SpringBoot、lombok、阿里云OSS存储服务
- SpringBoot版本：2.1.4

## 1.3 涉及知识点
- OSS简介，以及阿里云OSS控制台快速入门使用
- SpringBoot 整合 阿里云OSS 存储服务，进行文件上传、下载、查看、删除
- 阿里云OSS文档介绍，以及快速入门使用
- lombok入门使用以及IDEA lombok插件安装
- SpringMVC与AJAX前后端分离交互
- AJAX文件异步上传

# 2. 使用阿里云OSS
对象存储OSS的多重冗余架构设计，为数据持久存储提供可靠保障。
## 2.1 创建Bucket
使用OSS，首先需要创建Bucket，Bucket翻译成中文是水桶的意思，把存储的图片资源看做是水，想要盛水必须得
有桶。
进入控制台，https://oss.console.aliyun.com/overview

![新建Bucket](https://upload-images.jianshu.io/upload_images/5336514-f8c5512fa6084fe5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![新建 Bucket](https://upload-images.jianshu.io/upload_images/5336514-e3006193988291c4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

创建完成后，在左侧可以看到已经创建好的Bucket：

![Bucket](https://upload-images.jianshu.io/upload_images/5336514-b42a82d12baa340b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

选择Bucket后，即可看到对应的信息，如：url、消耗流量等

![Bucket相关信息](https://upload-images.jianshu.io/upload_images/5336514-38cb38befa6aeaf6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 2.2 管理文件
可以通过在线的方式进行管理文件

![管理文件](https://upload-images.jianshu.io/upload_images/5336514-4eea09a699e304ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 2.3 阿里云OSS文档

[阿里云OSS文档](https://help.aliyun.com/product/31815.html?spm=a2c4g.11186623.6.540.517822372ASOvk)

![阿里云OSS文档](https://upload-images.jianshu.io/upload_images/5336514-4df24be98ecfa55b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

右侧的开发指南说的更加详细
![开发指南](https://upload-images.jianshu.io/upload_images/5336514-4f709bb5848c7c3f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

阿里云虽然提供了完整的文档，但是做一个完整的前后端交互的文件上传、下载、查看、删除等操作，对于小白来说还是有点难度的，所以我把自己学习OSS的步骤以及代码分享了出来，共有需要的人使用。

# 3. 项目初始化

## 3.1 创建SpringBoot项目
在Idea中File——>New——>Project
![创建SpringBoot项目 步骤一](https://upload-images.jianshu.io/upload_images/5336514-0f9a30d8fe087c50.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![创建SpringBoot项目 步骤二](https://upload-images.jianshu.io/upload_images/5336514-a658fb76a0950b01.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![创建SpringBoot项目 步骤三](https://upload-images.jianshu.io/upload_images/5336514-6be4593b1c65adec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![创建SpringBoot项目 步骤四](https://upload-images.jianshu.io/upload_images/5336514-0ae22056caa7f423.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 3.2 Maven依赖
导入Maven相关依赖
```
<dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
            <version>2.8.3</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.4</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
            <version>2.9.9</version>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.8.1</version>
        </dependency>
```
## 3.3 安装lombok插件
因为项目中使用了lombok的`@Data`注解，当然你也可以自己写get、set等方法。
File——>settings——>Plugins
![安装lombok插件](https://upload-images.jianshu.io/upload_images/5336514-5276d48cc6776ce4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![安装lombok插件](https://upload-images.jianshu.io/upload_images/5336514-d5ac2a7871a502f2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后restart IDEA即可。

# 4. 后端服务编写
![项目结构](https://upload-images.jianshu.io/upload_images/5336514-aada92c3c74e00d2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 4.1 阿里云OSS配置
在resource下新建一个application-oss.properties
```
aliyun.endpoint=oss-cn-shanghai.aliyuncs.com
aliyun.accessKeyId=你的accessKeyId
aliyun.accessKeySecret=你的accessKeySecret
aliyun.bucketName=gaojun-testbucket
aliyun.urlPrefix=http://gaojun-testbucket.oss-cn-shanghai.aliyuncs.com/
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=1000MB
```

endpoint、bucketName、urlPrefix在OSS主面板就可以看到

![bucketName、endpoint与urlPrefix](https://upload-images.jianshu.io/upload_images/5336514-2818153bf58ebfce.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

accessKeyId、accessKeySecret需要在accesskeys里面查看

![accesskeys与accessKeySecret](https://upload-images.jianshu.io/upload_images/5336514-176959cccd4e52de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在java的包下新建一个config包，创建一个AliyunConfig.java
```java
package com.example.ossdemo.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 * @desc
 *
 * @author lastwhisper
 * @email gaojun56@163.com
 */
@Configuration
@PropertySource(value = {"classpath:application-oss.properties"})
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String urlPrefix;

    @Bean
    public OSS oSSClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
```

## 4.2 后端业务
### 4.2.1 vo

该实体类用于后台返回给前台。

```java
package com.example.ossdemo.vo;

import lombok.Data;

/**
 * @author lastwhisper
 * @desc 用于前后端交互的返回值
 * @email gaojun56@163.com
 */
@Data
public class FileUploadResult {
    // 文件唯一标识
    private String uid;
    // 文件名
    private String name;
    // 状态有：uploading done error removed
    private String status;
    // 服务端响应内容，如：'{"status": "success"}'
    private String response;
}

```
### 4.2.2 service

在service使用ossClient操作阿里云OSS，进行上传、下载、删除、查看所有文件等操作，同时可以将图片的url进行入库操作。

```java
package com.example.ossdemo.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.example.ossdemo.config.AliyunConfig;
import com.example.ossdemo.vo.FileUploadResult;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * @author lastwhisper
 * @desc
 * @email gaojun56@163.com
 */
@Service
public class FileUploadService {
    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg",
            ".jpeg", ".gif", ".png"};
    @Autowired
    private OSS ossClient;
    @Autowired
    private AliyunConfig aliyunConfig;

    /**
     * @author lastwhisper
     * @desc 文件上传
     * 文档链接 https://help.aliyun.com/document_detail/84781.html?spm=a2c4g.11186623.6.749.11987a7dRYVSzn
     * @email gaojun56@163.com
     */
    public FileUploadResult upload(MultipartFile uploadFile) {
        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),
                    type)) {
                isLegal = true;
                break;
            }
        }
        //封装Result对象，并且将文件的byte数组放置到result对象中
        FileUploadResult fileUploadResult = new FileUploadResult();
        if (!isLegal) {
            fileUploadResult.setStatus("error");
            return fileUploadResult;
        }
        //文件新路径
        String fileName = uploadFile.getOriginalFilename();
        String filePath = getFilePath(fileName);
        // 上传到阿里云
        try {
            ossClient.putObject(aliyunConfig.getBucketName(), filePath, new
                    ByteArrayInputStream(uploadFile.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            //上传失败
            fileUploadResult.setStatus("error");
            return fileUploadResult;
        }
        fileUploadResult.setStatus("done");
        fileUploadResult.setResponse("success");
        //this.aliyunConfig.getUrlPrefix() + filePath 文件路径需要保存到数据库
        fileUploadResult.setName(this.aliyunConfig.getUrlPrefix() + filePath);
        fileUploadResult.setUid(String.valueOf(System.currentTimeMillis()));
        return fileUploadResult;
    }

    /**
     * @author lastwhisper
     * @desc 生成路径以及文件名 例如：//images/2019/04/28/15564277465972939.jpg
     * @email gaojun56@163.com
     */
    private String getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        return "images/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }

    /**
     * @author lastwhisper
     * @desc 查看文件列表
     * 文档链接 https://help.aliyun.com/document_detail/84841.html?spm=a2c4g.11186623.2.13.3ad5b5ddqxWWRu#concept-84841-zh
     * @email gaojun56@163.com
     */
    public List<OSSObjectSummary> list() {
        // 设置最大个数。
        final int maxKeys = 200;
        // 列举文件。
        ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(aliyunConfig.getBucketName()).withMaxKeys(maxKeys));
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        return sums;
    }

    /**
     * @author lastwhisper
     * @desc 删除文件
     * 文档链接 https://help.aliyun.com/document_detail/84842.html?spm=a2c4g.11186623.6.770.4f9474b4UYlCtr
     * @email gaojun56@163.com
     */
    public FileUploadResult delete(String objectName) {
        // 根据BucketName,objectName删除文件
        ossClient.deleteObject(aliyunConfig.getBucketName(), objectName);
        FileUploadResult fileUploadResult = new FileUploadResult();
        fileUploadResult.setName(objectName);
        fileUploadResult.setStatus("removed");
        fileUploadResult.setResponse("success");
        return fileUploadResult;
    }

    /**
     * @author lastwhisper
     * @desc 下载文件
     * 文档链接 https://help.aliyun.com/document_detail/84823.html?spm=a2c4g.11186623.2.7.37836e84ZIuZaC#concept-84823-zh
     * @email gaojun56@163.com
     */
    public void exportOssFile(OutputStream os, String objectName) throws IOException {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(aliyunConfig.getBucketName(), objectName);
        // 读取文件内容。
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int lenght = 0;
        while ((lenght = in.read(buffer)) != -1) {
            out.write(buffer, 0, lenght);
        }
        if (out != null) {
            out.flush();
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }
}

```
### 4.2.3 controller

controller进行接收用户请求

```java
package com.example.ossdemo.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.example.ossdemo.service.FileUploadService;
import com.example.ossdemo.vo.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author lastwhisper
 * @desc
 * @email gaojun56@163.com
 */
@Controller
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * @author lastwhisper
     * @desc 文件上传到oss
     * @return FileUploadResult
     * @Param uploadFile
     */
    @RequestMapping("file/upload")
    @ResponseBody
    public FileUploadResult upload(@RequestParam("file") MultipartFile uploadFile)
            throws Exception {
        return this.fileUploadService.upload(uploadFile);
    }

    /**
     * @return FileUploadResult
     * @desc 根据文件名删除oss上的文件
     * http://localhost:8080/file/delete?fileName=images/2019/04/28/1556429167175766.jpg
     * @author lastwhisper
     * @Param objectName
     */
    @RequestMapping("file/delete")
    @ResponseBody
    public FileUploadResult delete(@RequestParam("fileName") String objectName)
            throws Exception {
        return this.fileUploadService.delete(objectName);
    }

    /**
     * @author lastwhisper
     * @desc 查询oss上的所有文件
     * http://localhost:8080/file/list
     * @return List<OSSObjectSummary>
     * @Param
     */
    @RequestMapping("file/list")
    @ResponseBody
    public List<OSSObjectSummary> list()
            throws Exception {
        return this.fileUploadService.list();
    }

    /**
     * @author lastwhisper
     * @desc 根据文件名下载oss上的文件
     * @return
     * @Param objectName
     */
    @RequestMapping("file/download")
    @ResponseBody
    public void download(@RequestParam("fileName") String objectName, HttpServletResponse response) throws IOException {
        //通知浏览器以附件形式下载
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(objectName.getBytes(), "ISO-8859-1"));
        this.fileUploadService.exportOssFile(response.getOutputStream(),objectName);
    }
}
```


# 5. 前端页面编写与测试

## 5.1 文件上传页面

使用ajax异步文件上传到后端对接的OSS上。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title>oss文件上传</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.11.2/jquery.js"></script>
    <script>
        function uploadfile() {
            $("#fileTypeError").html('');
            //获得文件名称
            var fileName = $('#file_upload').val();
            //截取文件类型,如(.jpg)　　　　　　　　　　　　　　　　
            var fileType = fileName.substr(fileName.length - 4, fileName.length);
            if (fileType == '.bmp' || fileType == '.jpg' || fileType == '.jpeg' || fileType == '.gif' || fileType == '.png') {　　　　　//验证文件类型,此处验证也可使用正则
                $.ajax({
                    url: 'file/upload',//上传地址
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#uploadForm')[0]),//表单数据
                    processData: false,
                    contentType: false,
                    success: function (rtn) {
                        if (rtn.status == 'error') {
                            $("#fileTypeError").html('*上传文件类型错误,支持类型:  .bmp .jpg .jpeg .gif .png');　　//根据后端返回值,回显错误信息
                        } else {
                            $('div').append('<img src="' + rtn.name + '" style="width: 300px;height: 300px"></img>')
                        }
                    }
                });
            } else {
                $("#fileTypeError").html('*上传文件类型错误,支持类型: .bmp .jpg .jpeg .gif .png');
            }
        }
    </script>
</head>
<body>
<form id="uploadForm" enctype="multipart/form-data">　　<!-- 声明文件上传 -->
    <input id="file_upload" type="file" name="file"/>　　<!-- 定义change事件,选择文件后触发 -->
    <br/><span style="color: red" id="fileTypeError"></span>　　　　<!-- 文件类型错误回显,此处通过前后端两次验证文件类型 -->
    <br/><input type="button" onclick="uploadfile()" value="上传">
</form>
<div></div>
</body>
</html>
```

**效果展示：**

![ajax文件上传到OSS](https://upload-images.jianshu.io/upload_images/5336514-9ce7586f3cff4448.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![ajax文件上传到OSS](https://upload-images.jianshu.io/upload_images/5336514-8979815659db74f7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![ajax文件上传到OSS成功](https://upload-images.jianshu.io/upload_images/5336514-74764aaef98c5634.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 5.2 文件管理页面

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>oss文件管理</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.11.2/jquery.js"></script>
    <script type="text/javascript">
        var pre = 'https://gaojun-testbucket.oss-cn-shanghai.aliyuncs.com/';
        $(function () {
            listfile();
        });

        //文件列表
        function listfile() {
            $.ajax({
                url: "http://localhost:8080/file/list",
                type: 'POST',
                success: function (rtn) {
                    console.log(rtn.length);
                    for (var i = 0; i < rtn.length; i++) {
                        $('div').append('<img src="' + pre + rtn[i].key + '" style="width: 300px;height: 300px; padding: 10px" ondblclick="deletefile(this.src)" onclick="downloadfile(this.src)"></img>')
                    }
                }
            });
        }

        //文件下载
        function downloadfile(src) {

            var fileName = src.split(pre)[1];
            window.location.href = "http://localhost:8080/file/download?fileName=" + fileName;
        }

        //文件删除
        function deletefile(src) {
            var fileName = src.split(pre)[1];
            var param = {fileName: fileName};
            $.ajax({
                url: "http://localhost:8080/file/delete",
                data: param,
                success: function () {
                    alert('删除成功',fileName);
                    //删除页面
                    location.reload();
                }
            });
        }

    </script>
</head>
<body>
单击下载oss上的图片、双击删除oss上的图片<br>
<div>

</div>
</body>
</html>
```

**效果展示：**

刚才上传了一张照片，可以立马看到

![文件管理](https://upload-images.jianshu.io/upload_images/5336514-62efcdedbb874287.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

单击页面即可下载图片

![image.png](https://upload-images.jianshu.io/upload_images/5336514-fa9b54c11e9a7ba4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

双击即可删除图片：

![删除图片](https://upload-images.jianshu.io/upload_images/5336514-a69777b462e3b919.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![删除图片](https://upload-images.jianshu.io/upload_images/5336514-670a3aa6e65cfb9e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)