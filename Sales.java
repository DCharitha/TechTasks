import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Sales {
    
    public static void main(String[] args) {
        Item[] basket = new Item[] {
            new Item("book", 12.49, true),
            new Item("music CD", 14.99, false),
            new Item("chocolate bar", 0.85, true)
        };
        
        int salesTaxRate = 10;
        int importDutyRate = 5;
        
        double salesTax = 0;
        double totalPrice = 0;
        
        for (Item item : basket) {
            double tax = 0;
            if (!item.exempt) {
                tax = Math.round(salesTaxRate / 100.0 * item.price * 100.0) / 100.0;
                tax = Math.ceil(tax / 0.05) * 0.05;
                salesTax += tax;
            }
            
            double itemTotalPrice = item.price + tax;
            totalPrice += itemTotalPrice;
            
            System.out.println("1 " + item.name + ": " + formatPrice(itemTotalPrice));
        }
        
        System.out.println("\nSales Taxes: " + formatPrice(salesTax));
        System.out.println("Total: " + formatPrice(totalPrice));
    }
    
    public static String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(price);
    }
}

class Item {
    String name;
    double price;
    boolean exempt;
    
    public Item(String name, double price, boolean exempt) {
        this.name = name;
        this.price = price;
        this.exempt = exempt;
    }
}
