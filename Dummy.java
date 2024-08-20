import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class PostgreSQLConnection {

    // Assuming these values are correctly loaded from application properties
    private static String name;
    private final static String connectionUrl;
    private final static String connectionUser;
    private final static String connectionCred;

    public static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        Properties properties = new Properties();

        try (InputStream input = PostgreSQLConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Incorrect resource loading.");
                return null;
            }

            properties.load(input);

            String url = properties.getProperty("spring.datasource.url");
            String user = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Postgres");

        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to establish connection to PostgreSQL.", e);
        }

        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Postgres connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
