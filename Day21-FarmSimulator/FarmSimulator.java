import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Crop {

    private String name;
    private int growth;
    private int value;

    public Crop(String name, int value) {
        this.name = name;
        this.value = value;
        this.growth = 0;
    }

    public String getName() {
        return name;
    }

    public int getGrowth() {
        return growth;
    }

    public int getValue() {
        return value;
    }

    public void grow() {

        if (growth < 100) {
            growth += 25;
        }

        if (growth > 100) {
            growth = 100;
        }
    }

    public boolean isReady() {
        return growth >= 100;
    }
}

class Farmer {

    private String name;
    private int coins;
    private ArrayList<Crop> crops;
    private HashMap<String, Integer> inventory;

    public Farmer(String name) {

        this.name = name;
        this.coins = 100;

        crops = new ArrayList<>();
        inventory = new HashMap<>();
    }

    public void addCrop(Crop crop) {
        crops.add(crop);
    }

    public void addInventory(String item, int quantity) {

        inventory.put(
            item,
            inventory.getOrDefault(item, 0) + quantity
        );
    }

    public boolean removeInventory(
            String item,
            int quantity) {

        int current =
            inventory.getOrDefault(item, 0);

        if (current < quantity) {
            return false;
        }

        inventory.put(item, current - quantity);

        return true;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public boolean spendCoins(int amount) {

        if (coins < amount) {
            return false;
        }

        coins -= amount;

        return true;
    }

    public int getCoins() {
        return coins;
    }

    public ArrayList<Crop> getCrops() {
        return crops;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void showStatus() {

        System.out.println("\n================================");
        System.out.println("        🌾 FARM STATUS");
        System.out.println("================================");

        System.out.println("👨‍🌾 Farmer: " + name);
        System.out.println("💰 Coins: " + coins);

        System.out.println("\n🌱 Crops:");

        if (crops.isEmpty()) {

            System.out.println("No crops planted.");

        } else {

            for (int i = 0; i < crops.size(); i++) {

                Crop crop = crops.get(i);

                System.out.println(
                    (i + 1)
                    + ". "
                    + crop.getName()
                    + " - Growth: "
                    + crop.getGrowth()
                    + "%"
                );
            }
        }

        System.out.println("\n🎒 Inventory:");

        if (inventory.isEmpty()) {

            System.out.println("Inventory is empty.");

        } else {

            for (String item : inventory.keySet()) {

                System.out.println(
                    "• "
                    + item
                    + " x"
                    + inventory.get(item)
                );
            }
        }
    }
}

public class FarmSimulator {

    static Random random = new Random();

    static void showSeedShop() {

        System.out.println("\n================================");
        System.out.println("          🌱 SEED SHOP");
        System.out.println("================================");

        System.out.println("1. Wheat Seeds   - ₹20");
        System.out.println("2. Corn Seeds    - ₹30");
        System.out.println("3. Tomato Seeds  - ₹40");
    }

    static void showCropMarket() {

        System.out.println("\n================================");
        System.out.println("          🏪 CROP MARKET");
        System.out.println("================================");

        System.out.println("Wheat   → ₹50");
        System.out.println("Corn    → ₹70");
        System.out.println("Tomato  → ₹100");
    }

    static Crop createCrop(int choice) {

        if (choice == 1) {
            return new Crop("Wheat", 50);
        }

        if (choice == 2) {
            return new Crop("Corn", 70);
        }

        if (choice == 3) {
            return new Crop("Tomato", 100);
        }

        return null;
    }

    static String getSeedName(int choice) {

        if (choice == 1) {
            return "Wheat Seeds";
        }

        if (choice == 2) {
            return "Corn Seeds";
        }

        if (choice == 3) {
            return "Tomato Seeds";
        }

        return "";
    }

    static int getSeedPrice(int choice) {

        if (choice == 1) {
            return 20;
        }

        if (choice == 2) {
            return 30;
        }

        if (choice == 3) {
            return 40;
        }

        return 0;
    }

    static void weatherEvent() {

        int event = random.nextInt(3);

        if (event == 0) {

            System.out.println(
                "☀️ Sunny weather! Crops grow normally."
            );

        } else if (event == 1) {

            System.out.println(
                "🌧️ Rainy weather! Great for farming."
            );

        } else {

            System.out.println(
                "🌬️ Windy weather! Protect your crops."
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       🌾 FARM SIMULATOR");
        System.out.println("================================");

        System.out.print("Enter farmer name: ");
        String name = sc.nextLine();

        Farmer farmer = new Farmer(name);

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("             MENU");
            System.out.println("================================");

            System.out.println("1. 🌱 Buy Seeds");
            System.out.println("2. 🌾 Plant Crop");
            System.out.println("3. 💧 Water Crops");
            System.out.println("4. ☀️ Grow Crops");
            System.out.println("5. 🧺 Harvest Crops");
            System.out.println("6. 🏪 Sell Crops");
            System.out.println("7. 📊 Farm Status");
            System.out.println("8. 🌦️ Weather");
            System.out.println("9. 🚪 Exit");

            System.out.print("\nChoose option: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                showSeedShop();

                System.out.print(
                    "\nChoose seed: "
                );

                int seedChoice = sc.nextInt();

                if (seedChoice < 1 || seedChoice > 3) {

                    System.out.println(
                        "❌ Invalid seed choice!"
                    );

                    continue;
                }

                int price =
                    getSeedPrice(seedChoice);

                String seed =
                    getSeedName(seedChoice);

                System.out.print(
                    "Enter quantity: "
                );

                int quantity = sc.nextInt();

                int totalCost =
                    price * quantity;

                if (farmer.spendCoins(totalCost)) {

                    farmer.addInventory(
                        seed,
                        quantity
                    );

                    System.out.println(
                        "✅ Purchased "
                        + quantity
                        + " "
                        + seed
                    );

                    System.out.println(
                        "💰 Remaining coins: ₹"
                        + farmer.getCoins()
                    );

                } else {

                    System.out.println(
                        "❌ Not enough coins!"
                    );
                }

            } else if (choice == 2) {

                System.out.println(
                    "\n🌱 Available Seeds:"
                );

                System.out.println(
                    "1. Wheat Seeds"
                );

                System.out.println(
                    "2. Corn Seeds"
                );

                System.out.println(
                    "3. Tomato Seeds"
                );

                System.out.print(
                    "\nChoose seed: "
                );

                int seedChoice = sc.nextInt();

                if (seedChoice < 1 || seedChoice > 3) {

                    System.out.println(
                        "❌ Invalid choice!"
                    );

                    continue;
                }

                String seed =
                    getSeedName(seedChoice);

                if (farmer.removeInventory(seed, 1)) {

                    Crop crop =
                        createCrop(seedChoice);

                    farmer.addCrop(crop);

                    System.out.println(
                        "🌱 "
                        + crop.getName()
                        + " planted successfully!"
                    );

                } else {

                    System.out.println(
                        "❌ You don't have this seed."
                    );
                }

            } else if (choice == 3) {

                if (farmer.getCrops().isEmpty()) {

                    System.out.println(
                        "❌ No crops to water."
                    );

                } else {

                    System.out.println(
                        "💧 Crops watered successfully!"
                    );
                }

            } else if (choice == 4) {

                if (farmer.getCrops().isEmpty()) {

                    System.out.println(
                        "❌ No crops planted."
                    );

                } else {

                    for (Crop crop : farmer.getCrops()) {
                        crop.grow();
                    }

                    System.out.println(
                        "☀️ Crops have grown!"
                    );

                    weatherEvent();
                }

            } else if (choice == 5) {

                int harvested = 0;

                for (int i = farmer.getCrops().size() - 1;
                     i >= 0;
                     i--) {

                    Crop crop =
                        farmer.getCrops().get(i);

                    if (crop.isReady()) {

                        farmer.addInventory(
                            crop.getName(),
                            1
                        );

                        farmer.getCrops().remove(i);

                        harvested++;

                        System.out.println(
                            "🧺 Harvested "
                            + crop.getName()
                        );
                    }
                }

                if (harvested == 0) {

                    System.out.println(
                        "❌ No crops are ready."
                    );
                }

            } else if (choice == 6) {

                showCropMarket();

                System.out.println(
                    "\n1. Sell Wheat"
                );

                System.out.println(
                    "2. Sell Corn"
                );

                System.out.println(
                    "3. Sell Tomato"
                );

                System.out.print(
                    "Choose crop: "
                );

                int cropChoice = sc.nextInt();

                String cropName = "";

                int price = 0;

                if (cropChoice == 1) {

                    cropName = "Wheat";
                    price = 50;

                } else if (cropChoice == 2) {

                    cropName = "Corn";
                    price = 70;

                } else if (cropChoice == 3) {

                    cropName = "Tomato";
                    price = 100;

                } else {

                    System.out.println(
                        "❌ Invalid choice!"
                    );

                    continue;
                }

                System.out.print(
                    "Enter quantity: "
                );

                int quantity = sc.nextInt();

                if (farmer.removeInventory(
                        cropName,
                        quantity)) {

                    int earnings =
                        price * quantity;

                    farmer.addCoins(earnings);

                    System.out.println(
                        "💰 Sold "
                        + quantity
                        + " "
                        + cropName
                    );

                    System.out.println(
                        "Earned: ₹"
                        + earnings
                    );

                } else {

                    System.out.println(
                        "❌ Not enough crops!"
                    );
                }

            } else if (choice == 7) {

                farmer.showStatus();

            } else if (choice == 8) {

                weatherEvent();

            } else if (choice == 9) {

                running = false;

                System.out.println(
                    "\n🌾 Farm simulator closed."
                );

            } else {

                System.out.println(
                    "❌ Invalid option!"
                );
            }
        }

        System.out.println("\n================================");
        System.out.println("       🌾 FARM SUMMARY");
        System.out.println("================================");

        System.out.println(
            "💰 Final Coins: ₹"
            + farmer.getCoins()
        );

        System.out.println(
            "🌱 Remaining Crops: "
            + farmer.getCrops().size()
        );

        System.out.println(
            "👋 Thank you for playing!"
        );

        sc.close();
    }
}