import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Detective {

    private String name;
    private int score;

    public Detective(String name) {
        this.name = name;
        this.score = 0;
    }

    public void addScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}

public class DetectiveMystery {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("======================================");
        System.out.println("        🔎 DETECTIVE MYSTERY");
        System.out.println("======================================");

        System.out.print("Enter detective name: ");
        String name = sc.nextLine();

        Detective detective = new Detective(name);

        ArrayList<String> suspects = new ArrayList<>();

        suspects.add("Alex");
        suspects.add("Bruno");
        suspects.add("Clara");
        suspects.add("Diana");

        HashMap<String, String> clues = new HashMap<>();

        clues.put("1", "A broken watch was found at the crime scene.");
        clues.put("2", "A muddy shoe print was found near the window.");
        clues.put("3", "A red scarf was found on the floor.");
        clues.put("4", "Someone heard a car leaving at midnight.");

        String culprit = suspects.get(random.nextInt(suspects.size()));

        System.out.println("\n🕵️ Welcome Detective " + detective.getName());
        System.out.println("A mysterious crime has occurred!");
        System.out.println("Find the clues and identify the culprit.");

        boolean playing = true;

        while (playing) {

            System.out.println("\n======================================");
            System.out.println("              🔎 MENU");
            System.out.println("======================================");

            System.out.println("1. View Suspects");
            System.out.println("2. Investigate Clues");
            System.out.println("3. Guess Culprit");
            System.out.println("4. Show Score");
            System.out.println("5. Exit");

            System.out.print("\nChoose option: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.println("\n👥 SUSPECTS");

                for (int i = 0; i < suspects.size(); i++) {
                    System.out.println(
                        (i + 1) + ". " + suspects.get(i)
                    );
                }

            } else if (choice == 2) {

                System.out.println("\n🔍 INVESTIGATING CLUES...");

                for (String key : clues.keySet()) {
                    System.out.println(
                        "Clue " + key + ": " + clues.get(key)
                    );
                }

                detective.addScore(20);

                System.out.println("\n⭐ +20 Investigation Points");

            } else if (choice == 3) {

                System.out.println("\n👤 Who is the culprit?");

                for (int i = 0; i < suspects.size(); i++) {
                    System.out.println(
                        (i + 1) + ". " + suspects.get(i)
                    );
                }

                System.out.print("\nEnter suspect number: ");
                int guess = sc.nextInt();

                if (guess >= 1 && guess <= suspects.size()) {

                    String selected = suspects.get(guess - 1);

                    if (selected.equals(culprit)) {

                        detective.addScore(100);

                        System.out.println(
                            "\n🎉 CORRECT!"
                        );

                        System.out.println(
                            "🔎 The culprit was " + culprit
                        );

                        System.out.println(
                            "🏆 +100 points"
                        );

                        playing = false;

                    } else {

                        detective.addScore(-20);

                        System.out.println(
                            "\n❌ Wrong suspect!"
                        );

                        System.out.println(
                            "⚠️ -20 points"
                        );
                    }

                } else {

                    System.out.println(
                        "❌ Invalid suspect number!"
                    );
                }

            } else if (choice == 4) {

                System.out.println("\n📊 DETECTIVE STATUS");

                System.out.println(
                    "🕵️ Detective: "
                    + detective.getName()
                );

                System.out.println(
                    "🏆 Score: "
                    + detective.getScore()
                );

            } else if (choice == 5) {

                System.out.println(
                    "\n🚪 Investigation closed."
                );

                playing = false;

            } else {

                System.out.println(
                    "\n❌ Invalid choice!"
                );
            }
        }

        System.out.println("\n======================================");
        System.out.println("          🏁 CASE CLOSED");
        System.out.println("======================================");

        System.out.println(
            "🕵️ Detective: " + detective.getName()
        );

        System.out.println(
            "🏆 Final Score: " + detective.getScore()
        );

        System.out.println(
            "\nThanks for playing! 🔎"
        );

        sc.close();
    }
}