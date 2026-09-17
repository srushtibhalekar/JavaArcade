import java.util.Random;
import java.util.Scanner;

class Player {

    private int row;
    private int col;
    private int health;
    private int coins;

    public Player() {
        row = 0;
        col = 0;
        health = 100;
        coins = 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getHealth() {
        return health;
    }

    public int getCoins() {
        return coins;
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void takeDamage(int damage) {
        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

public class DungeonEscape {

    static char[][] dungeon = {
        {'P', '.', '#', '.', '.'},
        {'.', '.', '#', '.', '#'},
        {'#', '.', '.', '.', '.'},
        {'.', '#', '#', '.', '.'},
        {'.', '.', '.', '.', 'T'}
    };

    static void displayDungeon(Player player) {

        System.out.println("\n🏰 DUNGEON MAP");
        System.out.println("-------------");

        for (int i = 0; i < dungeon.length; i++) {

            for (int j = 0; j < dungeon[i].length; j++) {

                if (i == player.getRow()
                        && j == player.getCol()) {

                    System.out.print("P ");

                } else if (dungeon[i][j] == '#') {

                    System.out.print("# ");

                } else if (dungeon[i][j] == 'T') {

                    System.out.print("T ");

                } else {

                    System.out.print(". ");
                }
            }

            System.out.println();
        }

        System.out.println("\nP = Player");
        System.out.println("# = Wall");
        System.out.println("T = Treasure");
    }

    static boolean validMove(int row, int col) {

        if (row < 0 || row >= dungeon.length) {
            return false;
        }

        if (col < 0 || col >= dungeon[0].length) {
            return false;
        }

        if (dungeon[row][col] == '#') {
            return false;
        }

        return true;
    }

    static void randomEvent(Player player, Random random) {

        int event = random.nextInt(4);

        if (event == 0) {

            int damage = random.nextInt(16) + 5;

            player.takeDamage(damage);

            System.out.println(
                "👹 A monster attacked you!"
            );

            System.out.println(
                "💔 Health lost: " + damage
            );

        } else if (event == 1) {

            int coins = random.nextInt(31) + 10;

            player.addCoins(coins);

            System.out.println(
                "💰 You found " + coins + " coins!"
            );

        } else if (event == 2) {

            System.out.println(
                "✨ You found a healing potion!"
            );

            int oldHealth = player.getHealth();

            player.takeDamage(-20);

            if (player.getHealth() > 100) {
                player.takeDamage(
                    -(100 - player.getHealth())
                );
            }

            System.out.println(
                "❤️ Health: "
                + oldHealth
                + " → "
                + player.getHealth()
            );

        } else {

            System.out.println(
                "🌙 The dungeon is quiet..."
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        Player player = new Player();

        System.out.println("================================");
        System.out.println("       🏰 DUNGEON ESCAPE");
        System.out.println("================================");

        System.out.println("\nYou are trapped inside a dungeon.");
        System.out.println("Find the treasure and escape!");
        System.out.println("Avoid walls and survive the dungeon.");

        boolean gameRunning = true;

        while (gameRunning && player.isAlive()) {

            displayDungeon(player);

            System.out.println("\n❤️ Health: " + player.getHealth());
            System.out.println("💰 Coins: " + player.getCoins());

            System.out.println("\nMove:");
            System.out.println("W = Up");
            System.out.println("S = Down");
            System.out.println("A = Left");
            System.out.println("D = Right");
            System.out.println("Q = Quit");

            System.out.print("\nEnter move: ");
            char move = sc.next().toUpperCase().charAt(0);

            int newRow = player.getRow();
            int newCol = player.getCol();

            if (move == 'W') {
                newRow--;
            } else if (move == 'S') {
                newRow++;
            } else if (move == 'A') {
                newCol--;
            } else if (move == 'D') {
                newCol++;
            } else if (move == 'Q') {

                System.out.println(
                    "\n🚪 You left the dungeon."
                );

                gameRunning = false;
                continue;

            } else {

                System.out.println(
                    "❌ Invalid movement!"
                );

                continue;
            }

            if (!validMove(newRow, newCol)) {

                System.out.println(
                    "🧱 You cannot move there!"
                );

                continue;
            }

            player.move(newRow, newCol);

            System.out.println(
                "\n🚶 You moved to a new location."
            );

            if (dungeon[newRow][newCol] == 'T') {

                System.out.println("\n💎 TREASURE FOUND!");
                System.out.println(
                    "🎉 You escaped the dungeon!"
                );

                player.addCoins(500);

                System.out.println(
                    "💰 Treasure reward: 500 coins"
                );

                gameRunning = false;

            } else {

                randomEvent(player, random);
            }
        }

        System.out.println("\n================================");
        System.out.println("          🏆 GAME OVER");
        System.out.println("================================");

        if (!player.isAlive()) {

            System.out.println(
                "💀 You were defeated in the dungeon."
            );

        } else if (player.getRow() == 4
                && player.getCol() == 4) {

            System.out.println(
                "🏆 DUNGEON ESCAPE SUCCESSFUL!"
            );

        } else {

            System.out.println(
                "🚪 You ended the adventure."
            );
        }

        System.out.println(
            "❤️ Final Health: "
            + player.getHealth()
        );

        System.out.println(
            "💰 Final Coins: "
            + player.getCoins()
        );

        System.out.println(
            "\nThanks for playing! 🏰"
        );

        sc.close();
    }
}