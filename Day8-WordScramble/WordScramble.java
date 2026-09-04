import java.util.Random;
import java.util.Scanner;

public class WordScramble {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] words = {
            "JAVA",
            "PROGRAM",
            "COMPUTER",
            "DEVELOPER",
            "KEYBOARD",
            "SOFTWARE",
            "DATABASE",
            "NETWORK"
        };

        int score = 0;
        int rounds = 5;

        System.out.println("=================================");
        System.out.println("       🔀 WORD SCRAMBLE GAME");
        System.out.println("=================================");
        System.out.println("Unscramble the letters!");
        System.out.println("You have " + rounds + " rounds.");

        for (int round = 1; round <= rounds; round++) {

            String word = words[random.nextInt(words.length)];
            String scrambled = scrambleWord(word, random);

            System.out.println("\n---------------------------------");
            System.out.println("Round " + round);
            System.out.println("Scrambled word: " + scrambled);

            System.out.print("Your answer: ");
            String answer = sc.next().toUpperCase();

            if (answer.equals(word)) {

                System.out.println("🎉 Correct!");
                score += 10;

            } else {

                System.out.println("❌ Wrong!");
                System.out.println("Correct word: " + word);
            }

            System.out.println("Score: " + score);
        }

        System.out.println("\n=================================");
        System.out.println("           🏆 GAME OVER");
        System.out.println("=================================");
        System.out.println("Final Score: " + score + "/" + (rounds * 10));

        if (score == rounds * 10) {
            System.out.println("🔥 Perfect Score!");
        } else if (score >= 30) {
            System.out.println("👏 Great Job!");
        } else {
            System.out.println("📚 Keep Practicing!");
        }

        System.out.println("=================================");

        sc.close();
    }

    static String scrambleWord(String word, Random random) {

        char[] letters = word.toCharArray();

        for (int i = letters.length - 1; i > 0; i--) {

            int j = random.nextInt(i + 1);

            char temp = letters[i];
            letters[i] = letters[j];
            letters[j] = temp;
        }

        return new String(letters);
    }
}