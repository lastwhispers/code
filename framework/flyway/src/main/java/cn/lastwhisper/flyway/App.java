package cn.lastwhisper.flyway;

import org.flywaydb.core.Flyway;

public class App {
    public static void main(String[] args) {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource(
                "jdbc:mysql://localhost:3306/application?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8",
                "root", "root").load();

        // Start the migration
        // 默认 flyway.locations=classpath:/db/migration
        flyway.migrate();
    }
}