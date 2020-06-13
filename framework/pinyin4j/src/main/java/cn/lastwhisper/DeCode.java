package cn.lastwhisper;

/**
 *
 * @author lastwhisper
 * @date 2020/6/8
 */
public class DeCode {

    public static void main(String[] args) {
        final String words = "原谅女儿离开父亲 昨日看到急报提示 " +
                             "阴祸氏突袭数据库 然后立刻离开城门 " +
                             "发现事情并不单纯 于是就跟随他来此 " +
                             "如果我没法再找寻 根据这追踪器书信 " +
                             "一定就会发现原因 ";
        String binary = PinYinUtil.word2Tonal(words);
        System.out.println(PinYinUtil.binary2Ascii(binary));
    }

}
