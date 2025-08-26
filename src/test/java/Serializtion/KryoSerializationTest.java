package Serializtion;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.io.*;

import static org.junit.Assert.assertEquals;


public class KryoSerializationTest {

    @Test
    public void testKryoSerialization() {
        // 1. 建立 Kryo 物件
        Kryo kryo = new Kryo();
        kryo.register(Person.class); // 註冊需要序列化的類別

        // 2. 要測試的物件
        Person originalPerson = new Person("John Doe", 30);

        // 3. 序列化物件
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        kryo.writeObject(output, originalPerson);
        output.close();

        // 4. 反序列化物件
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Input input = new Input(byteArrayInputStream);
        Person deserializedPerson = kryo.readObject(input, Person.class);
        input.close();

        System.out.println("Name: " + deserializedPerson.getName());
        System.out.println("Age: " + deserializedPerson.getAge());

        // 5. 驗證
        assertEquals("序列化和反序列化的結果一致", originalPerson, deserializedPerson);
    }

}
