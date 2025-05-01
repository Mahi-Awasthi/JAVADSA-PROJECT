import java.text.SimpleDateFormat;
import java.util.*;

public class BookingManager {
    Scanner sc = new Scanner(System.in);
    LinkedList<Event> events = new LinkedList<>();
    LinkedList<Client> clients = new LinkedList<>();
    Queue<Booking> bookings = new LinkedList<>();
    private double totalBudget = 0;
    private LinkedList<Expense> expenses = new LinkedList<>();

    private boolean isFutureDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            Date inputDate = sdf.parse(dateStr);
            return inputDate.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Event CRUD
    public void addEvent() {
        System.out.print("Enter Event ID, Name, Date (dd-MM-yyyy), Venue: ");
        int id = sc.nextInt(); sc.nextLine();
        String name = sc.nextLine();
        String date = sc.nextLine();
        String venue = sc.nextLine();

        if (!isFutureDate(date)) {
            System.out.println("Error: Event date must be in the future.");
            return;
        }

        for (Event e : events) {
            if (e.date.equals(date) && e.venue.equalsIgnoreCase(venue)) {
                System.out.println("Error: Event already booked at this venue on this date.");
                return;
            }
        }

        events.add(new Event(id, name, date, venue));
        System.out.println("Event added successfully.");
    }

    public void viewEvents() {
        if (events.isEmpty()) System.out.println("No events available.");
        else events.forEach(System.out::println);
    }

    public void updateEvent() {
        System.out.print("Enter Event ID to update: ");
        int id = sc.nextInt(); sc.nextLine();
        for (Event e : events) {
            if (e.id == id) {
                System.out.print("New Name: "); e.name = sc.nextLine();
                System.out.print("New Date (dd-MM-yyyy): ");
                String newDate = sc.nextLine();
                if (!isFutureDate(newDate)) {
                    System.out.println("Error: Event date must be in the future.");
                    return;
                }
                e.date = newDate;
                System.out.print("New Venue: "); e.venue = sc.nextLine();
                System.out.println("Event updated.");
                return;
            }
        }
        System.out.println("Event not found.");
    }

    public void deleteEvent() {
        System.out.print("Enter Event ID to delete: ");
        int id = sc.nextInt();
        events.removeIf(e -> e.id == id);
        System.out.println("Event deleted if found.");
    }

    // Client CRUD
    public void addClient() {
        System.out.print("Enter Client ID, Name, Contact: ");
        int id = sc.nextInt(); sc.nextLine();
        String name = sc.nextLine();
        String contact = sc.nextLine();

        if (!contact.matches("\\d{10}")) {
            System.out.println("Error: Phone number must be exactly 10 digits.");
            return;
        }

        clients.add(new Client(id, name, contact));
        System.out.println("Client added successfully.");
    }

    public void viewClients() {
        if (clients.isEmpty()) System.out.println("No clients available.");
        else clients.forEach(System.out::println);
    }

    // Booking
    public void bookEvent() {
        System.out.print("Enter Event ID and Client ID: ");
        int eventId = sc.nextInt(), clientId = sc.nextInt();
        Event e = null; Client c = null;
        for (Event ev : events) if (ev.id == eventId) e = ev;
        for (Client cl : clients) if (cl.id == clientId) c = cl;
        if (e != null && c != null) {
            bookings.add(new Booking(e, c));
            System.out.println("Booking successful.");
        } else {
            System.out.println("Invalid Event or Client ID.");
        }
    }

    public void viewBookings() {
        if (bookings.isEmpty()) System.out.println("No bookings.");
        else bookings.forEach(System.out::println);
    }

    // Expense Management
    public void setBudget() {
        System.out.print("Enter total budget: ₹");
        totalBudget = sc.nextDouble();
        sc.nextLine();
        System.out.println("Budget set to: ₹" + totalBudget);
    }

    public void addExpense() {
        System.out.println("Available Events:");
        viewEvents();
        
        System.out.print("Enter Event ID: ");
        int eventId = sc.nextInt();
        sc.nextLine();

        if (events.stream().noneMatch(e -> e.id == eventId)) {
            System.out.println("Invalid Event ID!");
            return;
        }

        System.out.println("\nSelect Expense Category:");
        ExpenseCategory[] categories = ExpenseCategory.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%d. %s\n", i+1, categories[i]);
        }
        System.out.print("Enter category number: ");
        int categoryChoice = sc.nextInt();
        sc.nextLine();
        
        if (categoryChoice < 1 || categoryChoice > categories.length) {
            System.out.println("Invalid category selection!");
            return;
        }
        ExpenseCategory category = categories[categoryChoice - 1];

        System.out.print("Enter amount: ₹");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter date (dd-MM-yyyy): ");
        String date = sc.nextLine();
        if (!isFutureDate(date)) {
            System.out.println("Invalid date! Must be future date.");
            return;
        }

        expenses.add(new Expense(expenses.size()+1, category, amount, date, eventId));
        System.out.println("Expense added successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n=== Expense Report ===");
        System.out.printf("Total Budget: ₹%.2f\n", totalBudget);
        
        double totalSpent = expenses.stream()
            .mapToDouble(e -> e.amount)
            .sum();
        
        System.out.printf("Total Spent: ₹%.2f\n", totalSpent);
        System.out.printf("Remaining Budget: ₹%.2f\n\n", totalBudget - totalSpent);

        System.out.println("Expense Breakdown:");
        System.out.printf("%-5s %-15s %-10s %-12s %s\n", 
            "ID", "Category", "Amount", "Date", "Event ID");
        
        for (Expense e : expenses) {
            System.out.printf("%-5d %-15s ₹%-9.2f %-12s %d\n",
                e.id, e.category, e.amount, e.date, e.eventId);
        }
    }

    public void removeExpense() {
        System.out.print("Enter Expense ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        if (expenses.removeIf(e -> e.id == id)) {
            System.out.println("Expense removed successfully.");
        } else {
            System.out.println("Expense not found.");
        }
    }
    public void showBalance() {
        double totalSpent = expenses.stream()
                .mapToDouble(e -> e.amount)
                .sum();
        
        System.out.println("\n=== BALANCE SUMMARY ===");
        System.out.printf("Total Budget: ₹%.2f\n", totalBudget);
        System.out.printf("Total Spent: ₹%.2f\n", totalSpent);
        System.out.printf("Remaining Balance: ₹%.2f\n", totalBudget - totalSpent);
    }
    
}
