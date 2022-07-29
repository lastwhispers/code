package cn.cunchang;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author cunchang
 * @date 2022/7/6 8:12 PM
 */
public class Main {

    /**
     * 有n个人围成一圈，顺序排号。
     * 从第1个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位，起始索引位置为0。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }
        int idx = 0;
        int count = 0;
        while (list.size() >1) {
            if (count == 2) {
                list.remove(idx);
                idx=-1;
                count=0;
            }
            idx++;
            count++;
        }

        // 还剩两个人
        System.out.println(list);
    }

}
