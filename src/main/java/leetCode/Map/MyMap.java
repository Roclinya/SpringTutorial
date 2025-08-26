package leetCode.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMap {

    public static void main(String args[]) {
        List<MyObject> myList = new ArrayList<>(
                List.of(
                        new MyObject("id1", 1, "desc1"),
                        new MyObject("id1", 1, "desc2"),
                        new MyObject("id1", 2, "desc3"),
                        new MyObject("id1", 2, "desc4"),
                        new MyObject("id2", 1, "desc5"),
                        new MyObject("id2", 1, "desc6"),
                        new MyObject("id2", 1, "desc7"),
                        new MyObject(null, null, "desc8")
                ));

        Map<String, Map<Integer, List<MyObject>>> byIdAndRow = myList // generating a map
                .stream()
                .collect(
                        HashMap::new,
                        (Map<String, Map<Integer, List<MyObject>>> mapMap, MyObject next) ->
                                mapMap.computeIfAbsent(next.getPersonalId(), k -> new HashMap<>())
                                        .computeIfAbsent(next.getRowNumber(), k -> new ArrayList<>())
                                        .add(next),
                        (left, right) -> right.forEach((k, v) -> left.merge(k, v,
                                (oldV, newV) -> {
                                    newV.forEach((k1, v1) -> oldV.merge(k1, v1,
                                            (listOld, listNew) -> {
                                                listOld.addAll(listNew);
                                                return listOld;
                                            }));
                                    return oldV;
                                }))
                );

        byIdAndRow.forEach((k, v) -> { // printing the map
            System.out.println(k);
            v.forEach((k1, v1) -> System.out.println(k1 + " -> " + v1));
        });

        Map<String, List<MyObject>> rows = new HashMap<>();
        byIdAndRow.forEach((k,v) -> rows.computeIfAbsent(k, s -> new ArrayList<>()).add(new MyObject("id0", 0, "desc0")));
        System.out.println("--------");
        System.out.println(rows);
    }

    public static class MyObject {
        private final String personalId;
        private final Integer rowNumber;
        private final String description;

        public MyObject(String personalId, Integer rowNumber, String description) {
            this.personalId = personalId;
            this.rowNumber = rowNumber;
            this.description = description;
        }

        public String getPersonalId() {
            return personalId;
        }

        public Integer getRowNumber() {
            return rowNumber;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "MyObject{" +
                    '\'' + personalId + '\'' +
                    ", " + rowNumber +
                    ", '" + description + '\'' +
                    '}';
        }
    }
}
