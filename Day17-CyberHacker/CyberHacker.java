import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Hacker {

    private String name;
    private int score;
    private int attempts;

    public Hacker(String name) {
        this.name = name;
        this.score = 0;
        this.attempts = 3;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void addScore(int points) {
        score += points;
    }

    public void loseAttempt() {
        attempts--;
    }
}

public class CyberHacker {

    static void showMenu() {

        System.out.println("\n================================");
        System.out.println("          💻 HACKER MENU");
        System.out.println("================================");

        System.out.println("1. 🔐 Crack Password");
        System.out.println("2. 🧩 Decode Message");
        System.out.println("3. 🔢 Guess Security Code");
        System.out.println("4. 📊 Show Status");
        System.out.println("5. 🚪 Exit");
    }

    static void crackPassword(
            Hacker hacker,
            Scanner sc) {

        String password = "JAVA";

        System.out.println("\n🔐 PASSWORD CRACKING");
        System.out.println("Hint: It is a programming language.");

        System.out.print("Enter password: ");
        String guess = sc.next().toUpperCase();

        if (guess.equals(password)) {

            System.out.println("✅ Password cracked!");

            hacker.addScore(100);

            System.out.println("🏆 +100 points");

        } else {

            System.out.println("❌ Wrong password!");

            hacker.loseAttempt();

            System.out.println("⚠️ Attempt lost.");
        }
    }

    static void decodeMessage(
            Hacker hacker,
            Scanner sc) {

        String encoded = "KBWB";

        System.out.println("\n🧩 DECODE MESSAGE");

        System.out.println(
            "Encoded message: " + encoded
        );

        System.out.println(
            "Hint: Every letter is shifted +1."
        );

        System.out.print(
            "Enter decoded message: "
        );

        String answer = sc.next().toUpperCase();

        if (answer.equals("JAVA")) {

            System.out.println(
                "🎉 Message decoded!"
            );

            hacker.addScore(150);

            System.out.println(
                "🏆 +150 points"
            );

        } else {

            System.out.println(
                "❌ Incorrect decoding!"
            );

            hacker.loseAttempt();
        }
    }

    static void securityCode(
            Hacker hacker,
            Scanner sc,
            Random random) {

        int secretCode = random.nextInt(10) + 1;

        System.out.println("\n🔢 SECURITY CODE");
        System.out.println(
            "Guess the security code between 1 and 10."
        );

        System.out.print("Enter code: ");
        int guess = sc.nextInt();

        if (guess == secretCode) {

            System.out.println(
                "🔓 Security system cracked!"
            );

            hacker.addScore(200);

            System.out.println(
                "🏆 +200 points"
            );

        } else {

            System.out.println(
                "❌ Wrong code!"
            );

            System.out.println(
                "🔎 Correct code was: "
                + secretCode
            );

            hacker.loseAttempt();
        }
    }

    static void showStatus(Hacker hacker) {

        System.out.println("\n📊 HACKER STATUS");

        System.out.println(
            "👤 Hacker: " + hacker.getName()
        );

        System.out.println(
            "🏆 Score: " + hacker.getScore()
        );

        System.out.println(
            "❤️ Attempts: " + hacker.getAttempts()
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        ArrayList<String> tools = new ArrayList<>();

        tools.add("Password Cracker");
        tools.add("Message Decoder");
        tools.add("Security Scanner");

        HashMap<Integer, String> missions =
            new HashMap<>();

        missions.put(1, "Crack the password");
        missions.put(2, "Decode the message");
        missions.put(3, "Guess the security code");

        System.out.println("================================");
        System.out.println("        💻 CYBER HACKER");
        System.out.println("================================");

        System.out.print(
            "Enter your hacker name: "
        );

        String name = sc.nextLine();

        Hacker hacker = new Hacker(name);

        System.out.println(
            "\n🔥 Welcome, " + hacker.getName() + "!"
        );

        System.out.println(
            "Complete missions and earn points."
        );

        System.out.println("\n🛠️ Available Tools:");

        for (String tool : tools) {
            System.out.println("• " + tool);
        }

        boolean running = true;

        while (running && hacker.getAttempts() > 0) {

            showMenu();

            System.out.print("\nChoose mission: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                crackPassword(hacker, sc);

            } else if (choice == 2) {

                decodeMessage(hacker, sc);

            } else if (choice == 3) {

                securityCode(
                    hacker,
                    sc,
                    random
                );

            } else if (choice == 4) {

                showStatus(hacker);

                System.out.println("\n📋 Missions:");

                for (Integer key : missions.keySet()) {

                    System.out.println(
                        key + ". "
                        + missions.get(key)
                    );
                }

            } else if (choice == 5) {

                System.out.println(
                    "\n🚪 Hacker session ended."
                );

                running = false;

            } else {

                System.out.println(
                    "❌ Invalid choice!"
                );
            }

            if (hacker.getAttempts() == 0) {

                System.out.println(
                    "\n🚨 SECURITY SYSTEM LOCKED!"
                );

                System.out.println(
                    "Too many failed attempts."
                );
            }
        }

        System.out.println("\n================================");
        System.out.println("         🏆 FINAL REPORT");
        System.out.println("================================");

        System.out.println(
            "👤 Hacker: "
            + hacker.getName()
        );

        System.out.println(
            "🏆 Final Score: "
            + hacker.getScore()
        );

        System.out.println(
            "❤️ Attempts Remaining: "
            + hacker.getAttempts()
        );

        System.out.println(
            "\n💻 Mission Complete!"
        );

        sc.close();
    }
}