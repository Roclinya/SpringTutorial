package enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum YorNBoolEnum {

    Y(Const.Y, true),
    N(Const.N, false);

    private String value;
    private boolean bool;

    public static Map<String, YorNBoolEnum> yorNBoolMap;

    static {
        yorNBoolMap =
                Stream.of(YorNBoolEnum.values())
                        .collect(Collectors.toMap(YorNBoolEnum::getValue, Function.identity()));
    }

    YorNBoolEnum(String value, boolean bool) {
        this.value = value;
        this.bool = bool;
    }

    public String getValue() {
        return this.value;
    }

    public boolean getBool() {
        return this.bool;
    }

    public static class Const {
        public static final String Y = "Y";
        public static final String N = "N";

        private Const() {}
    }

    public static YorNBoolEnum getByValue(String value) {
        return yorNBoolMap.get(value);
    }

    public static YorNBoolEnum getByBoolean(Boolean bool) {
        if (bool == null) return null;
        if (bool) return YorNBoolEnum.Y;
        else return YorNBoolEnum.N;
    }
}
