import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCart {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Select language
        System.out.println("Select a language: ");
        System.out.println("1. Farsi");
        System.out.println("2. Finnish");
        System.out.println("3. Japanese");
        System.out.println("4. English");

        int option = scanner.nextInt();

        Locale locale;
        switch (option)
        {
            case 1:
            {
                locale = new Locale("fa", "IR");
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
            messages = ResourceBundle.getBundle("messages", locale);

        }catch (MissingResourceException e)
        {
            System.out.println("Language not found");
            messages = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        }


        scanner.close();
    }
}