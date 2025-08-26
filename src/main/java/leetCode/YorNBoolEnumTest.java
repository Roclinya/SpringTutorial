package leetCode;

import enums.YorNBoolEnum;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class YorNBoolEnumTest {

    public static void main(String[] args) {

        String yorNBoolMap = null;
        Stream<YorNBoolEnum> values = Stream.of(YorNBoolEnum.values());
        values.forEach(e-> System.out.println(e));

        Map<String, YorNBoolEnum> map = Stream.of(YorNBoolEnum.values()).collect(Collectors.toMap(YorNBoolEnum::getValue, Function.identity()));
        System.out.println("Map "+map);

        System.out.println(YorNBoolEnum.getByValue("Y"));
    }
}
