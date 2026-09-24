import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Car {

    private String name;
    private int position;
    private int speed;
    private int fuel;

    public Car(String name) {
        this.name = name;
        position = 0;
        speed = 0;
        fuel = 100;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getFuel() {
        return fuel;
    }

    public void accelerate(Random random) {

        if (fuel <= 0) {
            System.out.println("⛽ No fuel!");
            return;
        }

        speed = random.nextInt(21) + 20;

        position += speed;

        fuel -= 10;

        if (fuel < 0) {
            fuel = 0;
        }
    }

    public void brake() {

        speed = 0;

        System.out.println(
            "🛑 You slowed down."
        );
    }

    public void refuel() {

        fuel = 100;

        System.out.println(
            "⛽ Fuel refilled!"
        );
    }

    public void showStatus() {

        System.out.println(
            "🏎️ " + name
            + " | Position: " + position
            + " | Fuel: " + fuel
        );
    }
}

public class RacingChampionship {

    static void showTrack(
            ArrayList<Car> cars) {

        System.out.println("\n🏁 RACE TRACK");
        System.out.println("--------------------------------");

        for (Car car : cars) {

            int blocks =
                Math.min(car.getPosition() / 5, 30);

            System.out.print(
                car.getName() + " | "
            );

            for (int i = 0; i < blocks; i++) {
                System.out.print("=");
            }

            System.out.println("🏎️");
        }

        System.out.println(
            "🏁 Finish Line = 150"
        );
    }

    static Car findWinner(
            ArrayList<Car> cars) {

        Car winner = cars.get(0);

        for (Car car : cars) {

            if (car.getPosition()
                    > winner.getPosition()) {

                winner = car;
            }
        }

        return winner;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        ArrayList<Car> cars =
            new ArrayList<>();

        System.out.println("================================");
        System.out.println("      🏎️ RACING CHAMPIONSHIP");
        System.out.println("================================");

        System.out.print(
            "Enter your car name: "
        );

        String playerName = sc.nextLine();

        Car player =
            new Car(playerName);

        Car redRocket =
            new Car("Red Rocket");

        Car speedKing =
            new Car("Speed King");

        Car thunder =
            new Car("Thunder");

        cars.add(player);
        cars.add(redRocket);
        cars.add(speedKing);
        cars.add(thunder);

        System.out.println(
            "\n🏁 Race distance: 150"
        );

        System.out.println(
            "First car to reach 150 wins!"
        );

        boolean racing = true;
        int round = 1;

        while (racing) {

            System.out.println("\n================================");
            System.out.println(
                "            ROUND " + round
            );
            System.out.println("================================");

            showTrack(cars);

            System.out.println("\nYour Car:");
            player.showStatus();

            System.out.println("\nChoose action:");
            System.out.println("1. 🚀 Accelerate");
            System.out.println("2. 🛑 Brake");
            System.out.println("3. ⛽ Refuel");
            System.out.println("4. 📊 Status");
            System.out.println("5. 🚪 Exit");

            System.out.print("\nEnter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                int oldPosition =
                    player.getPosition();

                player.accelerate(random);

                System.out.println(
                    "\n🚀 You accelerated!"
                );

                System.out.println(
                    "📍 Position: "
                    + oldPosition
                    + " → "
                    + player.getPosition()
                );

                if (player.getPosition() >= 150) {

                    racing = false;
                    break;
                }

                for (int i = 1; i < cars.size(); i++) {

                    Car opponent = cars.get(i);

                    opponent.accelerate(random);
                }

                for (Car car : cars) {

                    if (car.getPosition() >= 150) {

                        racing = false;
                        break;
                    }
                }

            } else if (choice == 2) {

                player.brake();

                for (int i = 1; i < cars.size(); i++) {

                    cars.get(i).accelerate(random);
                }

            } else if (choice == 3) {

                player.refuel();

            } else if (choice == 4) {

                System.out.println(
                    "\n📊 CURRENT STATUS"
                );

                for (Car car : cars) {
                    car.showStatus();
                }

            } else if (choice == 5) {

                System.out.println(
                    "\n🚪 You left the race."
                );

                racing = false;

            } else {

                System.out.println(
                    "\n❌ Invalid choice!"
                );
            }

            round++;
        }

        System.out.println("\n================================");
        System.out.println("          🏆 RACE RESULT");
        System.out.println("================================");

        showTrack(cars);

        Car winner = findWinner(cars);

        System.out.println(
            "\n🏆 Winner: "
            + winner.getName()
        );

        if (winner == player) {

            System.out.println(
                "🎉 CONGRATULATIONS! YOU WON!"
            );

        } else {

            System.out.println(
                "🏎️ Better luck next race!"
            );
        }

        System.out.println(
            "\nThanks for racing! 🏁"
        );

        sc.close();
    }
}