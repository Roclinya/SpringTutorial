package supplierAndConsumerAndFunctionInterface;

import java.util.function.Function;

public class FunctionExample {
    public int findLength() {
        Function<String, Integer> findLength = (text) -> text.length();
        int strLength = findLength.apply("bhairab");
        return strLength;
    }

    public static void main(String[] arg) {
        FunctionExample functionExample = new FunctionExample();
        System.out.println("The length of the string: " + functionExample.findLength());
    }
}
