package Serializtion;

import java.io.Serializable;

public class PersonSerializable implements Serializable {

    private String name;
    private int age;
    private Address country; // must be serializable too

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getCountry() {
        return country;
    }

    public void setCountry(Address country) {
        this.country = country;
    }
}
