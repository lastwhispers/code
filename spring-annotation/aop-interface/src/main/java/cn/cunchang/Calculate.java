package cn.cunchang;

/**
 * 计算类接口
 */
public interface Calculate {

    /**
     * 加法
     *
     * @param numA
     * @param numB
     * @return
     */
    int add(int numA, int numB);

    /**
     * 减法
     *
     * @param numA
     * @param numB
     * @return
     */
    int sub(int numA, int numB);

    /**
     * 除法
     *
     * @param numA
     * @param numB
     * @return
     */
    int div(int numA, int numB);

    /**
     * 乘法
     *
     * @param numA
     * @param numB
     * @return
     */
    int multi(int numA, int numB);

    int mod(int numA, int numB);
}