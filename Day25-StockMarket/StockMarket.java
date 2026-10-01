import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Stock {

    private String symbol;
    private String company;
    private double price;

    public Stock(String symbol, String company, double price) {
        this.symbol = symbol;
        this.company = company;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompany() {
        return company;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice() {

        double change =
            (random.nextDouble() * 20) - 10;

        price += price * change / 100;

        if (price < 10) {
            price = 10;
        }
    }

    static Random random = new Random();

    public void display() {

        System.out.printf(
            "%-8s | %-20s | ₹%.2f%n",
            symbol,
            company,
            price
        );
    }
}

class Portfolio {

    private HashMap<String, Integer> holdings;

    public Portfolio() {
        holdings = new HashMap<>();
    }

    public void buy(String symbol, int quantity) {

        holdings.put(
            symbol,
            holdings.getOrDefault(symbol, 0) + quantity
        );
    }

    public boolean sell(String symbol, int quantity) {

        int current =
            holdings.getOrDefault(symbol, 0);

        if (current < quantity) {
            return false;
        }

        int remaining = current - quantity;

        if (remaining == 0) {
            holdings.remove(symbol);
        } else {
            holdings.put(symbol, remaining);
        }

        return true;
    }

    public int getQuantity(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    public HashMap<String, Integer> getHoldings() {
        return holdings;
    }
}

public class StockMarket {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static ArrayList<Stock> stocks =
        new ArrayList<>();

    static double cash = 100000;

    static void createStocks() {

        stocks.add(
            new Stock(
                "TCS",
                "Tata Consultancy",
                3500
            )
        );

        stocks.add(
            new Stock(
                "INFY",
                "Infosys",
                1800
            )
        );

        stocks.add(
            new Stock(
                "RELI",
                "Reliance Industries",
                2900
            )
        );

        stocks.add(
            new Stock(
                "HDFC",
                "HDFC Bank",
                1700
            )
        );

        stocks.add(
            new Stock(
                "WIPRO",
                "Wipro",
                550
            )
        );
    }

    static Stock findStock(String symbol) {

        for (Stock stock : stocks) {

            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }

    static void showStocks() {

        System.out.println("\n==============================================");
        System.out.println("             📈 STOCK MARKET");
        System.out.println("==============================================");

        System.out.printf(
            "%-8s | %-20s | %s%n",
            "Symbol",
            "Company",
            "Price"
        );

        System.out.println(
            "----------------------------------------------"
        );

        for (Stock stock : stocks) {
            stock.display();
        }
    }

    static void buyStock(Portfolio portfolio) {

        showStocks();

        System.out.print(
            "\nEnter stock symbol: "
        );

        String symbol =
            sc.next().toUpperCase();

        Stock stock = findStock(symbol);

        if (stock == null) {

            System.out.println(
                "❌ Stock not found!"
            );

            return;
        }

        System.out.print(
            "Enter quantity: "
        );

        int quantity = sc.nextInt();

        if (quantity <= 0) {

            System.out.println(
                "❌ Quantity must be greater than zero."
            );

            return;
        }

        double total =
            stock.getPrice() * quantity;

        if (cash < total) {

            System.out.println(
                "❌ Insufficient cash!"
            );

            return;
        }

        cash -= total;

        portfolio.buy(
            stock.getSymbol(),
            quantity
        );

        System.out.printf(
            "\n✅ Purchased %d shares of %s%n",
            quantity,
            stock.getSymbol()
        );

        System.out.printf(
            "💰 Amount Paid: ₹%.2f%n",
            total
        );

        System.out.printf(
            "💵 Remaining Cash: ₹%.2f%n",
            cash
        );
    }

    static void sellStock(Portfolio portfolio) {

        showPortfolio(portfolio);

        System.out.print(
            "\nEnter stock symbol: "
        );

        String symbol =
            sc.next().toUpperCase();

        Stock stock = findStock(symbol);

        if (stock == null) {

            System.out.println(
                "❌ Stock not found!"
            );

            return;
        }

        int owned =
            portfolio.getQuantity(
                stock.getSymbol()
            );

        if (owned == 0) {

            System.out.println(
                "❌ You don't own this stock."
            );

            return;
        }

        System.out.print(
            "Enter quantity to sell: "
        );

        int quantity = sc.nextInt();

        if (quantity <= 0) {

            System.out.println(
                "❌ Invalid quantity."
            );

            return;
        }

        if (!portfolio.sell(
                stock.getSymbol(),
                quantity)) {

            System.out.println(
                "❌ You don't have enough shares."
            );

            return;
        }

        double amount =
            stock.getPrice() * quantity;

        cash += amount;

        System.out.printf(
            "\n✅ Sold %d shares of %s%n",
            quantity,
            stock.getSymbol()
        );

        System.out.printf(
            "💰 Sale Amount: ₹%.2f%n",
            amount
        );
    }

    static void updateMarket() {

        for (Stock stock : stocks) {
            stock.updatePrice();
        }

        System.out.println(
            "\n📊 Market prices updated!"
        );
    }

    static double calculatePortfolioValue(
            Portfolio portfolio) {

        double value = 0;

        for (String symbol :
                portfolio.getHoldings().keySet()) {

            Stock stock =
                findStock(symbol);

            int quantity =
                portfolio.getQuantity(symbol);

            if (stock != null) {

                value +=
                    stock.getPrice() * quantity;
            }
        }

        return value;
    }

    static void showPortfolio(
            Portfolio portfolio) {

        System.out.println("\n================================");
        System.out.println("          💼 PORTFOLIO");
        System.out.println("================================");

        if (portfolio.getHoldings().isEmpty()) {

            System.out.println(
                "No stocks owned."
            );

        } else {

            for (String symbol :
                    portfolio.getHoldings().keySet()) {

                Stock stock =
                    findStock(symbol);

                int quantity =
                    portfolio.getQuantity(symbol);

                double value =
                    stock.getPrice() * quantity;

                System.out.printf(
                    "%s | Shares: %d | Value: ₹%.2f%n",
                    symbol,
                    quantity,
                    value
                );
            }
        }

        double portfolioValue =
            calculatePortfolioValue(portfolio);

        System.out.printf(
            "\n💼 Portfolio Value: ₹%.2f%n",
            portfolioValue
        );

        System.out.printf(
            "💵 Cash: ₹%.2f%n",
            cash
        );

        System.out.printf(
            "💰 Total Wealth: ₹%.2f%n",
            cash + portfolioValue
        );
    }

    static void marketEvent() {

        int event = random.nextInt(4);

        System.out.println(
            "\n📰 MARKET NEWS"
        );

        if (event == 0) {

            System.out.println(
                "📈 Technology sector is performing strongly."
            );

        } else if (event == 1) {

            System.out.println(
                "📉 Market confidence has decreased."
            );

        } else if (event == 2) {

            System.out.println(
                "🏦 Banking sector receives positive news."
            );

        } else {

            System.out.println(
                "🌍 Global market conditions remain uncertain."
            );
        }
    }

    static void showReport(
            Portfolio portfolio) {

        double portfolioValue =
            calculatePortfolioValue(portfolio);

        double totalWealth =
            cash + portfolioValue;

        double profit =
            totalWealth - 100000;

        System.out.println("\n================================");
        System.out.println("       📊 TRADING REPORT");
        System.out.println("================================");

        System.out.printf(
            "💵 Cash: ₹%.2f%n",
            cash
        );

        System.out.printf(
            "💼 Portfolio: ₹%.2f%n",
            portfolioValue
        );

        System.out.printf(
            "💰 Total Wealth: ₹%.2f%n",
            totalWealth
        );

        if (profit >= 0) {

            System.out.printf(
                "📈 Profit: ₹%.2f%n",
                profit
            );

        } else {

            System.out.printf(
                "📉 Loss: ₹%.2f%n",
                Math.abs(profit)
            );
        }
    }

    public static void main(String[] args) {

        createStocks();

        Portfolio portfolio =
            new Portfolio();

        System.out.println("================================");
        System.out.println("       📈 STOCK MARKET");
        System.out.println("          SIMULATOR");
        System.out.println("================================");

        System.out.println(
            "💵 Starting Capital: ₹100000"
        );

        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("             MENU");
            System.out.println("================================");

            System.out.println("1. 📈 View Stocks");
            System.out.println("2. 🛒 Buy Stocks");
            System.out.println("3. 💰 Sell Stocks");
            System.out.println("4. 💼 View Portfolio");
            System.out.println("5. 🔄 Update Market");
            System.out.println("6. 📰 Market News");
            System.out.println("7. 📊 Trading Report");
            System.out.println("8. 🚪 Exit");

            System.out.print(
                "\nChoose option: "
            );

            int choice = sc.nextInt();

            if (choice == 1) {

                showStocks();

            } else if (choice == 2) {

                buyStock(portfolio);

            } else if (choice == 3) {

                sellStock(portfolio);

            } else if (choice == 4) {

                showPortfolio(portfolio);

            } else if (choice == 5) {

                updateMarket();

            } else if (choice == 6) {

                marketEvent();

            } else if (choice == 7) {

                showReport(portfolio);

            } else if (choice == 8) {

                running = false;

                System.out.println(
                    "\n📈 Trading session closed."
                );

                showReport(portfolio);

            } else {

                System.out.println(
                    "\n❌ Invalid option!"
                );
            }
        }

        System.out.println(
            "\n👋 Thank you for using Stock Market Simulator!"
        );

        sc.close();
    }
}