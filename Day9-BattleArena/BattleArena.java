import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Fighter {

    private String name;
    private int health;
    private int attack;
    private int defense;

    public Fighter(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
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

    public int calculateDamage(Random random) {

        int bonus = random.nextInt(11);

        return Math.max(1, attack + bonus - defense);
    }

    public void displayStatus() {

        System.out.println(
            name + " | ❤️ HP: " + health
        );
    }
}

public class BattleArena {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        ArrayList<Fighter> playerTeam = new ArrayList<>();
        ArrayList<Fighter> computerTeam = new ArrayList<>();

        System.out.println("=================================");
        System.out.println("        ⚔️ BATTLE ARENA");
        System.out.println("=================================");

        System.out.println("\nCreate your team!");

        for (int i = 1; i <= 3; i++) {

            System.out.print("Enter fighter " + i + " name: ");
            String name = sc.next();

            playerTeam.add(
                new Fighter(name, 100, 25, 10)
            );
        }

        computerTeam.add(
            new Fighter("Dragon", 100, 30, 8)
        );

        computerTeam.add(
            new Fighter("Robot", 100, 27, 12)
        );

        computerTeam.add(
            new Fighter("Ninja", 100, 28, 10)
        );

        System.out.println("\n🔥 Your Team");

        displayTeam(playerTeam);

        System.out.println("\n🤖 Computer Team");

        displayTeam(computerTeam);

        int playerIndex = 0;
        int computerIndex = 0;

        while (playerIndex < playerTeam.size()
                && computerIndex < computerTeam.size()) {

            Fighter player = playerTeam.get(playerIndex);
            Fighter computer = computerTeam.get(computerIndex);

            System.out.println("\n=================================");
            System.out.println("⚔️ BATTLE");
            System.out.println("=================================");

            System.out.println(
                "\nYour Fighter: " + player.getName()
            );

            System.out.println(
                "Enemy Fighter: " + computer.getName()
            );

            System.out.println("\n1. ⚔️ Attack");
            System.out.println("2. 📊 Check Status");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            if (choice == 2) {

                player.displayStatus();
                computer.displayStatus();

                continue;
            }

            if (choice != 1) {

                System.out.println("❌ Invalid choice!");
                continue;
            }

            int playerDamage = player.calculateDamage(random);

            computer.takeDamage(playerDamage);

            System.out.println(
                "\n💥 " + player.getName()
                + " attacked " + computer.getName()
            );

            System.out.println(
                "Damage dealt: " + playerDamage
            );

            if (!computer.isAlive()) {

                System.out.println(
                    "💀 " + computer.getName() + " defeated!"
                );

                computerIndex++;

                continue;
            }

            int computerDamage = computer.calculateDamage(random);

            player.takeDamage(computerDamage);

            System.out.println(
                "\n🤖 " + computer.getName()
                + " attacked " + player.getName()
            );

            System.out.println(
                "Damage dealt: " + computerDamage
            );

            if (!player.isAlive()) {

                System.out.println(
                    "💀 " + player.getName() + " defeated!"
                );

                playerIndex++;
            }

            System.out.println("\n❤️ Current Status");

            player.displayStatus();
            computer.displayStatus();
        }

        System.out.println("\n=================================");
        System.out.println("           🏆 RESULT");
        System.out.println("=================================");

        if (playerIndex >= playerTeam.size()) {

            System.out.println("🤖 COMPUTER WINS!");

        } else {

            System.out.println("🎉 YOUR TEAM WINS!");
        }

        System.out.println("=================================");

        sc.close();
    }

    static void displayTeam(ArrayList<Fighter> team) {

        for (Fighter fighter : team) {

            fighter.displayStatus();
        }
    }
}