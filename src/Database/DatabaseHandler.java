package Database;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DatabaseHandler {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "vedgkr";

    public static void insertUser(User user) {
        try (Connection conn = getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO users (user_id, name, email, password) VALUES (user_id.nextval, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public static User getUserByUsername(String username) {
            // SQL query to retrieve user information based on username
            String query = "SELECT * FROM users WHERE name = ?";

            try (Connection connection = getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                // Set the username parameter in the query
                statement.setString(1, username);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Check if a user with the provided username exists
                    if (resultSet.next()) {
                        // If a user is found, retrieve user information
                        int userId = resultSet.getInt("user_id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String password = resultSet.getString("password");

                        // Create a User object with the retrieved information
                        User user = new User( name, email, password);
                        return user;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Return null if no user with the provided username is found
            return null;
        }
}

