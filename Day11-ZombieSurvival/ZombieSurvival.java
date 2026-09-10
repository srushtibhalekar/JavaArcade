import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Survivor {

    private String name;
    private int health;
    private int score;
    private ArrayList<String> inventory;

    public Survivor(String name) {
        this.name = name;
        this.health = 100;
        this.score = 0;
        this.inventory = new ArrayList<>();
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    public void removeItem(String item) {
        inventory.remove(item);
    }

    public void addScore(int points) {
        score += points;
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

    public int getScore() {
        return score;
    }

    public void showStatus() {
        System.out.println("\n👤 Survivor: " + name);
        System.out.println("❤️ Health: " + health);
        System.out.println("🏆 Score: " + score);
        System.out.println("🎒 Inventory: " + inventory);
    }
}

public class ZombieSurvival {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=================================");
        System.out.println("       🧟 ZOMBIE SURVIVAL");
        System.out.println("=================================");

        System.out.print("Enter survivor name: ");
        String name = sc.nextLine();

        Survivor player = new Survivor(name);

        player.addItem("Knife");

        int rounds = 10;

        System.out.println("\n🔪 You start with a Knife.");
        System.out.println("🧟 Survive " + rounds + " zombie rounds!");

        for (int round = 1; round <= rounds && player.isAlive(); round++) {

            System.out.println("\n=================================");
            System.out.println("           ROUND " + round);
            System.out.println("=================================");

            int zombieHealth = random.nextInt(41) + 40;

            System.out.println(
                "🧟 A zombie appears with "
                + zombieHealth + " HP!"
            );

            boolean zombieAlive = true;

            while (zombieAlive && player.isAlive()) {

                System.out.println("\n1. 🔪 Attack");
                System.out.println("2. 🏃 Escape");
                System.out.println("3. ❤️ Use Medkit");
                System.out.println("4. 📊 Status");

                System.out.print("Choose: ");
                int choice = sc.nextInt();

                if (choice == 1) {

                    int damage = random.nextInt(21) + 15;

                    zombieHealth -= damage;

                    System.out.println(
                        "💥 You dealt " + damage + " damage!"
                    );

                    if (zombieHealth <= 0) {

                        zombieAlive = false;

                        player.addScore(100);

                        System.out.println(
                            "💀 Zombie defeated!"
                        );

                        System.out.println(
                            "🏆 +100 points"
                        );

                        int itemChance = random.nextInt(100);

                        if (itemChance < 30) {

                            player.addItem("Medkit");

                            System.out.println(
                                "🎁 You found a Medkit!"
                            );
                        }

                    } else {

                        int zombieDamage = random.nextInt(16) + 10;

                        player.takeDamage(zombieDamage);

                        System.out.println(
                            "🧟 Zombie attacks you for "
                            + zombieDamage + " damage!"
                        );
                    }

                } else if (choice == 2) {

                    int escapeChance = random.nextInt(100);

                    if (escapeChance < 60) {

                        System.out.println(
                            "🏃 You escaped!"
                        );

                        zombieAlive = false;

                    } else {

                        System.out.println(
                            "❌ Escape failed!"
                        );

                        int damage = random.nextInt(16) + 10;

                        player.takeDamage(damage);

                        System.out.println(
                            "🧟 Zombie damaged you for "
                            + damage
                        );
                    }

                } else if (choice == 3) {

                    if (player.hasItem("Medkit")) {

                        player.removeItem("Medkit");
                        player.heal(30);

                        System.out.println(
                            "❤️ Medkit used! +30 health."
                        );

                    } else {

                        System.out.println(
                            "❌ No Medkit available!"
                        );
                    }

                } else if (choice == 4) {

                    player.showStatus();

                } else {

                    System.out.println(
                        "❌ Invalid choice!"
                    );
                }
            }

            if (player.isAlive()) {

                System.out.println(
                    "\n✅ You survived Round " + round
                );

                System.out.println(
                    "❤️ Health: " + player.getHealth()
                );
            }
        }

        System.out.println("\n=================================");
        System.out.println("          🏆 FINAL RESULT");
        System.out.println("=================================");

        if (player.isAlive()) {

            player.addScore(500);

            System.out.println(
                "🎉 YOU SURVIVED THE APOCALYPSE!"
            );

            System.out.println(
                "🔥 Survival Bonus: +500 points"
            );

        } else {

            System.out.println(
                "💀 YOU WERE DEFEATED!"
            );
        }

        player.showStatus();

        System.out.println("=================================");

        sc.close();
    }
}