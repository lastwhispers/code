package cn.cunchang.hutool;

import cn.hutool.core.util.ZipUtil;

import java.io.File;

/**
 * 解压文件
 * @author cunchang
 * @date 2022/5/9 2:51 PM
 */
public class ZipUtilTest {

    public static void main(String[] args) {
        //将test.zip解压到e:\\aaa目录下，返回解压到的目录
        File unzip = ZipUtil.unzip("/Users/cunchang/Downloads/1.zip", "/Users/cunchang/Downloads/receipt");
        System.out.println(unzip.getAbsoluteFile());
        String[] filePaths = unzip.list();
        for (String filePath : filePaths) {
            System.out.println(unzip.getAbsoluteFile()+"/"+filePath);
        }
    }

}
