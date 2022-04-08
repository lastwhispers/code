package cn.lastwhisper;

/**
 * 
 * @date ：Created in 2020/7/3
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class Juc_LockAppend {

    StringBuffer stb = new StringBuffer();

    private void method(){
        stb.append("杨过");
        stb.append("小龙女");
        stb.append("大雕");
        stb.append("郭靖");
    }
}
