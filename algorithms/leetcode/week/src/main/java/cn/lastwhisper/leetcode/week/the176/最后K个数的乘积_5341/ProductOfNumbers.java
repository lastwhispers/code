package cn.lastwhisper.leetcode.week.the176.最后K个数的乘积_5341;

import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {

    private List<Integer> list = new ArrayList<>();

    public ProductOfNumbers() {


    }

    public void add(int num) {
        list.add(num);
    }

    public int getProduct(int k) {
        int count = 1;
        for (int i = list.size() - k; i < list.size(); i++) {
            count *= list.get(i);
        }
        return count;
    }

}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */