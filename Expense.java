public class Expense {
    int id;
    ExpenseCategory category;
    double amount;
    String date;
    int eventId;

    public Expense(int id, ExpenseCategory category, double amount, String date, int eventId) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return String.format("Expense [ID=%d, Category='%s', Amount=₹%.2f, Date=%s, EventID=%d]",
                id, category, amount, date, eventId);
    }
}
