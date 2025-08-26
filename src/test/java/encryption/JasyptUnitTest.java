package encryption;

import com.tutorial.SpringTutorial.Demo1Application;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Demo1Application.class)
public class JasyptUnitTest {

    //Spring Boot Password Encryption using Jasypt
    //https://medium.com/@javatechie/spring-boot-password-encryption-using-jasypt-e92eed7343ab
    //This code will override the default encryption configuration,ref to JasyptEncryptorConfig in this article
    //寫個小程式加密(方法二: 程式內呼叫套件)
    @Test
    public void testPasswordEncryption() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        // 從環境變數中讀取密鑰
        String encryptionKey = System.getenv("JASYPT_ENCRYPTION_KEY");
        if (encryptionKey == null || encryptionKey.isEmpty()) {
            throw new IllegalStateException("環境變數 JASYPT_ENCRYPTION_KEY 未設置！");
        }
        config.setPassword(encryptionKey); // encryptor's private key at environment parameter JASYPT_ENCRYPTION_KEY=MySalt
        config.setAlgorithm("PBEWithMD5AndDES");
//        config.setAlgorithm("PBEWithMD5AndTripleDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);


        String plainText = "27714944greenswift";
        String encryptedPassword = encryptor.encrypt(plainText);
        System.out.println("encryptedPassword : " + encryptedPassword);

        // 解密
        String decryptedText = encryptor.decrypt(encryptedPassword);
        System.out.println("decryptedPassword : " + decryptedText);
    }
}
