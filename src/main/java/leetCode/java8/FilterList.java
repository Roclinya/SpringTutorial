package leetCode.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterList {
    // 這段程式碼的目的是建立一組Student物件清單，每個Student物件包含一組StudentClass清單，並且根據特定條件過濾其中一個學生的班級清單
    public static void main(String[] args) {
        //建立 StudentClass 清單 (classList) 使用Stream.of建立三個StudentClass物件，並將它們加入到classList中,StudentClass包含了班級的ID和名稱。
        List<Student> list = new ArrayList<>();
        List<StudentClass> classList = new ArrayList<>();
        classList = Stream.of(new StudentClass("A","ClassA"), new StudentClass("B","ClassB"), new StudentClass("C","ClassC")).collect(Collectors.toList());
//        建立 Student 清單 (list)
        for (int i = 1; i < 4; i++) {
            list.add(new Student(i+"","學生"+i,"none",classList));
        }

        // 使用stream過濾出id為"3"的學生，對這個學生的班級清單進行進一步的過濾。過濾條件是排除掉班級名稱為"ClassA"的班級。最後將過濾後的班級清單設置回該學生。
        list.stream().filter(student->"3".equals(student.getId())).forEach(student -> {
            List<StudentClass> filteredClassList =
            student.getClassList().stream().filter(studentClass -> !"ClassA".equals(studentClass.getClassName())) .collect(Collectors.toList());
            student.setClassList(filteredClassList);
        });

        //使用forEach迴圈遍歷list，輸出每個Student物件的id、name，並列出其班級清單的每個StudentClass的id和className。
        list.forEach(e->{
            System.out.println("Id:"+e.getId()+" Name: "+e.getName());
            System.out.println("--List--");
            e.getClassList().forEach(k->{
                System.out.println("classId :"+k.getId()+"className : "+k.getClassName());
            });
            System.out.println("--------");
        });

    }
}
