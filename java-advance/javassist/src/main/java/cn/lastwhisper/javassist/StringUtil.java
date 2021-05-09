package cn.lastwhisper.javassist;

public class StringUtil {
    /**
     * 以添拼接的方式添加字符串
     */
    public String addString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (char) (i % 26 + 'a');
        }
        return result;
    }

    /**
     * 以追加的方式添加字符串
     */
    public String buildString(int length) {
        StringBuilder inst = new StringBuilder();
        for (int i = 0; i < length; i++) {
            inst.append((char) (i % 26 + 'a'));
        }
        return inst.toString();
    }

    public static void main(String[] args) {
        StringUtil util = new StringUtil();
        util.addString(1000);
		long begin = System.nanoTime();
		util.addString(10000);
		System.out.println(System.nanoTime() - begin);
		begin = System.nanoTime();
		util.buildString(10000);
		System.out.println(System.nanoTime() - begin);
    }
}
