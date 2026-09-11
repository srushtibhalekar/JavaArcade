import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Player {

    private String name;
    private int health;
    private int coins;
    private ArrayList<String> inventory;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.coins = 0;
        this.inventory = new ArrayList<>();
    }

    public void addCoins(int amount) {
        coins += amount;
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

    public void heal(int amount) {
        health += amount;

        if (health > 100) {
            health = 100;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getCoins() {
        return coins;
    }

    public void showStatus() {

        System.out.println("\n========== PLAYER STATUS ==========");
        System.out.println("👤 Name      : " + name);
        System.out.println("❤️ Health    : " + health);
        System.out.println("🪙 Coins     : " + coins);
        System.out.println("🎒 Inventory : " + inventory);
        System.out.println("===================================");
    }
}

public class TreasureHunt {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        HashMap<Integer, String> locations = new HashMap<>();

        locations.put(1, "Dark Forest");
        locations.put(2, "Ancient Cave");
        locations.put(3, "Abandoned Temple");
        locations.put(4, "Treasure Island");

        System.out.println("=================================");
        System.out.println("        🗺️ TREASURE HUNT");
        System.out.println("=================================");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        Player player = new Player(name);

        player.addItem("Map");

        boolean gameRunning = true;

        System.out.println("\n🗺️ You received an ancient map!");
        System.out.println("Find the hidden treasure.");
        System.out.println("Be careful of traps and enemies!");

        while (gameRunning && player.isAlive()) {

            System.out.println("\n=================================");
            System.out.println("              MENU");
            System.out.println("=================================");

            System.out.println("1. 🌲 Explore Dark Forest");
            System.out.println("2. 🕳️ Explore Ancient Cave");
            System.out.println("3. 🏛️ Explore Abandoned Temple");
            System.out.println("4. 🏝️ Search Treasure Island");
            System.out.println("5. 📊 Player Status");
            System.out.println("6. 🚪 Exit");

            System.out.print("\nChoose location: ");
            int choice = sc.nextInt();

            if (choice == 6) {
                System.out.println("\n👋 You left the treasure hunt.");
                break;
            }

            if (choice == 5) {
                player.showStatus();
                continue;
            }

            if (!locations.containsKey(choice)) {
                System.out.println("❌ Invalid choice!");
                continue;
            }

            System.out.println(
                "\n📍 You entered: " + locations.get(choice)
            );

            int event = random.nextInt(100);

            if (choice == 1) {

                if (event < 40) {

                    int coins = random.nextInt(101) + 50;
                    player.addCoins(coins);

                    System.out.println("🪙 You found " + coins + " coins!");

                } else if (event < 70) {

                    int damage = random.nextInt(21) + 10;
                    player.takeDamage(damage);

                    System.out.println("🐺 A wild animal attacked you!");
                    System.out.println("💥 Damage: " + damage);

                } else {

                    player.addItem("Forest Gem");

                    System.out.println("💎 You found a Forest Gem!");
                }

            } else if (choice == 2) {

                if (event < 40) {

                    int damage = random.nextInt(26) + 15;
                    player.takeDamage(damage);

                    System.out.println("🪨 A cave trap activated!");
                    System.out.println("💥 Damage: " + damage);

                } else if (event < 75) {

                    player.addItem("Golden Key");

                    System.out.println("🔑 You found a Golden Key!");

                } else {

                    int coins = random.nextInt(201) + 100;
                    player.addCoins(coins);

                    System.out.println("🪙 You found " + coins + " coins!");
                }

            } else if (choice == 3) {

                if (event < 35) {

                    System.out.println("👻 A temple guardian appeared!");

                    int damage = random.nextInt(31) + 20;
                    player.takeDamage(damage);

                    System.out.println(
                        "💥 Guardian damaged you for " + damage
                    );

                } else if (event < 70) {

                    player.addItem("Ancient Crystal");

                    System.out.println("🔮 You discovered an Ancient Crystal!");

                } else {

                    player.heal(20);

                    System.out.println("❤️ You found a healing potion!");
                    System.out.println("❤️ Health restored by 20.");
                }

            } else if (choice == 4) {

                System.out.println("\n🏝️ You reached Treasure Island!");

                if (player.getCoins() >= 300) {

                    System.out.println(
                        "🪙 You have enough coins to unlock the treasure!"
                    );

                    System.out.println(
                        "🎉 YOU FOUND THE HIDDEN TREASURE!"
                    );

                    System.out.println(
                        "💰 Treasure reward: 1000 coins"
                    );

                    player.addCoins(1000);

                    gameRunning = false;

                } else {

                    System.out.println(
                        "🔒 The treasure chest is locked!"
                    );

                    System.out.println(
                        "You need at least 300 coins."
                    );

                    System.out.println(
                        "Current coins: " + player.getCoins()
                    );
                }
            }

            if (!player.isAlive()) {

                System.out.println("\n=================================");
                System.out.println("          💀 GAME OVER");
                System.out.println("=================================");
                System.out.println("You lost all your health.");

                break;
            }

            System.out.println(
                "\n❤️ Current Health: " + player.getHealth()
            );

            System.out.println(
                "🪙 Current Coins: " + player.getCoins()
            );
        }

        System.out.println("\n=================================");
        System.out.println("          🏆 FINAL RESULT");
        System.out.println("=================================");

        player.showStatus();

        System.out.println("\nThanks for playing! 🗺️");

        sc.close();
    }
}