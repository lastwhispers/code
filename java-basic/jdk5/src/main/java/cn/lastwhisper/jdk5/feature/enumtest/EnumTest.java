package cn.lastwhisper.jdk5.feature.enumtest;

import java.util.Arrays;

/**
 * jdk5新特性：枚举类型
 * @author lastwhisper
 */
public class EnumTest {

    public enum WeekDay2 {
        Monday(1), Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;

        /**
         * 枚举类构造器：
         *  1.必须放到枚举属性之后（所有属性、方法都要放到枚举属性之后）
         *  2.必须是私有构造器
         *  3.默认调用无参构造，也可以显示在枚举属性上调用
         */
        private WeekDay2() {
            System.out.println("无参构造");
        }

        private WeekDay2(int day) {
            System.out.println("有参构造，参数：" + day);
        }
    }

    public enum TrafficLamp {
        // 信号灯枚举属性
        Green(40) {
            @Override
            public TrafficLamp nextLamp() {
                return Yellow;
            }
        }, Yellow(5) {
            @Override
            public TrafficLamp nextLamp() {
                return Red;
            }
        }, Red(30) {
            @Override
            public TrafficLamp nextLamp() {
                return Green;
            }
        };

        private int time;

        private TrafficLamp(int time) {
            this.time = time;
        }

        public abstract TrafficLamp nextLamp();
    }

    public static void main(String[] args) {
        // 枚举类型：编译时必须使用规定的值

        // 自己实现枚举类的功能
        //WeekDay monday = WeekDay.Monday;
        //WeekDay tuesday = monday.nextDay();
        //System.out.println(tuesday.toString());

        // 使用抽象方法将nextDay中的if.else语句转为一个个独立的类
        //WeekDay1 monday = WeekDay1.Monday;
        //WeekDay1 thesday = monday.nextDay();
        //System.out.println(thesday.toString());

        // 使用枚举类
        WeekDay2 monday = WeekDay2.Monday;
        System.out.println(monday);
        System.out.println(monday.name());
        System.out.println(monday.ordinal());// 下标
        System.out.println(WeekDay2.valueOf("Thursday"));// 静态方法
        System.out.println(Arrays.toString(WeekDay2.values()));// 静态方法

    }
}
