public enum ExpenseCategory {
    VENUE("Venue"),
    EQUIPMENT("Equipment"),
    DECOR("Decor"),
    CATERING("Catering"),
    TRANSPORTATION("Transportation"),
    STAFF("Staff"),
    MISCELLANEOUS("Miscellaneous");

    private final String displayName;

    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}