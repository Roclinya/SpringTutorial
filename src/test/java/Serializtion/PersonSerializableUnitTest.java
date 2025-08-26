package Serializtion;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Java-Serialization
 * https://www.baeldung.com/java-serialization
 */
public class PersonSerializableUnitTest {

    @TempDir
    Path tempDir;
// Junit 4 only ,removed by Jnuit 5 to  @TempDir
//    @Rule
//    public TemporaryFolder tempFolder = new TemporaryFolder();
    private File outputFile;

    private File outputFile2;

    @BeforeEach
    public void setUp() throws Exception {
        outputFile = tempDir.resolve("yourfile.txt").toFile();
        outputFile2 = tempDir.resolve("yourfile2.txt").toFile();
    }

    @Test
    public void whenSerializingAndDeserializing_ThenObjectIsTheSame() throws IOException, ClassNotFoundException {
        PersonSerializable p = new PersonSerializable();
        p.setAge(20);
        p.setName("Joe");

        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(p);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(outputFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        PersonSerializable p2 = (PersonSerializable) objectInputStream.readObject();
        objectInputStream.close();

        assertEquals(p2.getAge(), p.getAge());
        assertEquals(p2.getName(), p.getName());
    }


}
