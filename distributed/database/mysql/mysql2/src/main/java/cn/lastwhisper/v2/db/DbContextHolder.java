package cn.lastwhisper.v2.db;

/**
 * @author lastwhisper
 */
public class DbContextHolder {

    public static final String MASTER = "master";
    public static final String SLAVE = "slave";

    private static ThreadLocal<String> datasource = new ThreadLocal<>();

    public static void set(String name) {
        datasource.set(name);
    }

    public static String get() {
        String source = datasource.get() == null ? DbContextHolder.MASTER : datasource.get();
        return source;
    }

    // 清空数据类型
    public static void clear() {
        datasource.remove();
    }
}
