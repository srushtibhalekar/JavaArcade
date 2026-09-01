import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] words = {
            "JAVA",
            "COMPUTER",
            "PROGRAMMING",
            "DEVELOPER",
            "KEYBOARD"
            

        };

        String word = words[(int) (Math.random() * words.length)];

        char[] guessedWord = new char[word.length()];

        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        int attemptsLeft = 6;
        boolean won = false;

        System.out.println("=================================");
        System.out.println("          🪢 HANGMAN");
        System.out.println("=================================");

        System.out.println("Guess the hidden word!");

        while (attemptsLeft > 0 && !won) {

            System.out.print("\nWord: ");

            for (char c : guessedWord) {
                System.out.print(c + " ");
            }

            System.out.println("\nAttempts left: " + attemptsLeft);

            System.out.print("Enter a letter: ");
            char guess = Character.toUpperCase(sc.next().charAt(0));

            boolean found = false;

            for (int i = 0; i < word.length(); i++) {

                if (word.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    found = true;
                }
            }

            if (found) {
                System.out.println("✅ Correct guess!");
            } else {
                attemptsLeft--;
                System.out.println("❌ Wrong guess!");
            }

            won = true;

            for (char c : guessedWord) {
                if (c == '_') {
                    won = false;
                    break;
                }
            }
        }

        System.out.println("\n=================================");

        if (won) {
            System.out.println("🎉 YOU WON!");
            System.out.println("The word was: " + word);
        } else {
            System.out.println("💀 GAME OVER!");
            System.out.println("The word was: " + word);
        }

        System.out.println("=================================");
        System.out.println("Thanks for playing! 🎮");

        sc.close();
    }
}