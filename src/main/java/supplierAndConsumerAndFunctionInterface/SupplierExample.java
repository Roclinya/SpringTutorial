package supplierAndConsumerAndFunctionInterface;

import java.util.Random;
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        // Create a Supplier to generate random numbers
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(100);

        // Get a random number from the Supplier
        int randomNumber = randomSupplier.get();
        System.out.println("Random Number: " + randomNumber);


        Supplier<String> supplier = () -> "Hello, Supplier!";

        String result = supplier.get();
        System.out.println(result);  // Output: Hello, Supplier!
    }
}
