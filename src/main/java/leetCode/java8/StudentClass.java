package leetCode.java8;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentClass {

    private String id;

    private String className;

    public StudentClass(String id, String className) {
        this.id = id;
        this.className = className;
    }
}
