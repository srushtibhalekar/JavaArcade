import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Survivor {

    private String name;
    private int health;
    private int food;
    private int water;
    private int wood;
    private int days;

    public Survivor(String name) {
        this.name = name;
        health = 100;
        food = 50;
        water = 50;
        wood = 20;
        days = 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getFood() {
        return food;
    }

    public int getWater() {
        return water;
    }

    public int getWood() {
        return wood;
    }

    public int getDays() {
        return days;
    }

    public void addFood(int amount) {
        food += amount;
    }

    public void addWater(int amount) {
        water += amount;
    }

    public void addWood(int amount) {
        wood += amount;
    }

    public void useFood(int amount) {
        food -= amount;

        if (food < 0) {
            food = 0;
        }
    }

    public void useWater(int amount) {
        water -= amount;

        if (water < 0) {
            water = 0;
        }
    }

    public void takeDamage(int amount) {
        health -= amount;

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

    public void nextDay() {
        days++;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

public class IslandSurvival {

    static void showStatus(Survivor survivor) {

        System.out.println("\n================================");
        System.out.println("        📊 SURVIVOR STATUS");
        System.out.println("================================");

        System.out.println(
            "👤 Name   : " + survivor.getName()
        );

        System.out.println(
            "❤️ Health : " + survivor.getHealth()
        );

        System.out.println(
            "🍖 Food   : " + survivor.getFood()
        );

        System.out.println(
            "💧 Water  : " + survivor.getWater()
        );

        System.out.println(
            "🪵 Wood   : " + survivor.getWood()
        );

        System.out.println(
            "📅 Day    : " + survivor.getDays()
        );
    }

    static void explore(
            Survivor survivor,
            Random random,
            ArrayList<String> discoveries) {

        int event = random.nextInt(5);

        System.out.println("\n🔎 Exploring the island...");

        if (event == 0) {

            int food = random.nextInt(21) + 10;

            survivor.addFood(food);

            discoveries.add("Food");

            System.out.println(
                "🍎 You found " + food + " food!"
            );

        } else if (event == 1) {

            int water = random.nextInt(21) + 10;

            survivor.addWater(water);

            discoveries.add("Fresh Water");

            System.out.println(
                "💧 You found " + water + " water!"
            );

        } else if (event == 2) {

            int wood = random.nextInt(16) + 5;

            survivor.addWood(wood);

            discoveries.add("Wood");

            System.out.println(
                "🪵 You collected " + wood + " wood!"
            );

        } else if (event == 3) {

            int damage = random.nextInt(16) + 5;

            survivor.takeDamage(damage);

            System.out.println(
                "🐍 A wild animal attacked you!"
            );

            System.out.println(
                "💔 Damage: " + damage
            );

        } else {

            survivor.heal(10);

            discoveries.add("Medicine");

            System.out.println(
                "💊 You discovered medicine!"
            );

            System.out.println(
                "❤️ You recovered 10 health."
            );
        }
    }

    static void buildShelter(
            Survivor survivor) {

        if (survivor.getWood() >= 20) {

            survivor.addWood(-20);

            survivor.heal(15);

            System.out.println(
                "\n🏠 Shelter built successfully!"
            );

            System.out.println(
                "🪵 20 wood used."
            );

            System.out.println(
                "❤️ You rested and recovered 15 health."
            );

        } else {

            System.out.println(
                "\n❌ Not enough wood!"
            );

            System.out.println(
                "You need at least 20 wood."
            );
        }
    }

    static void surviveDay(
            Survivor survivor) {

        survivor.useFood(10);
        survivor.useWater(10);
        survivor.nextDay();

        System.out.println(
            "\n🌅 A new day begins..."
        );

        System.out.println(
            "🍖 Food used: 10"
        );

        System.out.println(
            "💧 Water used: 10"
        );

        if (survivor.getFood() == 0) {

            survivor.takeDamage(10);

            System.out.println(
                "⚠️ You have no food!"
            );

            System.out.println(
                "💔 Health -10"
            );
        }

        if (survivor.getWater() == 0) {

            survivor.takeDamage(15);

            System.out.println(
                "⚠️ You have no water!"
            );

            System.out.println(
                "💔 Health -15"
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        ArrayList<String> discoveries =
            new ArrayList<>();

        System.out.println("================================");
        System.out.println("       🏝️ ISLAND SURVIVAL");
        System.out.println("================================");

        System.out.print(
            "Enter survivor name: "
        );

        String name = sc.nextLine();

        Survivor survivor =
            new Survivor(name);

        System.out.println(
            "\n🌊 Your boat has crashed!"
        );

        System.out.println(
            "You are stranded on a mysterious island."
        );

        System.out.println(
            "Survive for 7 days and build enough resources to escape."
        );

        boolean running = true;

        while (running && survivor.isAlive()) {

            System.out.println("\n================================");
            System.out.println("             🏝️ MENU");
            System.out.println("================================");

            System.out.println("1. 🔎 Explore");
            System.out.println("2. 🏠 Build Shelter");
            System.out.println("3. 🌅 Survive Next Day");
            System.out.println("4. 📊 Show Status");
            System.out.println("5. 🎒 Show Discoveries");
            System.out.println("6. 🚪 Exit");

            System.out.print("\nChoose action: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                explore(
                    survivor,
                    random,
                    discoveries
                );

            } else if (choice == 2) {

                buildShelter(survivor);

            } else if (choice == 3) {

                surviveDay(survivor);

                if (survivor.getDays() >= 7) {

                    if (survivor.getFood() > 0
                            && survivor.getWater() > 0
                            && survivor.getWood() >= 20) {

                        System.out.println(
                            "\n🚁 A rescue helicopter arrived!"
                        );

                        System.out.println(
                            "🎉 You successfully survived!"
                        );

                        running = false;

                    } else {

                        System.out.println(
                            "\n⚠️ Rescue is nearby!"
                        );

                        System.out.println(
                            "But you need:"
                        );

                        System.out.println(
                            "🍖 Food > 0"
                        );

                        System.out.println(
                            "💧 Water > 0"
                        );

                        System.out.println(
                            "🪵 At least 20 wood"
                        );
                    }
                }

            } else if (choice == 4) {

                showStatus(survivor);

            } else if (choice == 5) {

                System.out.println(
                    "\n🎒 DISCOVERIES"
                );

                if (discoveries.isEmpty()) {

                    System.out.println(
                        "No discoveries yet."
                    );

                } else {

                    for (String item : discoveries) {
                        System.out.println(
                            "• " + item
                        );
                    }
                }

            } else if (choice == 6) {

                System.out.println(
                    "\n🚪 Survival mission ended."
                );

                running = false;

            } else {

                System.out.println(
                    "\n❌ Invalid choice!"
                );
            }
        }

        System.out.println("\n================================");
        System.out.println("          🏆 FINAL RESULT");
        System.out.println("================================");

        if (!survivor.isAlive()) {

            System.out.println(
                "💀 You did not survive the island."
            );

        } else if (survivor.getDays() >= 7
                && survivor.getFood() > 0
                && survivor.getWater() > 0
                && survivor.getWood() >= 20) {

            System.out.println(
                "🚁 RESCUE SUCCESSFUL!"
            );

            System.out.println(
                "🏆 You survived the island!"
            );

        } else {

            System.out.println(
                "🏝️ Your survival journey ended."
            );
        }

        showStatus(survivor);

        System.out.println(
            "\nThanks for playing! 🏝️"
        );

        sc.close();
    }
}