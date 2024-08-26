import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private double balance;
    private Map<String, Integer> holdings;

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
        this.holdings = new HashMap<>();
    }

    public double getBalance() {
        return balance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = price * quantity;
        if (cost <= balance) {
            balance -= cost;
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + symbol + " at $" + price + " per share.");
        } else {
            System.out.println("Insufficient funds to buy " + quantity + " shares of " + symbol + ".");
        }
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (holdings.containsKey(symbol) && holdings.get(symbol) >= quantity) {
            balance += price * quantity;
            holdings.put(symbol, holdings.get(symbol) - quantity);
            if (holdings.get(symbol) == 0) {
                holdings.remove(symbol);
            }
            System.out.println("Sold " + quantity + " shares of " + symbol + " at $" + price + " per share.");
        } else {
            System.out.println("Insufficient shares to sell " + quantity + " shares of " + symbol + ".");
        }
    }

    public void viewPortfolio(Map<String, Stock> market) {
        System.out.println("\nPortfolio Summary:");
        System.out.println("Balance: $" + balance);
        double totalValue = balance;
        for (String symbol : holdings.keySet()) {
            int quantity = holdings.get(symbol);
            double stockPrice = market.get(symbol).getPrice();
            double stockValue = stockPrice * quantity;
            totalValue += stockValue;
            System.out.println(symbol + ": " + quantity + " shares, Current Value: $" + stockValue + " (Price per Share: $" + stockPrice + ")");
        }
        System.out.println("Total Portfolio Value: $" + totalValue);
    }
}

