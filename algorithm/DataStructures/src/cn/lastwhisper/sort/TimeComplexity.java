package cn.lastwhisper.sort;

/**
 * 时间复杂度举例
 * @author lastwhisper
 */
public class TimeComplexity {
    
    public static void main(String[] args){
        new TimeComplexity().n2(5);
    }
    // O(1)
    public void o1() {
        int x = 1, s;
        for (int i = 0; i < 10000; i++) {
            x++;
            s = 0;
        }
    }

    // O(logn)
    public void logn(int n) {
        int i = 1;
        while (i < n) {
            i = i * 2;
        }
    }

    // O(n)
    public void n(int n) {
        int j = 0;
        for (int i = 0; i < n; i++) {
            j++;
        }
    }

    // O(nlogn)
    public void nlogn(int n) {
        for (int m = 0; m < n; m++) {
            int i = 1;
            while (i < n) {
                i = i * 2;
            }
        }
    }

    // O(n^2)
    public void n2(int n) {
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                k++;
            }
        }
    }


}
