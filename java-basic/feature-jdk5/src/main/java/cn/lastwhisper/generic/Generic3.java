package cn.lastwhisper.generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 获取变量泛型类型
 * @author lastwhisper
 */
public class Generic3 {

    private List<Date> dates = new ArrayList<Date>();

    // 通过变量本身是无法知道自身的泛型是啥，因为泛型在编译过后被擦除了。
    // 可以通过方法获取参数的泛型
    //https://www.cnblogs.com/homeword/p/7460594.html
    public static void main(String[] args) throws Exception {
        // 1.获取方法
        Method method = Generic3.class.getDeclaredMethod("showDate", List.class);
        // 2.获取带泛型的参数类型 java.util.List<java.util.Date>
        Type[] types = method.getGenericParameterTypes();
        // 3.获取ParameterizedType
        ParameterizedType pType = (ParameterizedType) types[0];
        // 4.获取实际类型参数
        System.out.println(((Class) (pType.getActualTypeArguments()[0])).getName());

        //System.out.println("showDate("
        //        + ((Class) pType.getRawType()).getName() + "<"
        //        + ((Class) (pType.getActualTypeArguments()[0])).getName()
        //        + ">)" );
    }

    public void showDate(List<Date> dates) {
        for (Date date : dates) {
            System.out.println(date);
        }
    }


}
