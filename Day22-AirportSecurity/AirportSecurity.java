import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Passenger {

    private String name;
    private String passportNumber;
    private boolean suspicious;
    private ArrayList<String> baggage;

    public Passenger(String name, String passportNumber,
                     boolean suspicious,
                     ArrayList<String> baggage) {

        this.name = name;
        this.passportNumber = passportNumber;
        this.suspicious = suspicious;
        this.baggage = baggage;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public boolean isSuspicious() {
        return suspicious;
    }

    public ArrayList<String> getBaggage() {
        return baggage;
    }

    public void display() {

        System.out.println("\n👤 Passenger: " + name);
        System.out.println(
            "🛂 Passport: " + passportNumber
        );

        System.out.println("🧳 Baggage:");

        for (String item : baggage) {
            System.out.println("• " + item);
        }
    }
}

public class AirportSecurity {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static ArrayList<Passenger> passengers =
        new ArrayList<>();

    static HashMap<String, Integer> statistics =
        new HashMap<>();

    static void createPassengers() {

        ArrayList<String> bag1 = new ArrayList<>();
        bag1.add("Clothes");
        bag1.add("Laptop");
        bag1.add("Book");

        ArrayList<String> bag2 = new ArrayList<>();
        bag2.add("Clothes");
        bag2.add("Water Bottle");
        bag2.add("Camera");

        ArrayList<String> bag3 = new ArrayList<>();
        bag3.add("Clothes");
        bag3.add("Knife");
        bag3.add("Phone");

        ArrayList<String> bag4 = new ArrayList<>();
        bag4.add("Laptop");
        bag4.add("Headphones");
        bag4.add("Charger");

        ArrayList<String> bag5 = new ArrayList<>();
        bag5.add("Clothes");
        bag5.add("Unknown Package");
        bag5.add("Book");

        passengers.add(
            new Passenger(
                "Aarav",
                "IN1001",
                false,
                bag1
            )
        );

        passengers.add(
            new Passenger(
                "Priya",
                "IN1002",
                false,
                bag2
            )
        );

        passengers.add(
            new Passenger(
                "Rahul",
                "IN1003",
                true,
                bag3
            )
        );

        passengers.add(
            new Passenger(
                "Sneha",
                "IN1004",
                false,
                bag4
            )
        );

        passengers.add(
            new Passenger(
                "Vikram",
                "IN1005",
                true,
                bag5
            )
        );

        statistics.put("Inspected", 0);
        statistics.put("Cleared", 0);
        statistics.put("Detained", 0);
        statistics.put("Correct Decisions", 0);
        statistics.put("Wrong Decisions", 0);
    }

    static void showPassengers() {

        System.out.println("\n================================");
        System.out.println("       👥 PASSENGER QUEUE");
        System.out.println("================================");

        for (int i = 0; i < passengers.size(); i++) {

            Passenger passenger =
                passengers.get(i);

            System.out.println(
                (i + 1)
                + ". "
                + passenger.getName()
                + " - "
                + passenger.getPassportNumber()
            );
        }
    }

    static boolean hasProhibitedItem(
            Passenger passenger) {

        for (String item : passenger.getBaggage()) {

            if (item.equalsIgnoreCase("Knife")
                    || item.equalsIgnoreCase("Unknown Package")) {

                return true;
            }
        }

        return false;
    }

    static void inspectPassenger(
            Passenger passenger) {

        passenger.display();

        System.out.println("\n🔍 SECURITY CHECK");

        System.out.println("1. Clear Passenger");
        System.out.println("2. Detain Passenger");

        System.out.print("\nYour decision: ");
        int decision = sc.nextInt();

        boolean prohibited =
            hasProhibitedItem(passenger);

        boolean correct = false;

        if (decision == 1) {

            if (!prohibited
                    && !passenger.isSuspicious()) {

                correct = true;

                statistics.put(
                    "Cleared",
                    statistics.get("Cleared") + 1
                );

                System.out.println(
                    "\n✅ Passenger cleared."
                );

            } else {

                System.out.println(
                    "\n⚠️ Wrong decision!"
                );
            }

        } else if (decision == 2) {

            if (prohibited
                    || passenger.isSuspicious()) {

                correct = true;

                statistics.put(
                    "Detained",
                    statistics.get("Detained") + 1
                );

                System.out.println(
                    "\n🚨 Passenger detained."
                );

            } else {

                System.out.println(
                    "\n⚠️ Wrong decision!"
                );
            }

        } else {

            System.out.println(
                "\n❌ Invalid decision!"
            );

            return;
        }

        if (correct) {

            statistics.put(
                "Correct Decisions",
                statistics.get("Correct Decisions") + 1
            );

            System.out.println(
                "🎯 Correct security decision!"
            );

        } else {

            statistics.put(
                "Wrong Decisions",
                statistics.get("Wrong Decisions") + 1
            );
        }

        statistics.put(
            "Inspected",
            statistics.get("Inspected") + 1
        );
    }

    static void randomSecurityEvent() {

        int event = random.nextInt(3);

        if (event == 0) {

            System.out.println(
                "📢 Security Alert: Random baggage scan activated!"
            );

        } else if (event == 1) {

            System.out.println(
                "🚪 Security Gate: Metal detector checking active."
            );

        } else {

            System.out.println(
                "📡 Security System: All scanners operational."
            );
        }
    }

    static void showStatistics() {

        System.out.println("\n================================");
        System.out.println("       📊 SECURITY REPORT");
        System.out.println("================================");

        System.out.println(
            "👥 Inspected: "
            + statistics.get("Inspected")
        );

        System.out.println(
            "✅ Cleared: "
            + statistics.get("Cleared")
        );

        System.out.println(
            "🚨 Detained: "
            + statistics.get("Detained")
        );

        System.out.println(
            "🎯 Correct Decisions: "
            + statistics.get("Correct Decisions")
        );

        System.out.println(
            "❌ Wrong Decisions: "
            + statistics.get("Wrong Decisions")
        );
    }

    public static void main(String[] args) {

        createPassengers();

        System.out.println("================================");
        System.out.println("   ✈️ AIRPORT SECURITY");
        System.out.println("       SIMULATOR");
        System.out.println("================================");

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("             MENU");
            System.out.println("================================");

            System.out.println("1. 👥 View Passengers");
            System.out.println("2. 🔍 Inspect Passenger");
            System.out.println("3. 📡 Security Event");
            System.out.println("4. 📊 Security Report");
            System.out.println("5. 🚪 Exit");

            System.out.print("\nChoose option: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                showPassengers();

            } else if (choice == 2) {

                if (statistics.get("Inspected")
                        >= passengers.size()) {

                    System.out.println(
                        "\n✅ All passengers inspected!"
                    );

                    continue;
                }

                showPassengers();

                System.out.print(
                    "\nSelect passenger number: "
                );

                int number = sc.nextInt();

                if (number < 1
                        || number > passengers.size()) {

                    System.out.println(
                        "❌ Invalid passenger!"
                    );

                    continue;
                }

                Passenger passenger =
                    passengers.get(number - 1);

                inspectPassenger(passenger);

            } else if (choice == 3) {

                randomSecurityEvent();

            } else if (choice == 4) {

                showStatistics();

            } else if (choice == 5) {

                running = false;

                System.out.println(
                    "\n✈️ Security shift completed."
                );

                showStatistics();

            } else {

                System.out.println(
                    "\n❌ Invalid option!"
                );
            }
        }

        System.out.println(
            "\n👮 Thank you for working the security checkpoint!"
        );

        sc.close();
    }
}