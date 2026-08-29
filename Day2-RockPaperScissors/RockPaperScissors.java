import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int playerScore = 0;
        int computerScore = 0;

        System.out.println("=================================");
        System.out.println("      ✊ ROCK PAPER SCISSORS ✌️");
        System.out.println("=================================");

        while (true) {

            System.out.println("\n1. Rock");
            System.out.println("2. Paper");
            System.out.println("3. Scissors");
            System.out.println("4. Exit");

            System.out.print("\nEnter your choice: ");
            int player = sc.nextInt();

            if (player == 4) {
                break;
            }

            if (player < 1 || player > 3) {
                System.out.println("❌ Invalid choice!");
                continue;
            }

            int computer = random.nextInt(3) + 1;

            String playerChoice = getChoice(player);
            String computerChoice = getChoice(computer);

            System.out.println("\nYou chose      : " + playerChoice);
            System.out.println("Computer chose : " + computerChoice);

            if (player == computer) {

                System.out.println("🤝 It's a Draw!");

            } else if (
                    (player == 1 && computer == 3) ||
                    (player == 2 && computer == 1) ||
                    (player == 3 && computer == 2)
            ) {

                System.out.println("🎉 You Win!");
                playerScore++;

            } else {

                System.out.println("💻 Computer Wins!");
                computerScore++;
            }

            System.out.println("\nScore");
            System.out.println("You      : " + playerScore);
            System.out.println("Computer : " + computerScore);
        }

        System.out.println("\n=================================");
        System.out.println("          FINAL SCORE");
        System.out.println("=================================");
        System.out.println("You      : " + playerScore);
        System.out.println("Computer : " + computerScore);
        System.out.println("\nThanks for playing! 🎮");

        sc.close();
    }

    public static String getChoice(int choice) {

        switch (choice) {

            case 1:
                return "Rock";

            case 2:
                return "Paper";

            case 3:
                return "Scissors";

            default:
                return "Unknown";
        }
    }
}