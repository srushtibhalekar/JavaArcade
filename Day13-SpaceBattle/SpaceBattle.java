import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Spaceship {

    private String name;
    private int health;
    private int attackPower;

    public Spaceship(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }

    public int attack(Random random) {
        return attackPower + random.nextInt(16);
    }

    public void showStatus() {
        System.out.println(
            "🚀 " + name + " | ❤️ HP: " + health
        );
    }
}

public class SpaceBattle {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        ArrayList<Spaceship> enemies = new ArrayList<>();

        enemies.add(new Spaceship("Alien Fighter", 100, 15));
        enemies.add(new Spaceship("Space Raider", 120, 18));
        enemies.add(new Spaceship("Galaxy Destroyer", 150, 20));

        System.out.println("=================================");
        System.out.println("         🚀 SPACE BATTLE");
        System.out.println("=================================");

        System.out.print("Enter your spaceship name: ");
        String name = sc.nextLine();

        Spaceship player =
            new Spaceship(name, 150, 20);

        int score = 0;
        int enemyIndex = 0;

        System.out.println("\n🌌 Welcome to the Space Battle!");
        System.out.println("Defeat all enemy spaceships!");

        while (player.isAlive()
                && enemyIndex < enemies.size()) {

            Spaceship enemy = enemies.get(enemyIndex);

            System.out.println("\n=================================");
            System.out.println("          ⚔️ BATTLE");
            System.out.println("=================================");

            System.out.println("\nYour Ship:");
            player.showStatus();

            System.out.println("\nEnemy:");
            enemy.showStatus();

            System.out.println("\n1. 🚀 Attack");
            System.out.println("2. 🛡️ Repair");
            System.out.println("3. 📊 Status");
            System.out.println("4. 🚪 Exit");

            System.out.print("\nChoose action: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                int damage = player.attack(random);

                enemy.takeDamage(damage);

                System.out.println(
                    "\n💥 You attacked "
                    + enemy.getName()
                );

                System.out.println(
                    "⚡ Damage dealt: " + damage
                );

                if (!enemy.isAlive()) {

                    score += 100;

                    System.out.println(
                        "💀 " + enemy.getName()
                        + " destroyed!"
                    );

                    System.out.println(
                        "🏆 +100 points"
                    );

                    enemyIndex++;

                    continue;
                }

                int enemyDamage =
                    enemy.attack(random);

                player.takeDamage(enemyDamage);

                System.out.println(
                    "\n👽 Enemy attacked your ship!"
                );

                System.out.println(
                    "💥 Damage received: "
                    + enemyDamage
                );

            } else if (choice == 2) {

                int repair = random.nextInt(21) + 10;

                int oldHealth = player.getHealth();

                player.takeDamage(-repair);

                int newHealth = player.getHealth();

                System.out.println(
                    "\n🛠️ Ship repaired!"
                );

                System.out.println(
                    "❤️ Health: "
                    + oldHealth + " → " + newHealth
                );

            } else if (choice == 3) {

                System.out.println("\n📊 STATUS");

                player.showStatus();
                enemy.showStatus();

                System.out.println(
                    "🏆 Score: " + score
                );

            } else if (choice == 4) {

                System.out.println(
                    "\n🚪 You escaped from the battle."
                );

                break;

            } else {

                System.out.println(
                    "❌ Invalid choice!"
                );
            }

            if (!player.isAlive()) {

                System.out.println(
                    "\n💀 Your spaceship was destroyed!"
                );
            }
        }

        System.out.println("\n=================================");
        System.out.println("          🏆 FINAL RESULT");
        System.out.println("=================================");

        if (!player.isAlive()) {

            System.out.println(
                "💀 GAME OVER!"
            );

        } else if (enemyIndex == enemies.size()) {

            score += 500;

            System.out.println(
                "🌌 ALL ENEMIES DEFEATED!"
            );

            System.out.println(
                "🎉 YOU ARE THE GALAXY CHAMPION!"
            );

            System.out.println(
                "🔥 Victory Bonus: +500 points"
            );

        } else {

            System.out.println(
                "🚀 Battle ended."
            );
        }

        System.out.println(
            "🏆 Final Score: " + score
        );

        player.showStatus();

        System.out.println(
            "\nThanks for playing! 🚀"
        );

        sc.close();
    }
}