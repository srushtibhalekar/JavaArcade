import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class DeliveryOrder {
    int orderId;
    String customerName;
    String address;
    String item;
    double amount;
    double deliveryCharge;
    String status;
    String partner;

    DeliveryOrder(int orderId, String customerName, String address,
                  String item, double amount, double deliveryCharge) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.address = address;
        this.item = item;
        this.amount = amount;
        this.deliveryCharge = deliveryCharge;
        this.status = "Order Placed";
        this.partner = "Not Assigned";
    }

    double getTotal() {
        return amount + deliveryCharge;
    }

    void display() {
        System.out.println("----------------------------------------");
        System.out.println("Order ID       : " + orderId);
        System.out.println("Customer       : " + customerName);
        System.out.println("Address        : " + address);
        System.out.println("Item           : " + item);
        System.out.println("Item Amount    : ₹" + amount);
        System.out.println("Delivery Charge: ₹" + deliveryCharge);
        System.out.println("Total Amount   : ₹" + getTotal());
        System.out.println("Partner        : " + partner);
        System.out.println("Status         : " + status);
    }
}

public class DeliveryService {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<DeliveryOrder> orders = new ArrayList<>();
    static HashMap<String, String> partners = new HashMap<>();

    static int nextOrderId = 1001;
    static double totalRevenue = 0;

    static Random random = new Random();

    public static void main(String[] args) {

        partners.put("Aarav", "Available");
        partners.put("Priya", "Available");
        partners.put("Rahul", "Available");
        partners.put("Sneha", "Available");
        partners.put("Vikram", "Available");

        while (true) {

            System.out.println("\n========================================");
            System.out.println("       DELIVERY SERVICE SIMULATOR");
            System.out.println("========================================");
            System.out.println("1. Add Delivery Order");
            System.out.println("2. View All Orders");
            System.out.println("3. Assign Delivery Partner");
            System.out.println("4. Update Delivery Status");
            System.out.println("5. Cancel Order");
            System.out.println("6. View Delivery Partners");
            System.out.println("7. Delivery Report");
            System.out.println("8. Exit");
            System.out.println("========================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addOrder();
                    break;

                case 2:
                    viewOrders();
                    break;

                case 3:
                    assignPartner();
                    break;

                case 4:
                    updateStatus();
                    break;

                case 5:
                    cancelOrder();
                    break;

                case 6:
                    viewPartners();
                    break;

                case 7:
                    deliveryReport();
                    break;

                case 8:
                    System.out.println("\nThank you for using Delivery Service Simulator!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addOrder() {

        System.out.println("\n========== ADD DELIVERY ORDER ==========");

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter delivery address: ");
        String address = scanner.nextLine();

        System.out.print("Enter item name: ");
        String item = scanner.nextLine();

        System.out.print("Enter item amount: ₹");
        double amount = scanner.nextDouble();

        double deliveryCharge;

        if (amount >= 1000) {
            deliveryCharge = 0;
        } else if (amount >= 500) {
            deliveryCharge = 40;
        } else {
            deliveryCharge = 60;
        }

        DeliveryOrder order = new DeliveryOrder(
                nextOrderId++,
                customerName,
                address,
                item,
                amount,
                deliveryCharge
        );

        orders.add(order);

        totalRevenue += deliveryCharge;

        System.out.println("\nOrder created successfully!");
        System.out.println("Order ID: " + order.orderId);
        System.out.println("Delivery Charge: ₹" + deliveryCharge);
        System.out.println("Total Amount: ₹" + order.getTotal());
    }

    static void viewOrders() {

        System.out.println("\n========== ALL DELIVERY ORDERS ==========");

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (DeliveryOrder order : orders) {
            order.display();
        }
    }

    static DeliveryOrder findOrder(int orderId) {

        for (DeliveryOrder order : orders) {
            if (order.orderId == orderId) {
                return order;
            }
        }

        return null;
    }

    static void assignPartner() {

        System.out.println("\n========== ASSIGN DELIVERY PARTNER ==========");

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        DeliveryOrder order = findOrder(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        if (order.status.equals("Cancelled")) {
            System.out.println("Cannot assign partner to a cancelled order.");
            return;
        }

        System.out.println("\nAvailable Partners:");

        ArrayList<String> availablePartners = new ArrayList<>();

        for (String partner : partners.keySet()) {
            if (partners.get(partner).equals("Available")) {
                availablePartners.add(partner);
                System.out.println("- " + partner);
            }
        }

        if (availablePartners.isEmpty()) {
            System.out.println("No delivery partners available.");
            return;
        }

        System.out.print("Enter partner name: ");
        String partnerName = scanner.nextLine();

        if (!partners.containsKey(partnerName)) {
            System.out.println("Partner not found.");
            return;
        }

        if (!partners.get(partnerName).equals("Available")) {
            System.out.println("Partner is currently busy.");
            return;
        }

        order.partner = partnerName;
        order.status = "Out for Delivery";
        partners.put(partnerName, "Busy");

        System.out.println("Partner assigned successfully!");
    }

    static void updateStatus() {

        System.out.println("\n========== UPDATE DELIVERY STATUS ==========");

        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        DeliveryOrder order = findOrder(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        if (order.status.equals("Cancelled")) {
            System.out.println("Cancelled order cannot be updated.");
            return;
        }

        System.out.println("\n1. Order Placed");
        System.out.println("2. Preparing");
        System.out.println("3. Out for Delivery");
        System.out.println("4. Delivered");

        System.out.print("Choose new status: ");
        int choice = scanner.nextInt();

        switch (choice) {

            case 1:
                order.status = "Order Placed";
                break;

            case 2:
                order.status = "Preparing";
                break;

            case 3:
                order.status = "Out for Delivery";
                break;

            case 4:
                order.status = "Delivered";

                if (!order.partner.equals("Not Assigned")) {
                    partners.put(order.partner, "Available");
                }

                totalRevenue += order.amount;
                break;

            default:
                System.out.println("Invalid status.");
                return;
        }

        System.out.println("Delivery status updated successfully!");
    }

    static void cancelOrder() {

        System.out.println("\n========== CANCEL ORDER ==========");

        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();

        DeliveryOrder order = findOrder(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        if (order.status.equals("Delivered")) {
            System.out.println("Delivered order cannot be cancelled.");
            return;
        }

        if (order.status.equals("Cancelled")) {
            System.out.println("Order is already cancelled.");
            return;
        }

        order.status = "Cancelled";

        if (!order.partner.equals("Not Assigned")) {
            partners.put(order.partner, "Available");
        }

        System.out.println("Order cancelled successfully.");
    }

    static void viewPartners() {

        System.out.println("\n========== DELIVERY PARTNERS ==========");

        for (String partner : partners.keySet()) {
            System.out.println(partner + " : " + partners.get(partner));
        }
    }

    static void deliveryReport() {

        System.out.println("\n========== DELIVERY REPORT ==========");

        int placed = 0;
        int preparing = 0;
        int outForDelivery = 0;
        int delivered = 0;
        int cancelled = 0;

        double orderValue = 0;

        for (DeliveryOrder order : orders) {

            orderValue += order.getTotal();

            switch (order.status) {

                case "Order Placed":
                    placed++;
                    break;

                case "Preparing":
                    preparing++;
                    break;

                case "Out for Delivery":
                    outForDelivery++;
                    break;

                case "Delivered":
                    delivered++;
                    break;

                case "Cancelled":
                    cancelled++;
                    break;
            }
        }

        System.out.println("Total Orders       : " + orders.size());
        System.out.println("Order Placed       : " + placed);
        System.out.println("Preparing          : " + preparing);
        System.out.println("Out for Delivery   : " + outForDelivery);
        System.out.println("Delivered          : " + delivered);
        System.out.println("Cancelled          : " + cancelled);
        System.out.println("Total Order Value  : ₹" + orderValue);
        System.out.println("Total Revenue      : ₹" + totalRevenue);
    }
}