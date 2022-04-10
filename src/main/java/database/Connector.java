package database;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

@Data
public class Connector {

    private Connection connection;

    @SneakyThrows
    public Connector() {
        Dotenv dotenv = Dotenv.load();

        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(dotenv.get("URL"),
                dotenv.get("USER"), dotenv.get("PW"));
    }
}
