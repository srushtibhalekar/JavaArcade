import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Player {

    private String name;
    private int health;
    private int money;
    private ArrayList<String> inventory;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.money = 0;
        this.inventory = new ArrayList<>();
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public void takeDamage(int damage) {
        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void showStatus() {

        System.out.println("\n👤 Player: " + name);
        System.out.println("❤️ Health: " + health);
        System.out.println("💰 Money: ₹" + money);
        System.out.println("🎒 Inventory: " + inventory);
    }
}

public class BankHeist {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        HashMap<Integer, String> locations = new HashMap<>();

        locations.put(1, "Bank Vault");
        locations.put(2, "Security Room");
        locations.put(3, "Storage Room");
        locations.put(4, "Exit");

        System.out.println("=================================");
        System.out.println("          🏦 BANK HEIST");
        System.out.println("=================================");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        Player player = new Player(name);

        boolean gameRunning = true;

        while (gameRunning && player.isAlive()) {

            System.out.println("\n=================================");
            System.out.println("             MENU");
            System.out.println("=================================");

            System.out.println("1. 🔐 Enter Bank Vault");
            System.out.println("2. 🛡️ Enter Security Room");
            System.out.println("3. 📦 Search Storage Room");
            System.out.println("4. 🚪 Escape");
            System.out.println("5. 📊 Player Status");

            System.out.print("\nChoose an option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\n🔐 Entering " + locations.get(1));

                    int vaultChance = random.nextInt(100);

                    if (vaultChance < 70) {

                        int cash = random.nextInt(5001) + 5000;

                        player.addMoney(cash);

                        System.out.println(
                            "💰 You successfully stole ₹" + cash
                        );

                    } else {

                        System.out.println(
                            "🚨 Security alarm activated!"
                        );

                        player.takeDamage(30);

                        System.out.println(
                            "💥 You lost 30 health!"
                        );
                    }

                    break;

                case 2:

                    System.out.println(
                        "\n🛡️ Entering " + locations.get(2)
                    );

                    int securityChance = random.nextInt(100);

                    if (securityChance < 60) {

                        player.addItem("Security Key");

                        System.out.println(
                            "🔑 You found a Security Key!"
                        );

                    } else {

                        System.out.println(
                            "🚨 Guard spotted you!"
                        );

                        player.takeDamage(20);

                        System.out.println(
                            "💥 You lost 20 health!"
                        );
                    }

                    break;

                case 3:

                    System.out.println(
                        "\n📦 Searching " + locations.get(3)
                    );

                    int itemChance = random.nextInt(100);

                    if (itemChance < 50) {

                        player.addItem("Diamond");

                        System.out.println(
                            "💎 You found a Diamond!"
                        );

                    } else if (itemChance < 80) {

                        int cash = random.nextInt(3001) + 1000;

                        player.addMoney(cash);

                        System.out.println(
                            "💵 You found ₹" + cash
                        );

                    } else {

                        System.out.println(
                            "😐 Nothing useful found."
                        );
                    }

                    break;

                case 4:

                    System.out.println(
                        "\n🚪 You escaped from the bank!"
                    );

                    System.out.println(
                        "🏆 HEIST COMPLETED!"
                    );

                    gameRunning = false;

                    break;

                case 5:

                    player.showStatus();

                    break;

                default:

                    System.out.println(
                        "❌ Invalid choice!"
                    );
            }
        }

        if (!player.isAlive()) {

            System.out.println("\n=================================");
            System.out.println("          💀 HEIST FAILED");
            System.out.println("=================================");
            System.out.println(
                "You were caught by security!"
            );

        } else {

            System.out.println("\n=================================");
            System.out.println("          🎉 GAME OVER");
            System.out.println("=================================");

            player.showStatus();
        }

        sc.close();
    }
}