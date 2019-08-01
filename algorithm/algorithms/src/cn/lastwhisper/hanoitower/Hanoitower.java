package cn.lastwhisper.hanoitower;

/**
 * 汉诺塔问题（递归）
 * @author lastwhisper
 */
public class Hanoitower {
    public static void main(String[] args) {
        move(3, 'A', 'B', 'C');
    }

    /**
     *
     *
     * @param n 盘子的数量
     * @param a 起始位置
     * @param b 中间缓存位置
     * @param c 终点位置
     * @return void
     */
    public static void move(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.printf("第1个盘从 %s->%s\n", a, c);
        } else {
            // 以a为起始位置，c为中间缓存位置，b为终点位置
            move(n - 1, a, c, b);
            System.out.printf("第%d个盘从 %s->%s\n", n, a, c);
            // 以b为起始位置，a为中间缓存位置，c为终点位置
            move(n - 1, b, a, c);
        }
    }

}
