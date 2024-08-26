import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio(10000.00);  // Initial balance
        Map<String, Stock> market = new HashMap<>();

        // Initialize some stocks
        market.put("AAPL", new Stock("AAPL", 150.00));
        market.put("GOOGL", new Stock("GOOGL", 2800.00));
        market.put("AMZN", new Stock("AMZN", 3400.00));
        market.put("MSFT", new Stock("MSFT", 300.00));

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewMarketData(market);
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = scanner.next().toUpperCase();
                    if (market.containsKey(buySymbol)) {
                        System.out.print("Enter quantity to buy: ");
                        int quantityToBuy = scanner.nextInt();
                        portfolio.buyStock(buySymbol, quantityToBuy, market.get(buySymbol).getPrice());
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = scanner.next().toUpperCase();
                    if (market.containsKey(sellSymbol)) {
                        System.out.print("Enter quantity to sell: ");
                        int quantityToSell = scanner.nextInt();
                        portfolio.sellStock(sellSymbol, quantityToSell, market.get(sellSymbol).getPrice());
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    portfolio.viewPortfolio(market);
                    break;
                case 5:
                    System.out.println("Exiting the platform.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            // Simulate market price fluctuation
            simulateMarket(market);
        }
    }

    public static void viewMarketData(Map<String, Stock> market) {
        System.out.println("\nMarket Data:");
        for (Stock stock : market.values()) {
            System.out.println(stock.getSymbol() + ": $" + stock.getPrice());
        }
    }

    public static void simulateMarket(Map<String, Stock> market) {
        Random random = new Random();
        for (Stock stock : market.values()) {
            double changePercent = (random.nextDouble() - 0.5) * 0.1;  // Random price change between -5% to +5%
            double newPrice = stock.getPrice() * (1 + changePercent);
            stock.setPrice(Math.round(newPrice * 100.0) / 100.0);  // Round to 2 decimal places
        }
    }
}
