import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class MenuItem {

    private int id;
    private String name;
    private double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.printf(
            "%d. %-20s ₹%.2f%n",
            id,
            name,
            price
        );
    }
}

class Order {

    private ArrayList<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double calculateBill() {

        double total = 0;

        for (MenuItem item : items) {
            total += item.getPrice();
        }

        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public void displayOrder() {

        if (items.isEmpty()) {

            System.out.println(
                "No items ordered."
            );

            return;
        }

        System.out.println("\n🍽️ ORDER DETAILS");

        for (MenuItem item : items) {

            System.out.printf(
                "• %-20s ₹%.2f%n",
                item.getName(),
                item.getPrice()
            );
        }

        System.out.printf(
            "Total: ₹%.2f%n",
            calculateBill()
        );
    }
}

public class RestaurantManagement {

    static void displayMenu(
            ArrayList<MenuItem> menu) {

        System.out.println("\n================================");
        System.out.println("          🍽️ MENU");
        System.out.println("================================");

        for (MenuItem item : menu) {
            item.display();
        }
    }

    static void showTables(
            HashMap<Integer, Order> tables) {

        System.out.println("\n================================");
        System.out.println("        🪑 TABLE STATUS");
        System.out.println("================================");

        for (int i = 1; i <= 5; i++) {

            if (tables.containsKey(i)
                    && tables.get(i).getItemCount() > 0) {

                System.out.println(
                    "Table " + i + " → Occupied"
                );

            } else {

                System.out.println(
                    "Table " + i + " → Available"
                );
            }
        }
    }

    static MenuItem findItem(
            ArrayList<MenuItem> menu,
            int id) {

        for (MenuItem item : menu) {

            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<MenuItem> menu =
            new ArrayList<>();

        menu.add(
            new MenuItem(1, "Pizza", 250)
        );

        menu.add(
            new MenuItem(2, "Burger", 150)
        );

        menu.add(
            new MenuItem(3, "Pasta", 180)
        );

        menu.add(
            new MenuItem(4, "Sandwich", 120)
        );

        menu.add(
            new MenuItem(5, "French Fries", 100)
        );

        menu.add(
            new MenuItem(6, "Cold Coffee", 90)
        );

        menu.add(
            new MenuItem(7, "Ice Cream", 80)
        );

        HashMap<Integer, Order> tables =
            new HashMap<>();

        double totalRevenue = 0;

        System.out.println("================================");
        System.out.println("    🍽️ RESTAURANT MANAGEMENT");
        System.out.println("================================");

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("              MENU");
            System.out.println("================================");

            System.out.println("1. 📋 View Food Menu");
            System.out.println("2. 🪑 Show Tables");
            System.out.println("3. 📝 Take Order");
            System.out.println("4. 🧾 View Table Bill");
            System.out.println("5. 💰 Generate Bill & Checkout");
            System.out.println("6. 📊 Show Revenue");
            System.out.println("7. 🚪 Exit");

            System.out.print("\nChoose option: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                displayMenu(menu);

            } else if (choice == 2) {

                showTables(tables);

            } else if (choice == 3) {

                showTables(tables);

                System.out.print(
                    "\nEnter table number (1-5): "
                );

                int tableNumber = sc.nextInt();

                if (tableNumber < 1
                        || tableNumber > 5) {

                    System.out.println(
                        "❌ Invalid table number!"
                    );

                    continue;
                }

                Order order =
                    tables.get(tableNumber);

                if (order == null) {

                    order = new Order();

                    tables.put(
                        tableNumber,
                        order
                    );
                }

                boolean ordering = true;

                while (ordering) {

                    displayMenu(menu);

                    System.out.println(
                        "\n0. Finish Order"
                    );

                    System.out.print(
                        "Enter item number: "
                    );

                    int itemId = sc.nextInt();

                    if (itemId == 0) {

                        ordering = false;

                    } else {

                        MenuItem item =
                            findItem(menu, itemId);

                        if (item != null) {

                            order.addItem(item);

                            System.out.println(
                                "✅ "
                                + item.getName()
                                + " added to order."
                            );

                        } else {

                            System.out.println(
                                "❌ Invalid item number!"
                            );
                        }
                    }
                }

            } else if (choice == 4) {

                System.out.print(
                    "\nEnter table number: "
                );

                int tableNumber = sc.nextInt();

                Order order =
                    tables.get(tableNumber);

                if (order != null) {

                    order.displayOrder();

                } else {

                    System.out.println(
                        "❌ No order found for this table."
                    );
                }

            } else if (choice == 5) {

                System.out.print(
                    "\nEnter table number: "
                );

                int tableNumber = sc.nextInt();

                Order order =
                    tables.get(tableNumber);

                if (order != null
                        && order.getItemCount() > 0) {

                    order.displayOrder();

                    double bill =
                        order.calculateBill();

                    System.out.printf(
                        "\n💰 Final Bill: ₹%.2f%n",
                        bill
                    );

                    System.out.println(
                        "✅ Payment successful!"
                    );

                    totalRevenue += bill;

                    tables.remove(tableNumber);

                } else {

                    System.out.println(
                        "❌ No active order found."
                    );
                }

            } else if (choice == 6) {

                System.out.println(
                    "\n📊 RESTAURANT REVENUE"
                );

                System.out.printf(
                    "💰 Total Revenue: ₹%.2f%n",
                    totalRevenue
                );

            } else if (choice == 7) {

                running = false;

                System.out.println(
                    "\n🚪 Restaurant closed."
                );

            } else {

                System.out.println(
                    "\n❌ Invalid choice!"
                );
            }
        }

        System.out.println("\n================================");
        System.out.println("       🏆 RESTAURANT REPORT");
        System.out.println("================================");

        System.out.printf(
            "💰 Total Revenue: ₹%.2f%n",
            totalRevenue
        );

        System.out.println(
            "🍽️ Thank you for using the simulator!"
        );

        sc.close();
    }
}