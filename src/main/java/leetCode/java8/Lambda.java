package leetCode.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda {
    public static void main(String[] args) {
        List<String> fruitNames = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

        Map<String, Integer> fruitMap = new HashMap<>();
        for (String f : fruitNames) {
            if (fruitMap.containsKey(f)) fruitMap.put(f, fruitMap.get(f) + 1);
            else fruitMap.put(f, 1);
        }
        System.out.println(fruitMap);

        Map<String, Long> result = fruitNames.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(result);
    }
}
