import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCart {
    public static float itemCalculator(int quantity, float price)
    {
        return quantity * price;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Select language
        System.out.println("Select a language: ");
        System.out.println("1. Swedish");
        System.out.println("2. Finnish");
        System.out.println("3. Japanese");
        System.out.println("4. English");

        int option = scanner.nextInt();

        Locale locale;
        switch (option)
        {
            case 1:
            {
                locale = new Locale("sv", "SE");
                break;
            }
            case 2:
            {
                locale = new Locale("fi", "FI");
                break;
            }
            case 3:
            {
                locale = new Locale("ja", "JP");
                break;
            }
            case 4:
            {
                locale = new Locale("en", "US");
                break;
            }
            default:
            {
                System.out.println("Invalid selection! Defaulting to English.");
                locale = new Locale("en", "US");
                break;
            }
        }

        ResourceBundle messages;
        try
        {
            messages = ResourceBundle.getBundle("MessagesBundle", locale);

        }catch (MissingResourceException e)
        {
            System.out.println("Language not found");
            messages = ResourceBundle.getBundle("MessagesBundle", new Locale("en", "US"));
        }
        System.out.println(messages.getString("inputItemNr"));
        int nrItems = scanner.nextInt();
        float total = 0;
        for (int i = 0; i < nrItems; i++)
        {
            System.out.println(messages.getString("inputPrice"));
            int quantity = scanner.nextInt();
            System.out.println(messages.getString("inputQuantity"));
            float price = scanner.nextFloat();
            total += itemCalculator(quantity, price);
            System.out.println();
        }
        System.out.println(messages.getString("outputCost") + String.format("%.2f",total));
        scanner.close();
    }
}