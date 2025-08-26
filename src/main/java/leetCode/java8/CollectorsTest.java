package leetCode.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;

/*
* 參考： https://www.baeldung.com/java-collectors
* */
public class CollectorsTest {
    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
//        ToMap
        Map<String, Integer> resultToMap = givenList.stream()
                .collect(toMap(Function.identity(), String::length)); //keyMapper() and valueMapper().
        System.out.println("ToMap:"+resultToMap);
//        Joining
        String resultJoin = givenList.stream()
                .collect(joining(" "));
        System.out.println("Joining:"+resultJoin);

//        groupingBy
        Map<Integer, Set<String>> resultGroupBy = givenList.stream()
                .collect(groupingBy(String::length, toSet()));
        System.out.println("groupingBy:"+resultGroupBy);
    }
}
