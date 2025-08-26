package leetCode.java8;

import java.util.List;

public class Student {

    private String id;
    private String name;
    private String group;

    private List<StudentClass> classList;


    public Student(String id, String name, String group,List classList) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.classList = classList;
    }

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<StudentClass> getClassList() {
        return classList;
    }

    public void setClassList(List<StudentClass> classList) {
        this.classList = classList;
    }
}
