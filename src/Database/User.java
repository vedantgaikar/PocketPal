package Database;

public class User {
    private String name;
    private String email;
    private String password;
    private static int nextUserId = 1; // Static variable to track the next available user ID

    private int userId; // Instance variable to store the user ID

    // Constructor
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userId = nextUserId++; // Assign the next available user ID and then increment the counter
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getUserId() {
        return userId;
    }
}
