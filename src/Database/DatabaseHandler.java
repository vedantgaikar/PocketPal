package Database;

import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class DatabaseHandler {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "vedgkr";
    private static Integer entryId = initializeEntryId();
    public static Integer initializeEntryId() {
        // SQL query to retrieve the maximum entry ID from the entry_details table
        String query = "SELECT max(entry_id) AS max_id FROM entry_details";

        try (Connection connection = getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Execute the query
            try (ResultSet rs = statement.executeQuery(query)) {
                // Check if the result set has any rows
                if (rs.next()) {
                    // Retrieve the maximum entry ID from the result set
                    int maxId = rs.getInt("max_id");

                    // Return the maximum entry ID incremented by 1
                    return maxId + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return -1 if an error occurs or if no entry ID is found
        return -1;
    }



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
    // Method to insert an entry into the database
    public static void insertEntry(EntryDetails entry, User user) {
        try (Connection conn = getConnection(URL, USERNAME, PASSWORD)) {

            int expenseFlag = 0;
            int incomeFlag = 0;
            // SQL query to insert entry details into the database
            String sql = "INSERT INTO entry_details (entry_id, user_id, entry_type, entry_date, category, amount, description, payment_mode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";



            entryId++;
            // Create a prepared statement with the SQL query
            PreparedStatement statement = conn.prepareStatement(sql);
            // Set values for the prepared statement parameters
            statement.setInt(1, entryId);
            statement.setInt(2, user.getUserId()); // Assuming 'getId()' returns the user's ID
            statement.setString(3, entry.getEntryType());
            statement.setString(4, entry.getDate());
            statement.setString(5, entry.getCategory());
            statement.setDouble(6, entry.getAmount());
            statement.setString(7, entry.getDescription());
            statement.setString(8, entry.getPaymentMode());

            // Execute the query to insert the entry into the database
            statement.executeUpdate();
            if(entry.getEntryType()=="Expense")
            {
                String query = "";
                try {
                    query = "UPDATE pocket_money SET amount = (amount - ?) WHERE user_id = ?";
                    PreparedStatement Query = conn.prepareStatement(query);
                    Query.setDouble(1, entry.getAmount());
                    Query.setInt(2,user.getUserId());
                    Query.executeUpdate();
                } catch (SQLException e)
                {
                    e.printStackTrace();

                }

            }
            else {
                String query = "";
                try {
                    if(incomeFlag==0) { // insert amount for first time
                        query = "INSERT INTO pocket_money VALUES (?, ?)";
                        PreparedStatement Query = conn.prepareStatement(query);
                        Query.setInt(1,user.getUserId());
                        Query.setDouble(2, entry.getAmount());
                        Query.executeUpdate();
                        expenseFlag=1;

                    }
                    else { // update amount if already filled
                        query = "UPDATE pocket_money SET amount = (amount + ?) WHERE user_id = ?";
                        PreparedStatement Query = conn.prepareStatement(query);
                        Query.setDouble(1, entry.getAmount());
                        Query.setInt(2,user.getUserId());
                        Query.executeUpdate();
                    }
                } catch (SQLException e)
                {
                    e.printStackTrace();

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // method to fetch details of entry by ID
    public static EntryDetails getEntryDetailsById(int entryId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EntryDetails entryDetails = null;

        try {

            conn = getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM entry_details WHERE entry_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, entryId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Retrieve entry details from the result set
                int entry_id = rs.getInt("entry_id");
                int user_id = rs.getInt("user_id");
                String entryType = rs.getString("entry_type");
                String date = rs.getString("entry_date");
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                String paymentMode = rs.getString("payment_mode");
                // Create EntryDetails object
                entryDetails = new EntryDetails(entryType, date, category, amount, description, paymentMode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return entryDetails;
    }
    public static boolean deleteEntry(int entryId) {
        try (Connection conn = getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "DELETE FROM entry_details WHERE entry_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, entryId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ResultSet getAllEntries(int userId) throws SQLException {
        ResultSet resultSet = null;
        Connection connection = getConnection(URL, USERNAME, PASSWORD);
        String query = "SELECT * FROM entry_details WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        resultSet = statement.executeQuery();

        // Do not attempt to access the result set here, as the connection is already closed
        return resultSet;
    }

    public static double getPocketMoneyForUser(int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double pocketMoney = 0.0;

        try {
            connection = getConnection(URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM pocket_money WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pocketMoney = resultSet.getDouble("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return pocketMoney;
    }
    public static List<EntryDetails> getExpensesForUser(int userId) throws SQLException {
        List<EntryDetails> expenses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM entry_details WHERE user_id = ? AND entry_type = 'Expense'";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String entryType = resultSet.getString("entry_type");
                String date = resultSet.getString("entry_date");
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                String paymentMode = resultSet.getString("payment_mode");

                EntryDetails expense = new EntryDetails(entryType, date, category, amount, description, paymentMode);
                expenses.add(expense);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return expenses;
    }

    public static double getHighestExpenseForUser(int userId) throws SQLException {
        List<EntryDetails> expenses = getExpensesForUser(userId);
        double highestExpense = 0.0;

        for (EntryDetails expense : expenses) {
            double amount = expense.getAmount();
            if (amount > highestExpense) {
                highestExpense = amount;
            }
        }

        return highestExpense;
    }

    public static double getLowestExpenseForUser(int userId) throws SQLException {
        List<EntryDetails> expenses = getExpensesForUser(userId);
        double lowestExpense = Double.MAX_VALUE;

        for (EntryDetails expense : expenses) {
            double amount = expense.getAmount();
            if (amount < lowestExpense) {
                lowestExpense = amount;
            }
        }

        return lowestExpense;
    }


}


