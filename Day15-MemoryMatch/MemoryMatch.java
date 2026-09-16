import java.util.Random;
import java.util.Scanner;

public class MemoryMatch {

    static char[][] board = {
        {'A', 'B', 'C', 'D'},
        {'E', 'F', 'G', 'H'},
        {'A', 'B', 'C', 'D'},
        {'E', 'F', 'G', 'H'}
    };

    static boolean[][] matched = new boolean[4][4];

    static void shuffleBoard() {

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int r = random.nextInt(4);
                int c = random.nextInt(4);

                char temp = board[i][j];
                board[i][j] = board[r][c];
                board[r][c] = temp;
            }
        }
    }

    static void displayBoard() {

        System.out.println("\n    1 2 3 4");
        System.out.println("   --------");

        for (int i = 0; i < 4; i++) {

            System.out.print((i + 1) + " | ");

            for (int j = 0; j < 4; j++) {

                if (matched[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("* ");
                }
            }

            System.out.println();
        }
    }

    static void displayTemporary(int r1, int c1, int r2, int c2) {

        System.out.println("\n    1 2 3 4");
        System.out.println("   --------");

        for (int i = 0; i < 4; i++) {

            System.out.print((i + 1) + " | ");

            for (int j = 0; j < 4; j++) {

                if (matched[i][j]
                        || (i == r1 && j == c1)
                        || (i == r2 && j == c2)) {

                    System.out.print(board[i][j] + " ");

                } else {

                    System.out.print("* ");
                }
            }

            System.out.println();
        }
    }

    static boolean allMatched() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (!matched[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        shuffleBoard();

        int moves = 0;

        System.out.println("================================");
        System.out.println("       🧠 MEMORY MATCH");
        System.out.println("================================");

        System.out.println("\nMatch all identical letters!");
        System.out.println("Choose row and column for each card.");

        while (!allMatched()) {

            displayBoard();

            System.out.println("\n🔹 First Card");

            System.out.print("Enter row (1-4): ");
            int r1 = sc.nextInt() - 1;

            System.out.print("Enter column (1-4): ");
            int c1 = sc.nextInt() - 1;

            if (r1 < 0 || r1 > 3 || c1 < 0 || c1 > 3
                    || matched[r1][c1]) {

                System.out.println("❌ Invalid or already matched card!");
                continue;
            }

            System.out.println("\n🔹 Second Card");

            System.out.print("Enter row (1-4): ");
            int r2 = sc.nextInt() - 1;

            System.out.print("Enter column (1-4): ");
            int c2 = sc.nextInt() - 1;

            if (r2 < 0 || r2 > 3 || c2 < 0 || c2 > 3
                    || matched[r2][c2]
                    || (r1 == r2 && c1 == c2)) {

                System.out.println("❌ Invalid card selection!");
                continue;
            }

            moves++;

            displayTemporary(r1, c1, r2, c2);

            if (board[r1][c1] == board[r2][c2]) {

                System.out.println("\n🎉 MATCH FOUND!");

                matched[r1][c1] = true;
                matched[r2][c2] = true;

            } else {

                System.out.println("\n❌ Not a match!");
                System.out.println("Try to remember the positions.");
            }
        }

        System.out.println("\n================================");
        System.out.println("          🏆 YOU WON!");
        System.out.println("================================");

        displayBoard();

        System.out.println("\n🧠 Total Moves: " + moves);

        if (moves <= 8) {
            System.out.println("🔥 Perfect Memory!");
        } else if (moves <= 12) {
            System.out.println("⭐ Great Memory!");
        } else {
            System.out.println("👏 Good Job!");
        }

        System.out.println("\nThanks for playing! 🧠");

        sc.close();
    }
}