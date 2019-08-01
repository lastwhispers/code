package cn.lastwhisper.recursion;

/**
 * 递归通俗解释：
 *      1）、https://www.zhihu.com/question/20507130/answer/31826402
 *      坐在第几排——》坐在电影院里，你想知道自己坐在第几排，于是你问前面一排的A【你坐在那一排】，这样A的排数+1你就知道自己在第几排了。
 *      A也不知道自己坐在那一排，于是A问他前一排的B【你坐在那一排】，B也不知道自己坐在那一排，于是B...，一直问到第一排的人，
 *      递归结束，开始回溯，最后大家都知道自己坐在那一排了。
 *      2）、https://www.zhihu.com/question/20507130/answer/52759136
 *      查字典——》查一个词，发现这个词的解释中某个词仍不懂，于是查第二个词，第二个词的解释中某个词仍不懂，于是查第三个词
 *      ，直到有一个词你明白了，递归结束，开始回溯，懂了第三个词，懂了第二个词，懂了查的这个词。
 * @author lastwhisper
 */
public class Recursion {
    public static void main(String[] args) {
        System.out.println(fun(5));
    }

    public static int fun(int n) {
        if (n == 1) {
            return 1;
        } else {
            return fun(n - 1) * n;
        }
    }
}
