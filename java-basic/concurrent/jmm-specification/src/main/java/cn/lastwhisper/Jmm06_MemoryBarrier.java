package cn.lastwhisper;

/**
 * ,;,,;
 * ,;;'(    社
 * __      ,;;' ' \   会
 * /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 * ,;' \    /-.,,(   ) \    码
 * ) /       ) / )|    农
 * ||        ||  \)
 * (_\       (_\
 *
 * 
 * @date ：Created in 2020/4/29
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class Jmm06_MemoryBarrier {
    int a;
    public volatile int m1 = 1;
    public volatile int m2 = 2;

    public void readAndWrite() {
        int i = m1;   // 第一个volatile读
        int j = m2;   // 第二个volatile读

        a = i + j;    // i,j普通读，a普通写

        m1 = i + 1;   // 第一个volatile写
        m2 = j * 2;   // 第二个 volatile写
    }

}
