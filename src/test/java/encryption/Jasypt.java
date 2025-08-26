package encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//【Java库】如何使用优秀的加密库Jasypt来保护你的敏感信息
//https://www.cnblogs.com/larrydpk/p/12026512.html

//寫個小程式加密(方法二: 程式內呼叫套件)
public class Jasypt {
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        // 從環境變數中讀取密鑰
        String encryptionKey = System.getenv("JASYPT_ENCRYPTION_KEY");
        if (encryptionKey == null || encryptionKey.isEmpty()) {
            throw new IllegalStateException("環境變數 JASYPT_ENCRYPTION_KEY 未設置！");
        }
        // 设置密钥
        encryptor.setPassword(encryptionKey);
        // 设置加密算法
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        // 加密信息
        String encryptedText = encryptor.encrypt("My secret message.");
        System.out.println("encryptedText:" + encryptedText);
         // 解密
        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("decryptedText:" + decryptedText);
    }
}
