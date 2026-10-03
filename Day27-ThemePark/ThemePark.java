import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Ride {
    int rideId;
    String name;
    int capacity;
    double ticketPrice;
    int currentVisitors;

    Ride(int rideId, String name, int capacity, double ticketPrice) {
        this.rideId = rideId;
        this.name = name;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
        this.currentVisitors = 0;
    }

    boolean hasSpace() {
        return currentVisitors < capacity;
    }

    void display() {
        System.out.println("----------------------------------------");
        System.out.println("Ride ID       : " + rideId);
        System.out.println("Ride Name     : " + name);
        System.out.println("Capacity      : " + capacity);
        System.out.println("Visitors      : " + currentVisitors);
        System.out.println("Available     : " + (capacity - currentVisitors));
        System.out.println("Ticket Price  : ₹" + ticketPrice);
    }
}

class Visitor {
    int visitorId;
    String name;
    int age;
    ArrayList<String> rides;

    Visitor(int visitorId, String name, int age) {
        this.visitorId = visitorId;
        this.name = name;
        this.age = age;
        this.rides = new ArrayList<>();
    }

    void display() {
        System.out.println("----------------------------------------");
        System.out.println("Visitor ID : " + visitorId);
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);

        if (rides.isEmpty()) {
            System.out.println("Rides      : No rides");
        } else {
            System.out.println("Rides      : " + String.join(", ", rides));
        }
    }
}

public class ThemePark {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Ride> rides = new ArrayList<>();
    static HashMap<Integer, Visitor> visitors = new HashMap<>();

    static int nextVisitorId = 1001;
    static double totalRevenue = 0;

    public static void main(String[] args) {

        loadRides();

        while (true) {

            System.out.println("\n========================================");
            System.out.println("          THEME PARK SIMULATOR");
            System.out.println("========================================");
            System.out.println("1. View Rides");
            System.out.println("2. Add Visitor");
            System.out.println("3. Buy Ride Ticket");
            System.out.println("4. View Visitors");
            System.out.println("5. Start Ride");
            System.out.println("6. Park Revenue Report");
            System.out.println("7. Exit");
            System.out.println("========================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewRides();
                    break;

                case 2:
                    addVisitor();
                    break;

                case 3:
                    buyTicket();
                    break;

                case 4:
                    viewVisitors();
                    break;

                case 5:
                    startRide();
                    break;

                case 6:
                    parkReport();
                    break;

                case 7:
                    System.out.println("\nThank you for visiting the Theme Park!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void loadRides() {

        rides.add(new Ride(1, "Roller Coaster", 5, 250));
        rides.add(new Ride(2, "Ferris Wheel", 6, 150));
        rides.add(new Ride(3, "Water Splash", 5, 200));
        rides.add(new Ride(4, "Bumper Cars", 8, 120));
        rides.add(new Ride(5, "Haunted House", 6, 180));
    }

    static void viewRides() {

        System.out.println("\n========== THEME PARK RIDES ==========");

        for (Ride ride : rides) {
            ride.display();
        }
    }

    static void addVisitor() {

        System.out.println("\n========== ADD VISITOR ==========");

        System.out.print("Enter visitor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter visitor age: ");
        int age = scanner.nextInt();

        if (age <= 0) {
            System.out.println("Invalid age.");
            return;
        }

        Visitor visitor = new Visitor(nextVisitorId++, name, age);

        visitors.put(visitor.visitorId, visitor);

        System.out.println("\nVisitor added successfully!");
        System.out.println("Visitor ID: " + visitor.visitorId);
    }

    static Ride findRide(int rideId) {

        for (Ride ride : rides) {
            if (ride.rideId == rideId) {
                return ride;
            }
        }

        return null;
    }

    static void buyTicket() {

        System.out.println("\n========== BUY RIDE TICKET ==========");

        if (visitors.isEmpty()) {
            System.out.println("No visitors registered.");
            return;
        }

        System.out.print("Enter visitor ID: ");
        int visitorId = scanner.nextInt();

        Visitor visitor = visitors.get(visitorId);

        if (visitor == null) {
            System.out.println("Visitor not found.");
            return;
        }

        viewRides();

        System.out.print("\nEnter ride ID: ");
        int rideId = scanner.nextInt();

        Ride ride = findRide(rideId);

        if (ride == null) {
            System.out.println("Ride not found.");
            return;
        }

        if (!ride.hasSpace()) {
            System.out.println("Sorry, this ride is full.");
            return;
        }

        if (visitor.rides.contains(ride.name)) {
            System.out.println("Visitor already has a ticket for this ride.");
            return;
        }

        ride.currentVisitors++;
        visitor.rides.add(ride.name);

        totalRevenue += ride.ticketPrice;

        System.out.println("\nTicket purchased successfully!");
        System.out.println("Visitor : " + visitor.name);
        System.out.println("Ride    : " + ride.name);
        System.out.println("Price   : ₹" + ride.ticketPrice);
    }

    static void viewVisitors() {

        System.out.println("\n========== VISITORS ==========");

        if (visitors.isEmpty()) {
            System.out.println("No visitors registered.");
            return;
        }

        for (Visitor visitor : visitors.values()) {
            visitor.display();
        }
    }

    static void startRide() {

        System.out.println("\n========== START RIDE ==========");

        System.out.print("Enter ride ID: ");
        int rideId = scanner.nextInt();

        Ride ride = findRide(rideId);

        if (ride == null) {
            System.out.println("Ride not found.");
            return;
        }

        if (ride.currentVisitors == 0) {
            System.out.println("No visitors are waiting for this ride.");
            return;
        }

        System.out.println("\nRide Started!");
        System.out.println("Ride: " + ride.name);
        System.out.println("Visitors: " + ride.currentVisitors);

        ride.currentVisitors = 0;

        System.out.println("Ride completed successfully.");
        System.out.println("Seats are now available again.");
    }

    static void parkReport() {

        System.out.println("\n========== PARK REVENUE REPORT ==========");

        int totalTickets = 0;

        for (Visitor visitor : visitors.values()) {
            totalTickets += visitor.rides.size();
        }

        System.out.println("Total Visitors : " + visitors.size());
        System.out.println("Tickets Sold   : " + totalTickets);
        System.out.println("Park Revenue   : ₹" + totalRevenue);

        System.out.println("\nRide Status:");

        for (Ride ride : rides) {
            System.out.println(
                    ride.name + " -> " +
                    ride.currentVisitors + "/" +
                    ride.capacity + " visitors"
            );
        }
    }
}