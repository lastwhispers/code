package cn.lastwhisper.elasticsearch.operation.enums;

public enum DbConfig {
    DB_DRIVER("com.mysql.jdbc.Driver"),
    DB_URL("jdbc:mysql://127.0.0.1:3306/qingcheng_goods?useUnicode=true&characterEncoding=utf8&useSSL=true"),
    DB_USERNAME("root"),
    DB_PASSWORD("root"),

    ES_HOSTNAME("127.0.0.1"),
    ES_SCHEMA("http"),
    ;

    String value;

    DbConfig(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}