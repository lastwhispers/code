package cn.lastwhisper.feature5.enums;

/**
 * 实现枚举功能
 * @author lastwhisper
 */
public class WeekDay {
    // 周一至周日
    public static final WeekDay Monday = new WeekDay();
    public static final WeekDay Tuesday = new WeekDay();
    public static final WeekDay Wednesday = new WeekDay();
    public static final WeekDay Thursday = new WeekDay();
    public static final WeekDay Friday = new WeekDay();
    public static final WeekDay Saturday = new WeekDay();
    public static final WeekDay Sunday = new WeekDay();

    // 私有构造器，禁止创建对象
    private WeekDay() {
    }

    public WeekDay nextDay() {
        if (this == Monday) {
            return Tuesday;
        } else if (this == Tuesday) {
            return Wednesday;
        } else if (this == Wednesday) {
            return Thursday;
        } else if (this == Thursday) {
            return Friday;
        } else if (this == Friday) {
            return Saturday;
        } else if (this == Saturday) {
            return Sunday;
        } else {
            return Sunday;
        }
    }

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
