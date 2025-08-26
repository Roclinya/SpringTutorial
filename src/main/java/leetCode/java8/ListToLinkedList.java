package leetCode.java8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ListToLinkedList {

    public static void main(String[] args) {
        List<String> array = Arrays.asList("a", "b", "c");
        LinkedList<String> list = array.stream().map(e -> e.toUpperCase()).collect(Collectors.toCollection(LinkedList::new));

        //DESC
        List<Integer> sortedDesc = Stream.of(120,24,59,63,11,74)
                .sorted((n1,n2) -> n2.compareTo(n1))
                .collect(toList());
        System.out.println("sorted desc: " + sortedDesc);

    }
}
