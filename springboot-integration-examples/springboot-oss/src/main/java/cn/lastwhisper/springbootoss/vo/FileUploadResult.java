package cn.lastwhisper.springbootoss.vo;

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
