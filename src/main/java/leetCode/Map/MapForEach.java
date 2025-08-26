package leetCode.Map;

import java.util.HashMap;
import java.util.Map;

public class MapForEach {
    public static void main(String[] args) {

        // https://malagege.github.io/blog/posts/Java-HashMap-foreach-loop/
        // Java 中使用 foreach 迴圈來遍歷 HashMap
        Map<String, String> map = new HashMap<>();
        map.put("Apple", "蘋果");
        map.put("Banana", "香蕉");
        map.put("Cherry", "櫻桃");

// 使用 foreach 迴圈遍歷 HashMap
        //Performance considerations for keySet() and entrySet() of Map
        //https://stackoverflow.com/questions/3870064/performance-considerations-for-keyset-and-entryset-of-map
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        for (var entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        map.forEach((key, value) -> System.out.println(key + " : " + value));



    }
}
