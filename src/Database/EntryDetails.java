package Database;

public class EntryDetails {
    private String entryType;
    private String date;
    private String category;
    private double amount;
    private String description;
    private String time;
    private String paymentMode;

    public EntryDetails(String entryType, String date, String category, double amount, String description, String time, String paymentMode) {
        this.entryType = entryType;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.time = time;
        this.paymentMode = paymentMode;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
                "entryType='" + entryType + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", time='" + time + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                '}';
    }
}
