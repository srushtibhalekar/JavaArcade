
import java.util.Random;
import java.util.Scanner;

public class GuessGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 7;

        System.out.println("=================================");
        System.out.println("       🎯 GUESS THE NUMBER");
        System.out.println("=================================");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts!");

        while (attempts < maxAttempts) {

            System.out.print("\nEnter your guess: ");
            int guess = sc.nextInt();

            attempts++;

            if (guess == secretNumber) {

                int score = (maxAttempts - attempts + 1) * 10;

                System.out.println("\n🎉 CORRECT!");
                System.out.println("You found the number in "
                        + attempts + " attempts.");
                System.out.println("🏆 Your Score: " + score);

                break;

            } else if (guess < secretNumber) {

                System.out.println("⬆️ Too low! Try a higher number.");

            } else {

                System.out.println("⬇️ Too high! Try a lower number.");
            }

            System.out.println("Attempts left: "
                    + (maxAttempts - attempts));

            if (attempts == maxAttempts) {

                System.out.println("\n💀 GAME OVER!");
                System.out.println("The number was: " + secretNumber);
            }
        }

        System.out.println("\n=================================");
        System.out.println("       Thanks for playing! 🎮");
        System.out.println("=================================");

        sc.close();
    }

}
