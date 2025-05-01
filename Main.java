import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWelcome to the Event Management System");
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Add Client");
            System.out.println("6. View Clients");
            System.out.println("7. Book Event");
            System.out.println("8. View Bookings");
            System.out.println("9. Set Budget");
            System.out.println("10. Add Expense");
            System.out.println("11. View Expenses");
            System.out.println("12. Remove Expense");
            System.out.println("13. View Balance");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: manager.addEvent(); break;
                case 2: manager.viewEvents(); break;
                case 3: manager.updateEvent(); break;
                case 4: manager.deleteEvent(); break;
                case 5: manager.addClient(); break;
                case 6: manager.viewClients(); break;
                case 7: manager.bookEvent(); break;
                case 8: manager.viewBookings(); break;
                case 9: manager.setBudget(); break;
                case 10: manager.addExpense(); break;
                case 11: manager.viewExpenses(); break;
                case 12: manager.removeExpense(); break;
                case 13: manager.showBalance(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}
