package cn.cunchang.od;


import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int xmH = sc.nextInt();//小明身高
        int N = sc.nextInt();// 同学数量

        // 同学身高
        List<Integer> hList = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            hList.add(sc.nextInt());
        }
        // 计算各个同学的身高差，按差升序，放到TreeMapList<差,同学身高list>
        Map<Integer, List<Integer>> diffSortMapList = new TreeMap<>();
        for (Integer height : hList) {
            int subDiff = Math.abs(xmH - height);
            List<Integer> someList = diffSortMapList.get(subDiff);
            if (someList == null || someList.isEmpty()) {
                someList = new ArrayList<>();
                diffSortMapList.put(subDiff, someList);
            }
            someList.add(height);
        }

        // 返回结果
        List<Integer> result = new ArrayList<>(N);

        // 对同学身高list局部排序
        for (Map.Entry<Integer, List<Integer>> entry : diffSortMapList.entrySet()) {
            List<Integer> someList = entry.getValue();
            Collections.sort(someList);
            result.addAll(someList);
        }
        for (int i = 0; i < result.size(); i++) {
            if (i == result.size() - 1) {
                System.out.print(result.get(i));
            } else {
                System.out.print(result.get(i) + " ");
            }
        }
    }

}