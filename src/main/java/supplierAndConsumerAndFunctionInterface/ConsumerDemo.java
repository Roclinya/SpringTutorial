package supplierAndConsumerAndFunctionInterface;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ConsumerDemo {
    public void consumerExample() {
        Consumer<String> consumer = (name) -> System.out.println("This is my name ->  " + name);
        consumer.accept("bhairab");
    }

    // consumer with side effect
    public void consumerExample2() {

        ArrayList<Integer> number = new ArrayList<>();
        number.add(2);
        number.add(5);
        number.add(10);
        number.add(14);
        number.add(9);
        Consumer<Integer> consumer = (num) -> printItem(num);

        for (Integer num : number) {
            consumer.accept(num);
        }
    }

    private void printItem(Integer num) {
        System.out.println("The number is " + num);
    }

    public static void main(String[] args) {
        ConsumerDemo consumerDemo = new ConsumerDemo();
        consumerDemo.consumerExample();

        consumerDemo.consumerExample2();
    }
}
