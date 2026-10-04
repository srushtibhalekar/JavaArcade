import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Resource {
    String name;
    int value;

    Resource(String name, int value) {
        this.name = name;
        this.value = value;
    }
}

class Miner {
    String name;
    int energy;
    int coins;
    int equipmentLevel;
    HashMap<String, Integer> inventory;

    Miner(String name) {
        this.name = name;
        this.energy = 100;
        this.coins = 500;
        this.equipmentLevel = 1;
        this.inventory = new HashMap<>();
    }

    void addResource(String resource, int quantity) {
        inventory.put(
            resource,
            inventory.getOrDefault(resource, 0) + quantity
        );
    }

    int getResourceCount(String resource) {
        return inventory.getOrDefault(resource, 0);
    }
}

public class MiningAdventure {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static Miner miner;
    static ArrayList<Resource> resources = new ArrayList<>();

    static int totalMiningTrips = 0;
    static int totalResourcesMined = 0;
    static int totalSales = 0;

    public static void main(String[] args) {

        loadResources();

        System.out.println("========================================");
        System.out.println("       MINING ADVENTURE SIMULATOR");
        System.out.println("========================================");

        System.out.print("Enter your miner name: ");
        String name = scanner.nextLine();

        miner = new Miner(name);

        System.out.println("\nWelcome, " + miner.name + "!");
        System.out.println("You have ₹500 and 100 energy.");

        while (true) {

            System.out.println("\n========================================");
            System.out.println("          MINING ADVENTURE");
            System.out.println("========================================");
            System.out.println("1. Enter Mine");
            System.out.println("2. View Inventory");
            System.out.println("3. Sell Resources");
            System.out.println("4. Buy Equipment");
            System.out.println("5. View Miner Status");
            System.out.println("6. Mining Report");
            System.out.println("7. Rest");
            System.out.println("8. Exit");
            System.out.println("========================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    enterMine();
                    break;

                case 2:
                    viewInventory();
                    break;

                case 3:
                    sellResources();
                    break;

                case 4:
                    buyEquipment();
                    break;

                case 5:
                    viewStatus();
                    break;

                case 6:
                    miningReport();
                    break;

                case 7:
                    rest();
                    break;

                case 8:
                    System.out.println("\nThanks for playing Mining Adventure!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void loadResources() {

        resources.add(new Resource("Coal", 30));
        resources.add(new Resource("Iron", 60));
        resources.add(new Resource("Gold", 150));
        resources.add(new Resource("Diamond", 500));
        resources.add(new Resource("Ruby", 300));
    }

    static void enterMine() {

        System.out.println("\n========== ENTER MINE ==========");

        if (miner.energy < 20) {
            System.out.println("Not enough energy!");
            System.out.println("Rest before entering the mine.");
            return;
        }

        miner.energy -= 20;
        totalMiningTrips++;

        int numberOfFinds = random.nextInt(3) + 1;

        System.out.println("You entered the mine...");
        System.out.println("Equipment Level: " + miner.equipmentLevel);

        for (int i = 0; i < numberOfFinds; i++) {

            Resource resource = resources.get(
                random.nextInt(resources.size())
            );

            int quantity = random.nextInt(
                miner.equipmentLevel + 2
            ) + 1;

            miner.addResource(resource.name, quantity);

            totalResourcesMined += quantity;

            System.out.println(
                "⛏️ Found " + quantity + " " + resource.name
            );
        }

        if (random.nextInt(100) < 15) {
            System.out.println("\n⚠️ You encountered a dangerous cave section!");
            miner.energy -= 10;

            if (miner.energy < 0) {
                miner.energy = 0;
            }
        }

        System.out.println("\nMining trip completed.");
    }

    static void viewInventory() {

        System.out.println("\n========== INVENTORY ==========");

        if (miner.inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        int totalValue = 0;

        for (Resource resource : resources) {

            int quantity = miner.getResourceCount(resource.name);

            if (quantity > 0) {

                int value = quantity * resource.value;
                totalValue += value;

                System.out.println(
                    resource.name +
                    " : " + quantity +
                    " | Value: ₹" + value
                );
            }
        }

        System.out.println("--------------------------------");
        System.out.println("Inventory Value: ₹" + totalValue);
    }

    static void sellResources() {

        System.out.println("\n========== SELL RESOURCES ==========");

        if (miner.inventory.isEmpty()) {
            System.out.println("No resources available.");
            return;
        }

        viewInventory();

        scanner.nextLine();

        System.out.print("\nEnter resource name to sell: ");
        String resourceName = scanner.nextLine();

        Resource selected = null;

        for (Resource resource : resources) {
            if (resource.name.equalsIgnoreCase(resourceName)) {
                selected = resource;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Resource not found.");
            return;
        }

        int quantity = miner.getResourceCount(selected.name);

        if (quantity == 0) {
            System.out.println("You don't have this resource.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int sellQuantity = scanner.nextInt();

        if (sellQuantity <= 0 || sellQuantity > quantity) {
            System.out.println("Invalid quantity.");
            return;
        }

        int earnings = sellQuantity * selected.value;

        int remaining = quantity - sellQuantity;

        if (remaining == 0) {
            miner.inventory.remove(selected.name);
        } else {
            miner.inventory.put(selected.name, remaining);
        }

        miner.coins += earnings;
        totalSales += earnings;

        System.out.println("\nSale completed!");
        System.out.println("Resource: " + selected.name);
        System.out.println("Quantity: " + sellQuantity);
        System.out.println("Earned: ₹" + earnings);
    }

    static void buyEquipment() {

        System.out.println("\n========== BUY EQUIPMENT ==========");

        if (miner.equipmentLevel >= 5) {
            System.out.println("Your equipment is already at maximum level.");
            return;
        }

        int cost = miner.equipmentLevel * 500;

        System.out.println(
            "Current Equipment Level: " +
            miner.equipmentLevel
        );

        System.out.println(
            "Upgrade Cost: ₹" + cost
        );

        System.out.print("Buy upgrade? (yes/no): ");
        scanner.nextLine();
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("yes")) {
            System.out.println("Upgrade cancelled.");
            return;
        }

        if (miner.coins < cost) {
            System.out.println("Not enough coins.");
            return;
        }

        miner.coins -= cost;
        miner.equipmentLevel++;

        System.out.println("\nEquipment upgraded!");
        System.out.println(
            "New Level: " +
            miner.equipmentLevel
        );
    }

    static void viewStatus() {

        System.out.println("\n========== MINER STATUS ==========");

        System.out.println("Miner Name       : " + miner.name);
        System.out.println("Energy           : " + miner.energy);
        System.out.println("Coins            : ₹" + miner.coins);
        System.out.println("Equipment Level  : " + miner.equipmentLevel);
        System.out.println("Mining Trips     : " + totalMiningTrips);
        System.out.println("Resources Mined  : " + totalResourcesMined);
    }

    static void miningReport() {

        System.out.println("\n========== MINING REPORT ==========");

        System.out.println("Miner            : " + miner.name);
        System.out.println("Total Trips      : " + totalMiningTrips);
        System.out.println("Resources Mined  : " + totalResourcesMined);
        System.out.println("Total Sales      : ₹" + totalSales);
        System.out.println("Current Coins    : ₹" + miner.coins);
        System.out.println("Equipment Level  : " + miner.equipmentLevel);

        System.out.println("\nResource Inventory:");

        if (miner.inventory.isEmpty()) {
            System.out.println("No resources.");
        } else {
            for (String resource : miner.inventory.keySet()) {
                System.out.println(
                    resource +
                    " : " +
                    miner.inventory.get(resource)
                );
            }
        }
    }

    static void rest() {

        System.out.println("\n========== REST ==========");

        if (miner.energy >= 100) {
            System.out.println("Your energy is already full.");
            return;
        }

        miner.energy += 30;

        if (miner.energy > 100) {
            miner.energy = 100;
        }

        System.out.println("You rested and recovered energy.");
        System.out.println("Current Energy: " + miner.energy);
    }
}