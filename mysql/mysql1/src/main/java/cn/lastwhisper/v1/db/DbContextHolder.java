package cn.lastwhisper.db;

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
        return datasource.get();
    }

}
