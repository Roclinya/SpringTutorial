package leetCode.Map;

import leetCode.java8.TypeDto;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsToMap02 {

    public static void main(String[] args) {
        List<Map.Entry<String, TypeDto>> typeList = getEntries();

        // 使用流對List進行排序
        List<Map.Entry<String, TypeDto>> sortedTypeList = typeList.stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getSort()))
                .collect(Collectors.toList());

        // 如果需要將排序後的List轉換回Map
        Map<String, TypeDto> sortedTypeMap = sortedTypeList.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // 打印排序後的Map
        sortedTypeMap.forEach((key, value) ->
                System.out.println("Key: " + key + ", Sort: " + value.getSort())
        );
    }

    private static List<Map.Entry<String, TypeDto>> getEntries() {
        Map<String, TypeDto> typeMap = new HashMap<>();

        // 添加一些示例數據
        TypeDto typeDto1 = new TypeDto();
        typeDto1.setType("type1");
        typeDto1.setTypeName("Type Name 1");
        typeDto1.setTypeSeq("001");
        typeDto1.setSort(2);
        typeMap.put(typeDto1.getType(), typeDto1);

        TypeDto typeDto2 = new TypeDto();
        typeDto2.setType("type2");
        typeDto2.setTypeName("Type Name 2");
        typeDto2.setTypeSeq("002");
        typeDto2.setSort(1);
        typeMap.put(typeDto2.getType(), typeDto2);

        // 將Map轉換為List
        // https://blog.csdn.net/zuzhiang/article/details/118444885
       // Map.entrySet（）方法主要用在对Map的键和值的遍历上，你可能会问了，既然已经有了更简便的方法去访问Map的键和值，
        // 为什么又要弄一个相对复杂的东西呢？答案就是速度更快，特别是在对大容量的Map进行键值对的遍历时
//        那为什么第一种常用的遍历方式会更慢呢？我的理解是：该种方式是先取出所有键的集合，然后拿着每个键再去Map中查找对应的值。
//        而第二种方法是直接遍历每个键值对，直接用getKey（）和getValue（）方法分别获取其键和值即可。
//        所以很明显第一种方式是多了一步的，也就更慢一些了。
        List<Map.Entry<String, TypeDto>> typeList = new ArrayList<>(typeMap.entrySet());
        return typeList;
    }
}


