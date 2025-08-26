package leetCode.Map;

import leetCode.java8.Student;
import leetCode.java8.StudentClass;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsToMap01 {
//  參考：  https://stackabuse.com/guide-to-java-8-collectors-groupingby/
    public static void main(String[] args) {
//https://www.cnblogs.com/ylsx/p/16139709.html
        List<Student> list = new ArrayList<>();
        List<StudentClass> classList = new ArrayList<>();
        classList = Stream.of(new StudentClass("A","ClassA"), new StudentClass("B","ClassB"), new StudentClass("C","ClassC")).collect(Collectors.toList());;

        List<Student> duplicateList = new ArrayList<>();
        duplicateList.add(new Student("1","學生1","GroupA",classList));
        duplicateList.add(new Student("1","學生2","GroupB",classList));
        duplicateList.add(new Student("3","學生3","GroupC",classList));

        for (int i = 1; i < 4; i++) {
            list.add(new Student(i+"","學生"+i,"none",classList));
        }

/*
        keyMapper：Key 的映射函数
        valueMapper：Value 的映射函数
        mergeFunction：當 Key 衝突時，调用的合併方法
        mapSupplier：Map 构造器，在需要返回特定的 Map 时使用
        将list转成以id为key的map，value是id对应的Sudent对象：
*/
        Map<String, Student> map = list.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
        Map<String, Student> map2 = list.stream().collect(Collectors.toMap(Student::getId, t->t));
        System.out.println("map: "+map);
        System.out.println("map2: "+map2);


        // 使用 toMap 將 list 轉換成 Map<String, ReturnDTO>
        Map<String, ReturnDTO> map3 = list.stream()
                .collect(Collectors.toMap(
                        Student::getId, // 鍵：學生的 id
                        student -> {   // 值：ReturnDTO 對象
                            ReturnDTO dto = new ReturnDTO();
                            dto.setId(student.getId());
                            dto.setName(student.getName());
                            return dto;
                        },
                        (existing, replacement) -> existing // 如果有鍵重複，保留現有值
                ));

        // 打印結果
        map3.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
        System.out.println("map3: "+map3);
        //假如存在id重复，两个vaue可以这样映射到同一个id： 此處id相同時以逗號串接value方式顯示
        Map<String, String> duplicateMap = duplicateList.stream().collect(Collectors.toMap(Student::getId,Student::getName,(e1,e2)->e1+","+e2));
        System.out.println("duplicateMap: "+duplicateMap);

        List<Student> groupList = new ArrayList<>();
        groupList.add(new Student("1","學生1","GroupA",classList));
        groupList.add(new Student("2","學生2","GroupB",classList));
        groupList.add(new Student("3","學生3","GroupC",classList));
        //把Student集合按照group分组到map中
        Map<String, List<Student>> groupMap = groupList.stream().collect(Collectors.groupingBy(Student::getGroup));
        System.out.println("groupMap: "+groupMap);
//        Collectors.mapping
        Map<String, List<String>> mappingGroupMap = groupList.stream().collect(Collectors.groupingBy(Student::getId,Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println("mapping groupMap: "+mappingGroupMap);

        LinkedHashMap<String, LinkedHashMap<String, List<Student>>> linkedHashMapGroupMap = groupList.stream().collect(Collectors.groupingBy(
//                 第一層根據 groupID 做群組排序
                Student::getId, LinkedHashMap::new, Collectors.groupingBy(
//                         第二層根據 name 做群組排序
                        Student::getName, LinkedHashMap::new, Collectors.toList())));
        System.out.println("LinkedHashMap groupMap: "+linkedHashMapGroupMap);

        //Java中的LinkedHashMap : https://codegym.cc/tw/groups/posts/tw.997.javalinkedhashmap
    }

    private ReturnDTO convertToReturnDTO(Student student){
        ReturnDTO returnDto = new ReturnDTO();
        returnDto.setId(student.getId());
        returnDto.setName(student.getName()+" "+"RTN");
        return returnDto;
    }

//    這個問題是因為你嘗試在一個 靜態上下文（static context）中引用了一個 非靜態內部類（non-static inner class）。在 Java 中，非靜態內部類需要一個外部類的實例才能被引用，而靜態方法是無法直接訪問非靜態成員的。
//    問題出在你嘗試在 main 方法中（靜態上下文）使用 ReturnDTO 類，但 ReturnDTO 是非靜態內部類，必須透過外部類的實例來訪問。


    //因此,需要將 ReturnDTO 類聲明為靜態內部類
    static class ReturnDTO{
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
