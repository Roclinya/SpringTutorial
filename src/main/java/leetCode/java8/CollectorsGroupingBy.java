package leetCode.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsGroupingBy {

    public static void main(String[] args) {

//https://www.concretepage.com/java/jdk-8/java-8-collectors-groupingby-example
        List<StudentClass> classListA = Stream.of(new StudentClass("A","ClassA"), new StudentClass("B","ClassB"), new StudentClass("C","ClassC")).collect(Collectors.toList());
        List<StudentClass> classListB = Stream.of( new StudentClass("B","ClassB")).collect(Collectors.toList());
        List<StudentClass> classListC = Stream.of( new StudentClass("C","ClassC")).collect(Collectors.toList());
        Student s1 = new Student("A","Ram", "GroupA", classListA);
        Student s2 = new Student("B","Shyam", "GroupB", classListB);
        Student s3 = new Student("A","Mohan", "GroupA", classListA);
        Student s4 = new Student("C","Mahesh", "GroupC", classListC);
        Student s5 = new Student("B","Krishna",  "GroupB",classListB);
        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        //Group Student on the basis of id
        System.out.println("----Group Student on the basis of id----");
        Map<String, List<Student>> stdByClass = list.stream()
                .collect(Collectors.groupingBy(Student::getId));

        stdByClass.forEach((k,v)->System.out.println("Key:"+k+"  "+
                ((List<Student>)v).stream().map(m->m.getName()).collect(Collectors.joining(","))));

        //Group Student on the basis of Group
        System.out.println("----Group Student on the basis of Group----");
        Map<String, List<Student>> stdByAge = list.stream()
                .collect(Collectors.groupingBy(Student::getGroup));

        stdByAge.forEach((k,v)->System.out.println("Key:"+k+"  "+
                ((List<Student>)v).stream().map(m->m.getName()).collect(Collectors.joining(","))));

    }
}
