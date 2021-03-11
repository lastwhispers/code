package cn.cunchang.fortry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cunchang
 * @date 2020/12/29 3:06 下午
 */
public class ForTryFinallyTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        for (Integer num : list) {
            try {
                if (num == 2) {
                    continue;
                }
                if (num == 3) {
                    break;
                }
                System.out.println("正常执行 " + num);
            } finally {
                if (num == 2) {
                    System.out.println("for循环,try里面continue会执行finally " + num);
                }
                if (num == 3) {
                    System.out.println("for循环,try里面break会执行finally " + num);
                }
            }
        }
    }


}
