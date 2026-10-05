import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Movie {
    int movieId;
    String name;
    String genre;
    double ticketPrice;
    int totalSeats;
    int bookedSeats;

    Movie(int movieId, String name, String genre,
          double ticketPrice, int totalSeats) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.ticketPrice = ticketPrice;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    boolean hasSeats(int seats) {
        return bookedSeats + seats <= totalSeats;
    }

    int availableSeats() {
        return totalSeats - bookedSeats;
    }

    void display() {
        System.out.println("----------------------------------------");
        System.out.println("Movie ID      : " + movieId);
        System.out.println("Movie Name    : " + name);
        System.out.println("Genre         : " + genre);
        System.out.println("Ticket Price  : ₹" + ticketPrice);
        System.out.println("Available     : " + availableSeats());
        System.out.println("Booked        : " + bookedSeats);
    }
}

class Booking {
    int bookingId;
    String customerName;
    String movieName;
    int seats;
    double ticketAmount;
    String status;

    Booking(int bookingId, String customerName,
            String movieName, int seats, double ticketAmount) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seats = seats;
        this.ticketAmount = ticketAmount;
        this.status = "Confirmed";
    }

    void display() {
        System.out.println("----------------------------------------");
        System.out.println("Booking ID    : " + bookingId);
        System.out.println("Customer      : " + customerName);
        System.out.println("Movie         : " + movieName);
        System.out.println("Seats         : " + seats);
        System.out.println("Ticket Amount : ₹" + ticketAmount);
        System.out.println("Status        : " + status);
    }
}

public class MovieTheater {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Movie> movies = new ArrayList<>();
    static HashMap<Integer, Booking> bookings = new HashMap<>();

    static int nextBookingId = 1001;
    static double totalRevenue = 0;
    static double foodRevenue = 0;

    public static void main(String[] args) {

        loadMovies();

        while (true) {

            System.out.println("\n========================================");
            System.out.println("         MOVIE THEATER SIMULATOR");
            System.out.println("========================================");
            System.out.println("1. View Movies");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Order Food");
            System.out.println("6. Theater Report");
            System.out.println("7. Exit");
            System.out.println("========================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewMovies();
                    break;

                case 2:
                    bookTickets();
                    break;

                case 3:
                    cancelBooking();
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    orderFood();
                    break;

                case 6:
                    theaterReport();
                    break;

                case 7:
                    System.out.println("\nThank you for visiting our theater!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void loadMovies() {

        movies.add(new Movie(
                1,
                "The Last Mission",
                "Action",
                250,
                50
        ));

        movies.add(new Movie(
                2,
                "Dream World",
                "Adventure",
                200,
                50
        ));

        movies.add(new Movie(
                3,
                "Love Story",
                "Romance",
                180,
                50
        ));

        movies.add(new Movie(
                4,
                "Mystery House",
                "Thriller",
                220,
                50
        ));

        movies.add(new Movie(
                5,
                "Galaxy War",
                "Sci-Fi",
                300,
                50
        ));
    }

    static void viewMovies() {

        System.out.println("\n========== MOVIES ==========");

        for (Movie movie : movies) {
            movie.display();
        }
    }

    static Movie findMovie(int movieId) {

        for (Movie movie : movies) {
            if (movie.movieId == movieId) {
                return movie;
            }
        }

        return null;
    }

    static void bookTickets() {

        System.out.println("\n========== BOOK TICKETS ==========");

        viewMovies();

        System.out.print("\nEnter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        Movie movie = findMovie(movieId);

        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter number of seats: ");
        int seats = scanner.nextInt();

        if (seats <= 0) {
            System.out.println("Invalid number of seats.");
            return;
        }

        if (!movie.hasSeats(seats)) {
            System.out.println("Not enough seats available.");
            System.out.println("Available seats: " + movie.availableSeats());
            return;
        }

        double amount = seats * movie.ticketPrice;

        Booking booking = new Booking(
                nextBookingId++,
                customerName,
                movie.name,
                seats,
                amount
        );

        bookings.put(booking.bookingId, booking);

        movie.bookedSeats += seats;
        totalRevenue += amount;

        System.out.println("\nBooking successful!");
        System.out.println("Booking ID : " + booking.bookingId);
        System.out.println("Movie      : " + movie.name);
        System.out.println("Seats      : " + seats);
        System.out.println("Amount     : ₹" + amount);
    }

    static void cancelBooking() {

        System.out.println("\n========== CANCEL BOOKING ==========");

        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();

        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        if (booking.status.equals("Cancelled")) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (booking.status.equals("Confirmed")) {

            for (Movie movie : movies) {

                if (movie.name.equals(booking.movieName)) {
                    movie.bookedSeats -= booking.seats;
                    break;
                }
            }

            booking.status = "Cancelled";

            totalRevenue -= booking.ticketAmount;

            System.out.println("Booking cancelled successfully.");
        }
    }

    static void viewBookings() {

        System.out.println("\n========== BOOKINGS ==========");

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Booking booking : bookings.values()) {
            booking.display();
        }
    }

    static void orderFood() {

        System.out.println("\n========== FOOD & BEVERAGES ==========");

        System.out.println("1. Popcorn      - ₹120");
        System.out.println("2. Cold Drink   - ₹80");
        System.out.println("3. Nachos       - ₹150");
        System.out.println("4. Combo Meal   - ₹250");

        System.out.print("Choose item: ");
        int choice = scanner.nextInt();

        double price;

        switch (choice) {

            case 1:
                price = 120;
                break;

            case 2:
                price = 80;
                break;

            case 3:
                price = 150;
                break;

            case 4:
                price = 250;
                break;

            default:
                System.out.println("Invalid food item.");
                return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        double amount = price * quantity;

        foodRevenue += amount;
        totalRevenue += amount;

        System.out.println("\nFood order successful!");
        System.out.println("Amount: ₹" + amount);
    }

    static void theaterReport() {

        System.out.println("\n========== THEATER REPORT ==========");

        int confirmedBookings = 0;
        int cancelledBookings = 0;
        int totalSeatsBooked = 0;

        for (Booking booking : bookings.values()) {

            if (booking.status.equals("Confirmed")) {
                confirmedBookings++;
                totalSeatsBooked += booking.seats;
            } else {
                cancelledBookings++;
            }
        }

        System.out.println("Total Movies       : " + movies.size());
        System.out.println("Total Bookings     : " + bookings.size());
        System.out.println("Confirmed Bookings : " + confirmedBookings);
        System.out.println("Cancelled Bookings : " + cancelledBookings);
        System.out.println("Seats Booked       : " + totalSeatsBooked);
        System.out.println("Ticket Revenue     : ₹" +
                (totalRevenue - foodRevenue));
        System.out.println("Food Revenue       : ₹" + foodRevenue);
        System.out.println("Total Revenue      : ₹" + totalRevenue);

        System.out.println("\nMovie Occupancy:");

        for (Movie movie : movies) {

            System.out.println(
                    movie.name +
                    " -> " +
                    movie.bookedSeats +
                    "/" +
                    movie.totalSeats
            );
        }
    }
}