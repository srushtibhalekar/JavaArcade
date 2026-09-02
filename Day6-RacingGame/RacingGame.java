import java.util.Random;
import java.util.Scanner;

public class RacingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int playerPosition = 0;
        int computerPosition = 0;
        int finishLine = 30;

        System.out.println("=================================");
        System.out.println("        🏎️ JAVA RACING GAME");
        System.out.println("=================================");
        System.out.println("First to reach the finish line wins!");
        System.out.println("Press ENTER to race.");

        sc.nextLine();

        while (playerPosition < finishLine &&
               computerPosition < finishLine) {

            System.out.println("\n---------------------------------");

            System.out.print("Press ENTER to accelerate...");
            sc.nextLine();

            int playerMove = random.nextInt(5) + 1;
            int computerMove = random.nextInt(5) + 1;

            playerPosition += playerMove;
            computerPosition += computerMove;

            if (playerPosition > finishLine) {
                playerPosition = finishLine;
            }

            if (computerPosition > finishLine) {
                computerPosition = finishLine;
            }

            System.out.println("\n🏎️ You moved      : " + playerMove);
            System.out.println("🤖 Computer moved : " + computerMove);

            System.out.println("\nYou      : " + createTrack(playerPosition));
            System.out.println("Computer : " + createTrack(computerPosition));

            System.out.println("\nYour position      : "
                    + playerPosition + "/" + finishLine);

            System.out.println("Computer position  : "
                    + computerPosition + "/" + finishLine);
        }

        System.out.println("\n=================================");

        if (playerPosition >= finishLine &&
            computerPosition >= finishLine) {

            System.out.println("🤝 It's a DRAW!");

        } else if (playerPosition >= finishLine) {

            System.out.println("🏆 YOU WIN THE RACE!");

        } else {

            System.out.println("💻 COMPUTER WINS!");

        }

        System.out.println("=================================");
        System.out.println("Thanks for racing! 🏁");

        sc.close();
    }

    public static String createTrack(int position) {

        StringBuilder track = new StringBuilder();

        for (int i = 0; i < 30; i++) {

            if (i == position) {
                track.append("🏎️");
            } else {
                track.append("-");
            }
        }

        return track.toString();
    }
}