import java.util.Random;
import java.util.Scanner;

public class NumberPuzzle {

    static int[][] board = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        shuffleBoard(random);

        int moves = 0;

        System.out.println("=================================");
        System.out.println("        🧩 NUMBER PUZZLE");
        System.out.println("=================================");
        System.out.println("Arrange the numbers from 1 to 8.");
        System.out.println("Use 0 as the empty space.");
        System.out.println("Example move: 2 3");
        System.out.println("Type -1 to quit.");

        while (true) {

            printBoard();

            if (isSolved()) {
                System.out.println("\n🎉 PUZZLE SOLVED!");
                System.out.println("Total moves: " + moves);
                break;
            }

            System.out.print("\nEnter row (1-3): ");
            int row = sc.nextInt();

            if (row == -1) {
                System.out.println("👋 Game ended.");
                break;
            }

            System.out.print("Enter column (1-3): ");
            int column = sc.nextInt();

            row--;
            column--;

            if (isValidMove(row, column)) {

                moveTile(row, column);
                moves++;

                System.out.println("✅ Move completed!");

            } else {

                System.out.println("❌ Invalid move!");
            }
        }

        sc.close();
    }

    static void printBoard() {

        System.out.println("\n+---+---+---+");

        for (int i = 0; i < 3; i++) {

            System.out.print("|");

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == 0) {
                    System.out.print("   |");
                } else {
                    System.out.print(" " + board[i][j] + " |");
                }
            }

            System.out.println();
            System.out.println("+---+---+---+");
        }
    }

    static void shuffleBoard(Random random) {

        for (int i = 0; i < 100; i++) {

            int row = random.nextInt(3);
            int column = random.nextInt(3);

            if (isValidMove(row, column)) {
                moveTile(row, column);
            }
        }
    }

    static boolean isValidMove(int row, int column) {

        if (row < 0 || row >= 3 || column < 0 || column >= 3) {
            return false;
        }

        int emptyRow = 0;
        int emptyColumn = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyColumn = j;
                }
            }
        }

        int rowDifference = Math.abs(row - emptyRow);
        int columnDifference = Math.abs(column - emptyColumn);

        return rowDifference + columnDifference == 1;
    }

    static void moveTile(int row, int column) {

        int emptyRow = 0;
        int emptyColumn = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyColumn = j;
                }
            }
        }

        board[emptyRow][emptyColumn] = board[row][column];
        board[row][column] = 0;
    }

    static boolean isSolved() {

        int number = 1;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (i == 2 && j == 2) {
                    return board[i][j] == 0;
                }

                if (board[i][j] != number) {
                    return false;
                }

                number++;
            }
        }

        return true;
    }
}