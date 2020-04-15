package cn.lastwhisper.feature5.classloader.encrypt;

import java.util.Date;

/**
 * 继承Date是有原因的！！！自己定义的类加载器还原字节码后，创建的对象由Date变量接收。
 * @author lastwhisper
 */
public class ClassLoaderAttachment extends Date {
    @Override
    public String toString() {
        return "Hello Date toString";
    }
}
