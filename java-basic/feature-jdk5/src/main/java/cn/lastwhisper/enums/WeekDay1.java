package cn.lastwhisper.enums;

/**
 * 实现枚举功能
 * @author lastwhisper
 */
public abstract class WeekDay1 {
    // 使用抽象方法将nextDay中的if.else语句转为一个个独立的类
    public static final WeekDay1 Monday = new WeekDay1() {
        @Override
        public WeekDay1 nextDay() {
            return Tuesday;
        }
    };
    public static final WeekDay1 Tuesday = new WeekDay1() {
        @Override
        public WeekDay1 nextDay() {
            return Wednesday;
        }
    };
    public static final WeekDay1 Wednesday = new WeekDay1() {
        @Override
        public WeekDay1 nextDay() {
            return Thursday;
        }
    };
    public static final WeekDay1 Thursday = new WeekDay1() {
        @Override
        public WeekDay1 nextDay() {
            return Friday;
        }
    };
    public static final WeekDay1 Friday = new WeekDay1() {
        @Override
        public WeekDay1 nextDay() {
            return Saturday;
        }
    };
    public static final WeekDay1 Saturday = new WeekDay1() {
        @Override
        public WeekDay1 nextDay() {
            return Sunday;
        }
    };
    public static final WeekDay1 Sunday = new WeekDay1() {
        @Override
        public WeekDay1 nextDay() {
            return this;
        }
    };

    // 私有构造器，禁止创建对象
    private WeekDay1() {
    }

    public abstract WeekDay1 nextDay();

    public String toString() {
        if (this == Monday) {
            return "Monday";
        } else if (this == Tuesday) {
            return "Tuesday";
        } else if (this == Wednesday) {
            return "Wednesday";
        } else if (this == Thursday) {
            return "Thursday";
        } else if (this == Friday) {
            return "Friday";
        } else if (this == Saturday) {
            return "Saturday";
        } else {
            return "Sunday";
        }
    }

}
