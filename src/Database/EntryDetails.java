package Database;

public class EntryDetails {
    private int entryId;
    private String entryType;
    private String date;
    private String category;
    private double amount;
    private String description;
    private String paymentMode;

    private static int nextEntryId = 0; // Static variable to track the next available entry ID

    // Constructor
    public EntryDetails(String entryType, String date, String category, double amount, String description, String paymentMode) {
        nextEntryId++; // Assign the next available entry ID and then increment the counter
        this.entryId =nextEntryId;
        this.entryType = entryType;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.paymentMode = paymentMode;
    }

    // Getters and setters for all fields

    public int getEntryId() {
        return entryId;
    }

    // No setter for entryId as it should be automatically generated

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return "EntryDetails{" +
                "entryId=" + entryId +
                ", entryType='" + entryType + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +

                ", paymentMode='" + paymentMode + '\'' +
                '}';
    }
}
