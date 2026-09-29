import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Train {

    private int trainNumber;
    private String trainName;
    private String destination;
    private int seats;
    private double ticketPrice;

    public Train(int trainNumber, String trainName,
                 String destination, int seats,
                 double ticketPrice) {

        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.destination = destination;
        this.seats = seats;
        this.ticketPrice = ticketPrice;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getDestination() {
        return destination;
    }

    public int getSeats() {
        return seats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public boolean bookSeat() {

        if (seats <= 0) {
            return false;
        }

        seats--;
        return true;
    }

    public void cancelSeat() {
        seats++;
    }

    public void display() {

        System.out.printf(
            "%d | %-20s | %-15s | Seats: %d | ₹%.2f%n",
            trainNumber,
            trainName,
            destination,
            seats,
            ticketPrice
        );
    }
}

class Ticket {

    private int ticketId;
    private String passengerName;
    private int trainNumber;
    private String destination;
    private double price;

    public Ticket(int ticketId, String passengerName,
                  int trainNumber, String destination,
                  double price) {

        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void display() {

        System.out.println("\n------------------------------");
        System.out.println("🎫 Ticket ID: " + ticketId);
        System.out.println("👤 Passenger: " + passengerName);
        System.out.println("🚆 Train No: " + trainNumber);
        System.out.println("📍 Destination: " + destination);
        System.out.printf("💰 Price: ₹%.2f%n", price);
        System.out.println("------------------------------");
    }
}

public class TrainStation {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static ArrayList<Train> trains =
        new ArrayList<>();

    static HashMap<Integer, Ticket> tickets =
        new HashMap<>();

    static int nextTicketId = 1001;
    static double totalRevenue = 0;

    static void createTrains() {

        trains.add(
            new Train(
                101,
                "Deccan Express",
                "Mumbai",
                5,
                450
            )
        );

        trains.add(
            new Train(
                102,
                "Intercity Express",
                "Nashik",
                5,
                350
            )
        );

        trains.add(
            new Train(
                103,
                "Rajdhani Express",
                "Delhi",
                5,
                1200
            )
        );

        trains.add(
            new Train(
                104,
                "Karnataka Express",
                "Bangalore",
                5,
                900
            )
        );

        trains.add(
            new Train(
                105,
                "Goa Express",
                "Goa",
                5,
                750
            )
        );
    }

    static void showTrains() {

        System.out.println("\n==============================================");
        System.out.println("              🚆 TRAIN LIST");
        System.out.println("==============================================");

        for (Train train : trains) {
            train.display();
        }
    }

    static Train findTrain(int trainNumber) {

        for (Train train : trains) {

            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }

        return null;
    }

    static void bookTicket() {

        showTrains();

        System.out.print(
            "\nEnter train number: "
        );

        int trainNumber = sc.nextInt();
        sc.nextLine();

        Train train = findTrain(trainNumber);

        if (train == null) {

            System.out.println(
                "❌ Train not found!"
            );

            return;
        }

        if (train.getSeats() <= 0) {

            System.out.println(
                "❌ No seats available!"
            );

            return;
        }

        System.out.print(
            "Enter passenger name: "
        );

        String passengerName = sc.nextLine();

        if (passengerName.trim().isEmpty()) {

            System.out.println(
                "❌ Passenger name cannot be empty!"
            );

            return;
        }

        if (train.bookSeat()) {

            int ticketId = nextTicketId++;

            Ticket ticket =
                new Ticket(
                    ticketId,
                    passengerName,
                    train.getTrainNumber(),
                    train.getDestination(),
                    train.getTicketPrice()
                );

            tickets.put(ticketId, ticket);

            totalRevenue += train.getTicketPrice();

            System.out.println(
                "\n✅ Ticket booked successfully!"
            );

            ticket.display();
        }
    }

    static void cancelTicket() {

        if (tickets.isEmpty()) {

            System.out.println(
                "\n❌ No active tickets."
            );

            return;
        }

        System.out.print(
            "\nEnter ticket ID: "
        );

        int ticketId = sc.nextInt();

        Ticket ticket =
            tickets.get(ticketId);

        if (ticket == null) {

            System.out.println(
                "❌ Ticket not found!"
            );

            return;
        }

        Train train =
            findTrain(ticket.getTrainNumber());

        if (train != null) {
            train.cancelSeat();
        }

        tickets.remove(ticketId);

        System.out.println(
            "✅ Ticket cancelled successfully."
        );
    }

    static void showTickets() {

        System.out.println("\n================================");
        System.out.println("          🎫 TICKETS");
        System.out.println("================================");

        if (tickets.isEmpty()) {

            System.out.println(
                "No active tickets."
            );

            return;
        }

        for (Ticket ticket : tickets.values()) {
            ticket.display();
        }
    }

    static void stationAnnouncement() {

        int event = random.nextInt(4);

        System.out.println("\n📢 STATION ANNOUNCEMENT");

        if (event == 0) {

            System.out.println(
                "🚆 Deccan Express is arriving shortly."
            );

        } else if (event == 1) {

            System.out.println(
                "⏰ Passengers are requested to reach the platform."
            );

        } else if (event == 2) {

            System.out.println(
                "🔊 Please keep your tickets ready for inspection."
            );

        } else {

            System.out.println(
                "🚉 Platform is now open for boarding."
            );
        }
    }

    static void showStatistics() {

        System.out.println("\n================================");
        System.out.println("        📊 STATION REPORT");
        System.out.println("================================");

        System.out.println(
            "🎫 Active Tickets: "
            + tickets.size()
        );

        System.out.printf(
            "💰 Total Revenue: ₹%.2f%n",
            totalRevenue
        );

        System.out.println(
            "🚆 Total Trains: "
            + trains.size()
        );
    }

    public static void main(String[] args) {

        createTrains();

        System.out.println("================================");
        System.out.println("      🚆 TRAIN STATION");
        System.out.println("         SIMULATOR");
        System.out.println("================================");

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("             MENU");
            System.out.println("================================");

            System.out.println("1. 🚆 View Trains");
            System.out.println("2. 🎫 Book Ticket");
            System.out.println("3. ❌ Cancel Ticket");
            System.out.println("4. 📋 View Tickets");
            System.out.println("5. 📢 Station Announcement");
            System.out.println("6. 📊 Station Report");
            System.out.println("7. 🚪 Exit");

            System.out.print("\nChoose option: ");

            int choice = sc.nextInt();

            if (choice == 1) {

                showTrains();

            } else if (choice == 2) {

                bookTicket();

            } else if (choice == 3) {

                cancelTicket();

            } else if (choice == 4) {

                showTickets();

            } else if (choice == 5) {

                stationAnnouncement();

            } else if (choice == 6) {

                showStatistics();

            } else if (choice == 7) {

                running = false;

                System.out.println(
                    "\n🚆 Train station closed."
                );

                showStatistics();

            } else {

                System.out.println(
                    "\n❌ Invalid option!"
                );
            }
        }

        System.out.println(
            "\n👋 Thank you for using Train Station Simulator!"
        );

        sc.close();
    }
}