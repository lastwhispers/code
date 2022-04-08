package cn.lastwhisper;

/**
 *                  ,;,,;
 *                ,;;'(    社
 *      __      ,;;' ' \   会
 *   /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 *,;' \    /-.,,(   ) \    码
 *     ) /       ) / )|    农
 *     ||        ||  \)     
 *     (_\       (_\
 * 
 * @date ：Created in 2020/4/29 14:00
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 
 **/
public class Jmm02_CpuCache {
    private static final int RUNS = 10;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 6;

    private static long[][] longs;

    public static void main(String[] args) throws Exception {
        /*
         * 初始化数组
         */
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 1L;
            }
        }
        System.out.println("初始化完毕....");

        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int i = 0; i < DIMENSION_1; i++) {//DIMENSION_1=1024*1024
                for (int j=0;j<DIMENSION_2;j++){//6
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("spend time1:"+(System.currentTimeMillis()-start));
        System.out.println("sum1:"+sum);

        sum = 0L;
        start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int j=0;j<DIMENSION_2;j++) {//6
                for (int i = 0; i < DIMENSION_1; i++){//1024*1024
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("spend time2:"+(System.currentTimeMillis()-start));
        System.out.println("sum2:"+sum);

    }
}
