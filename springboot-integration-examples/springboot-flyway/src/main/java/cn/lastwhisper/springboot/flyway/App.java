package cn.lastwhisper.springboot.flyway;

import org.flywaydb.core.Flyway;

public class App {
    public static void main(String[] args) {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:3306/flyway", "root", "root").load();

        // Start the migration
        flyway.migrate();
    }
}