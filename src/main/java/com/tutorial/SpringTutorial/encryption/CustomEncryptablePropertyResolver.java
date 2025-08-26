package com.tutorial.SpringTutorial.encryption;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import com.ulisesbocchio.jasyptspringboot.util.Singleton;
import org.apache.commons.lang3.ObjectUtils;
import org.jasypt.encryption.StringEncryptor;

public class CustomEncryptablePropertyResolver implements EncryptablePropertyResolver {

    private final StringEncryptor stringEncryptor;

    public CustomEncryptablePropertyResolver(StringEncryptor defaultStringEncryptor, Singleton<JasyptEncryptorConfigurationProperties> configProps) {
        StringEncryptor encryptor = null;
        try {
            // 取得 properties 的設定值
            String password = configProps.get().getPassword();
            String privateKeyLocation = configProps.get().getPrivateKeyLocation();
            String privateKeyString = configProps.get().getPrivateKeyString();

            // 有設定 'jasypt.encryptor.password' 就走 PBE
            if (!ObjectUtils.isEmpty(password)) {
                System.out.println("Password-based Encryption Configuration detected!");
                encryptor = defaultStringEncryptor;

                // 有設定 "jasypt.encryptor.private-key-string" 或 "jasypt.encryptor.private-key-location" 就走非對稱式
            } else if (!ObjectUtils.isEmpty(privateKeyString) || !ObjectUtils.isEmpty(privateKeyLocation)) {
                System.out.println("Asymmetric Encryption Configuration detected!");
                encryptor = new StringEncryptor() {
                    @Override
                    public String encrypt(String message) {
                        // 主要目標是解密, 所以加密用預設的演算法即可
                        return defaultStringEncryptor.encrypt(message);
                    }

                    @Override
                    public String decrypt(String encryptedMessage) {
                        // 使用預設演算法解密後, 故意在字串後面加上 "_TPI_FOREVER" 字樣，以利測試
                        String decryptedMessage = defaultStringEncryptor.decrypt(encryptedMessage);
                        decryptedMessage += "_TPI_FOREVER";
                        return decryptedMessage;
                    }
                };
            } else {
                throw new Exception("Either 'jasypt.encryptor.password' or"
                        + " one of ['jasypt.encryptor.private-key-string', 'jasypt.encryptor.private-key-location']"
                        + " must be provided for Password-based or Asymmetric encryption");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.stringEncryptor = encryptor;
    }

    @Override
    public String resolvePropertyValue(String value) {
        if (value != null && value.startsWith("ENC(")) {
            // 先拆解出識別字中被加密的字段
            String encrypted = value.substring(value.indexOf("ENC(") + 4, value.lastIndexOf(")"));
            // 使用預設加密器進行解密
            String decrypted = this.stringEncryptor.decrypt(encrypted);
            return decrypted;
        }
        return value;
    }

}
