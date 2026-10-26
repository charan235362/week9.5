import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrates connecting to a MySQL database using JDBC,
 * executing a SELECT query, and displaying the results.
 */
public class JDBCExample {

    // Step 2: Constants for JDBC URL, username and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password"; // change to your MySQL password

    public static void main(String[] args) {

        String sqlQuery = "SELECT * FROM users";

        // Step 3: try-with-resources for automatic closing of resources
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            System.out.println("Connected to the database successfully.\n");

            // Step 7: Loop through the ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");

                System.out.println("User ID: " + id
                        + ", Username: " + username
                        + ", Email: " + email);
            }

        } catch (SQLException e) {
            // Step 8: Handle SQLException
            System.err.println("Database error occurred!");
            System.err.println("Message   : " + e.getMessage());
            System.err.println("SQL State : " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
        }
        // Step 9: Resources are closed automatically
    }
}

/*
 * ---------------------------------------------------------------
 * DATABASE SETUP (run in MySQL before executing the program)
 * ---------------------------------------------------------------
 * CREATE DATABASE mydatabase;
 * USE mydatabase;
 *
 * CREATE TABLE users (
 *     id INT PRIMARY KEY AUTO_INCREMENT,
 *     username VARCHAR(50) NOT NULL,
 *     email VARCHAR(100) NOT NULL
 * );
 *
 * INSERT INTO users (username, email) VALUES
 *     ('johndoe', 'johndoe@example.com'),
 *     ('janedoe', 'janedoe@example.com'),
 *     ('alice',   'alice@example.com');
 *
 * ---------------------------------------------------------------
 * COMPILE & RUN
 * ---------------------------------------------------------------
 * javac JDBCExample.java
 * java -cp ".;mysql-connector-java-8.0.33.jar" JDBCExample     (Windows)
 * java -cp ".:mysql-connector-java-8.0.33.jar" JDBCExample     (Linux/Mac)
 *
 * ---------------------------------------------------------------
 * EXPECTED OUTPUT
 * ---------------------------------------------------------------
 * Connected to the database successfully.
 *
 * User ID: 1, Username: johndoe, Email: johndoe@example.com
 * User ID: 2, Username: janedoe, Email: janedoe@example.com
 * User ID: 3, Username: alice, Email: alice@example.com
 */
