import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Citizen {
    int citizenId;
    String name;
    int age;
    String area;
    double electricityBill;
    boolean billPaid;

    Citizen(int citizenId, String name, int age, String area) {
        this.citizenId = citizenId;
        this.name = name;
        this.age = age;
        this.area = area;
        this.electricityBill = 0;
        this.billPaid = true;
    }

    void display() {
        System.out.println("----------------------------------------");
        System.out.println("Citizen ID      : " + citizenId);
        System.out.println("Name            : " + name);
        System.out.println("Age             : " + age);
        System.out.println("Area            : " + area);
        System.out.println("Electricity Bill: ₹" + electricityBill);
        System.out.println("Bill Status     : " +
                (billPaid ? "Paid" : "Pending"));
    }
}

class CityProblem {
    int problemId;
    String citizenName;
    String category;
    String description;
    String status;

    CityProblem(int problemId, String citizenName,
                String category, String description) {
        this.problemId = problemId;
        this.citizenName = citizenName;
        this.category = category;
        this.description = description;
        this.status = "Reported";
    }

    void display() {
        System.out.println("----------------------------------------");
        System.out.println("Problem ID   : " + problemId);
        System.out.println("Citizen      : " + citizenName);
        System.out.println("Category     : " + category);
        System.out.println("Description  : " + description);
        System.out.println("Status       : " + status);
    }
}

public class SmartCity {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Citizen> citizens = new ArrayList<>();
    static ArrayList<CityProblem> problems = new ArrayList<>();
    static HashMap<String, Integer> cityServices = new HashMap<>();

    static int nextCitizenId = 1001;
    static int nextProblemId = 5001;

    static double cityRevenue = 0;
    static int resolvedProblems = 0;

    public static void main(String[] args) {

        loadServices();

        while (true) {

            System.out.println("\n========================================");
            System.out.println("          SMART CITY SIMULATOR");
            System.out.println("========================================");
            System.out.println("1. Register Citizen");
            System.out.println("2. View Citizens");
            System.out.println("3. Generate Electricity Bill");
            System.out.println("4. Pay Electricity Bill");
            System.out.println("5. Report City Problem");
            System.out.println("6. View City Problems");
            System.out.println("7. Resolve City Problem");
            System.out.println("8. View City Services");
            System.out.println("9. Smart City Report");
            System.out.println("10. Exit");
            System.out.println("========================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerCitizen();
                    break;

                case 2:
                    viewCitizens();
                    break;

                case 3:
                    generateBill();
                    break;

                case 4:
                    payBill();
                    break;

                case 5:
                    reportProblem();
                    break;

                case 6:
                    viewProblems();
                    break;

                case 7:
                    resolveProblem();
                    break;

                case 8:
                    viewServices();
                    break;

                case 9:
                    cityReport();
                    break;

                case 10:
                    System.out.println("\nThank you for using Smart City Simulator!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void loadServices() {

        cityServices.put("Hospitals", 8);
        cityServices.put("Schools", 15);
        cityServices.put("Police Stations", 6);
        cityServices.put("Fire Stations", 4);
        cityServices.put("Public Parks", 12);
        cityServices.put("Bus Stations", 10);
    }

    static void registerCitizen() {

        System.out.println("\n========== REGISTER CITIZEN ==========");

        System.out.print("Enter citizen name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        if (age <= 0) {
            System.out.println("Invalid age.");
            return;
        }

        System.out.print("Enter area: ");
        String area = scanner.nextLine();

        Citizen citizen = new Citizen(
                nextCitizenId++,
                name,
                age,
                area
        );

        citizens.add(citizen);

        System.out.println("\nCitizen registered successfully!");
        System.out.println("Citizen ID: " + citizen.citizenId);
    }

    static Citizen findCitizen(int citizenId) {

        for (Citizen citizen : citizens) {

            if (citizen.citizenId == citizenId) {
                return citizen;
            }
        }

        return null;
    }

    static void viewCitizens() {

        System.out.println("\n========== REGISTERED CITIZENS ==========");

        if (citizens.isEmpty()) {
            System.out.println("No citizens registered.");
            return;
        }

        for (Citizen citizen : citizens) {
            citizen.display();
        }
    }

    static void generateBill() {

        System.out.println("\n========== GENERATE ELECTRICITY BILL ==========");

        if (citizens.isEmpty()) {
            System.out.println("No citizens registered.");
            return;
        }

        System.out.print("Enter citizen ID: ");
        int citizenId = scanner.nextInt();

        Citizen citizen = findCitizen(citizenId);

        if (citizen == null) {
            System.out.println("Citizen not found.");
            return;
        }

        System.out.print("Enter electricity units consumed: ");
        int units = scanner.nextInt();

        if (units < 0) {
            System.out.println("Invalid units.");
            return;
        }

        double bill;

        if (units <= 100) {
            bill = units * 3.0;
        } else if (units <= 300) {
            bill = 100 * 3.0 +
                   (units - 100) * 5.0;
        } else {
            bill = 100 * 3.0 +
                   200 * 5.0 +
                   (units - 300) * 8.0;
        }

        citizen.electricityBill = bill;
        citizen.billPaid = false;

        System.out.println("\nElectricity bill generated!");
        System.out.println("Citizen: " + citizen.name);
        System.out.println("Units  : " + units);
        System.out.println("Bill   : ₹" + bill);
    }

    static void payBill() {

        System.out.println("\n========== PAY ELECTRICITY BILL ==========");

        System.out.print("Enter citizen ID: ");
        int citizenId = scanner.nextInt();

        Citizen citizen = findCitizen(citizenId);

        if (citizen == null) {
            System.out.println("Citizen not found.");
            return;
        }

        if (citizen.billPaid || citizen.electricityBill == 0) {
            System.out.println("No pending bill.");
            return;
        }

        System.out.println(
                "Pending Bill: ₹" +
                citizen.electricityBill
        );

        System.out.print("Confirm payment? (yes/no): ");
        scanner.nextLine();
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("yes")) {
            System.out.println("Payment cancelled.");
            return;
        }

        cityRevenue += citizen.electricityBill;
        citizen.billPaid = true;

        System.out.println("Bill paid successfully!");
    }

    static void reportProblem() {

        System.out.println("\n========== REPORT CITY PROBLEM ==========");

        System.out.print("Enter citizen name: ");
        String citizenName = scanner.nextLine();

        System.out.println("\nProblem Categories:");
        System.out.println("1. Road");
        System.out.println("2. Water");
        System.out.println("3. Electricity");
        System.out.println("4. Garbage");
        System.out.println("5. Street Light");
        System.out.println("6. Other");

        System.out.print("Choose category: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String category;

        switch (choice) {

            case 1:
                category = "Road";
                break;

            case 2:
                category = "Water";
                break;

            case 3:
                category = "Electricity";
                break;

            case 4:
                category = "Garbage";
                break;

            case 5:
                category = "Street Light";
                break;

            case 6:
                category = "Other";
                break;

            default:
                System.out.println("Invalid category.");
                return;
        }

        System.out.print("Describe the problem: ");
        String description = scanner.nextLine();

        CityProblem problem = new CityProblem(
                nextProblemId++,
                citizenName,
                category,
                description
        );

        problems.add(problem);

        System.out.println("\nProblem reported successfully!");
        System.out.println("Problem ID: " + problem.problemId);
    }

    static void viewProblems() {

        System.out.println("\n========== CITY PROBLEMS ==========");

        if (problems.isEmpty()) {
            System.out.println("No problems reported.");
            return;
        }

        for (CityProblem problem : problems) {
            problem.display();
        }
    }

    static void resolveProblem() {

        System.out.println("\n========== RESOLVE CITY PROBLEM ==========");

        if (problems.isEmpty()) {
            System.out.println("No problems available.");
            return;
        }

        System.out.print("Enter problem ID: ");
        int problemId = scanner.nextInt();

        CityProblem selectedProblem = null;

        for (CityProblem problem : problems) {

            if (problem.problemId == problemId) {
                selectedProblem = problem;
                break;
            }
        }

        if (selectedProblem == null) {
            System.out.println("Problem not found.");
            return;
        }

        if (selectedProblem.status.equals("Resolved")) {
            System.out.println("Problem is already resolved.");
            return;
        }

        selectedProblem.status = "Resolved";
        resolvedProblems++;

        System.out.println("\nProblem resolved successfully!");
        System.out.println(
                "Category: " +
                selectedProblem.category
        );
    }

    static void viewServices() {

        System.out.println("\n========== CITY SERVICES ==========");

        for (String service : cityServices.keySet()) {

            System.out.println(
                    service +
                    " : " +
                    cityServices.get(service)
            );
        }
    }

    static void cityReport() {

        System.out.println("\n========== SMART CITY REPORT ==========");

        int pendingProblems = problems.size() - resolvedProblems;

        int paidBills = 0;
        int pendingBills = 0;

        for (Citizen citizen : citizens) {

            if (citizen.billPaid) {
                paidBills++;
            } else if (citizen.electricityBill > 0) {
                pendingBills++;
            }
        }

        System.out.println("Registered Citizens : " + citizens.size());
        System.out.println("Total Problems     : " + problems.size());
        System.out.println("Resolved Problems  : " + resolvedProblems);
        System.out.println("Pending Problems   : " + pendingProblems);
        System.out.println("Paid Bills         : " + paidBills);
        System.out.println("Pending Bills      : " + pendingBills);
        System.out.println("City Revenue       : ₹" + cityRevenue);

        System.out.println("\nCity Services:");

        for (String service : cityServices.keySet()) {

            System.out.println(
                    service +
                    " : " +
                    cityServices.get(service)
            );
        }

        System.out.println("\nCity status: " +
                (pendingProblems == 0
                        ? "All reported problems resolved"
                        : "Some problems need attention"));
    }
}