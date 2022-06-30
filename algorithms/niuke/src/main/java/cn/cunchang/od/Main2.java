package cn.cunchang.od;


import java.util.*;

public class Main2 {
    // 映射与反映射 j=11，q=12，k=13，a=14
    static Map<String, Integer> pokMap = new HashMap<>();
    static Map<Integer, String> codeMap = new HashMap<>();

    static {
        pokMap.put("3", 0);
        pokMap.put("4", 1);
        pokMap.put("5", 2);
        pokMap.put("6", 3);
        pokMap.put("7", 4);
        pokMap.put("8", 5);
        pokMap.put("9", 6);
        pokMap.put("10", 7);
        pokMap.put("J", 8);
        pokMap.put("Q", 9);
        pokMap.put("K", 10);
        pokMap.put("A", 11);

        codeMap.put(0, "3");
        codeMap.put(1, "4");
        codeMap.put(2, "5");
        codeMap.put(3, "6");
        codeMap.put(4, "7");
        codeMap.put(5, "8");
        codeMap.put(6, "9");
        codeMap.put(7, "10");
        codeMap.put(8, "J");
        codeMap.put(9, "Q");
        codeMap.put(10, "K");
        codeMap.put(11, "A");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String my = sc.nextLine();//我的手牌
        String show = sc.nextLine();//别人打了的

        // 顺子【3,14】不能有 2、大小王

        // 统计my和show里面的牌频率
        String[] mySplit = my.split("-");
        String[] showSplit = show.split("-");

        int[] freqs = new int[12];//12种可以构成顺子的牌
        for (String myPok : mySplit) {
            Integer pokCode = pokMap.get(myPok);
            freqs[pokCode]++;
        }
        for (String showPok : showSplit) {
            Integer pokCode = pokMap.get(showPok);
            freqs[pokCode]++;
        }
        // 找到四张牌
        List<Integer> fourIdxList = new ArrayList<>();
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] == 4) {
                fourIdxList.add(i);
            }
        }

        fourIdxList.add(12);//保证结尾扑克也会被扫到，偷懒写法

        List<KV> orderList = new ArrayList<>();

        // 顺子12张：0,1,2,3,4,5,6,7,8,9,10,11
        int preIdx = 0;
        int orderMax = 0;//顺子最大个数

        // 构成顺子的
        for (Integer fourIdx : fourIdxList) {
            // 0~fourIdx-1；fourIdx+1~fourIdx-1；
            int subOrderCount = fourIdx - preIdx;
            if (subOrderCount >= 5) {//构成顺子
                orderList.add(new KV(preIdx, fourIdx - 1));
                orderMax = Math.max(subOrderCount, orderMax);
            }

            preIdx = fourIdx + 1;
        }

        // 找到四张牌，在【3,14】的位置，没有直接返回【3,A】

//        if (fourIdxList.isEmpty()) {
//            orderList.add(new KV(0, 11));
//            orderMax = 11;
//        }

        int start = -1, end = -1;
        for (KV kv : orderList) {
            if (kv.v - kv.k == orderMax-1) {//减1是因为这里的kv是两个闭区间
                start = kv.k;
                end = kv.v;
            }
        }
        if (start == -1) {
            System.out.println("NO-CHAIN");
        } else {
            for (int i = start; i <= end; i++) {
                if (i == end) {
                    System.out.print(codeMap.get(i));
                } else {
                    System.out.print(codeMap.get(i) + "-");
                }
            }
        }
    }

    static class KV {
        public int k;
        public int v;

        public KV(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }
}