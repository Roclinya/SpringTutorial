package leetCode.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest {
    //https://www.geeksforgeeks.org/stream-flatmap-java-examples/

    public static void main(String[] args) {
        // Creating a List of Strings
        List<String> listStr = Arrays.asList("Geeks", "GFG", "GeeksforGeeks", "gfg");

        // Using Stream flatMap(Function mapper)
        listStr.stream()
                .flatMap(str -> Stream.of(str.charAt(2)))
//                .map(str -> Stream.of(str.charAt(2)))
                .forEach(System.out::println);


        // This example demonstrates how flatMap flattens nested lists into a single list.
        // Using map would keep the nested structure intact.

        // Creating a list of prime numbers
        List<Integer> PrimeNumbers = Arrays.asList(5, 7, 11, 13);
        // Creating a list of odd numbers
        List<Integer> OddNumbers = Arrays.asList(1, 3, 5);
        // Creating a list of even numbers
        List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8);

        // Combining the above lists into a list of lists
        List<List<Integer>> listOfListofInts = Arrays.asList(PrimeNumbers, OddNumbers, EvenNumbers);

        // Printing the structure before flattening
        System.out.println("The Structure before flattening is : " + listOfListofInts);

        // Flattening the list of lists into a single list using flatMap
        // use case: mapper function with multiple values
        List<Integer> listofInts = listOfListofInts.stream()
                .flatMap(list -> list.stream()) //因為flatMap mapper 需要繼承 extends Stream
                .collect(Collectors.toList());

        // Printing the structure after flattening
        System.out.println("The Structure after flattening is : " + listofInts);
    }


}
