package cn.lastwhisper.interview.OverloadVsOverride;

/**
 *  测试重载
 * @author lastwhisper
 */
public class OverLoadClass {

    public void overloadFun() {
        // 无参函数
    }

    public void overloadFun(Long id) {
        // 重载overloadFun，参数个数不同
    }

    public void overloadFun(Integer id) {
        // 重载overloadFun，参数类型不同
    }

    public void overloadFun(Long id, String name) {
        // 重载overloadFun，参数个数不同
    }

    public void overloadFun(String name, Long id) {
        // 重载overloadFun，参数顺序不同
    }

    //// 抛出异常不同，并不能构成函数重载
    //public void overloadFun() throws Exception{
    //}
    //
    //// 访问修饰符不同，并不能构成函数重载
    //private void overloadFun(Integer id) {
    //}
    //
    //// 返回值不同，并不能构成函数重载
    //public int overloadFun(){
    //    return 0;
    //}

}

