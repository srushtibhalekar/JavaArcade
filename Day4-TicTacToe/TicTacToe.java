import java.util.Scanner;

public class TicTacToe {

    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    static char currentPlayer = 'X';

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("          ❌ TIC TAC TOE ⭕");
        System.out.println("=================================");

        boolean gameOver = false;

        while (!gameOver) {

            printBoard();

            System.out.println("\nPlayer " + currentPlayer + "'s turn");

            System.out.print("Enter row (1-3): ");
            int row = sc.nextInt() - 1;

            System.out.print("Enter column (1-3): ");
            int column = sc.nextInt() - 1;

            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("❌ Invalid position!");
                continue;
            }

            if (board[row][column] != ' ') {
                System.out.println("⚠️ Position already taken!");
                continue;
            }

            board[row][column] = currentPlayer;

            if (checkWinner()) {

                printBoard();

                System.out.println("\n🎉 Player " + currentPlayer + " Wins!");
                gameOver = true;

            } else if (isBoardFull()) {

                printBoard();

                System.out.println("\n🤝 It's a Draw!");
                gameOver = true;

            } else {

                switchPlayer();
            }
        }

        System.out.println("\nThanks for playing! 🎮");

        sc.close();
    }

    static void printBoard() {

        System.out.println();

        System.out.println("     1   2   3");
        System.out.println("   -------------");

        for (int i = 0; i < 3; i++) {

            System.out.print((i + 1) + "  |");

            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |");
            }

            System.out.println();
            System.out.println("   -------------");
        }
    }

    static void switchPlayer() {

        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    static boolean checkWinner() {

        // Rows
        for (int i = 0; i < 3; i++) {

            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) {

                return true;
            }
        }

        // Columns
        for (int i = 0; i < 3; i++) {

            if (board[0][i] != ' ' &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i]) {

                return true;
            }
        }

        // Main diagonal
        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {

            return true;
        }

        // Other diagonal
        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {

            return true;
        }

        return false;
    }

    static boolean isBoardFull() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }
}