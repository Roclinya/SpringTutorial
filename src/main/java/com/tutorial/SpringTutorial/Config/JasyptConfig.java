package com.tutorial.SpringTutorial.Config;

import com.tutorial.SpringTutorial.encryption.CustomEncryptablePropertyResolver;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import com.ulisesbocchio.jasyptspringboot.util.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Autowired
    private DefaultLazyEncryptor defaultStringEncryptor;

    //讀取 properties 檔，來判斷使用自訂解密演算法的時機，可以透過
    // JasyptEncryptorConfigurationProperties 類別來取得 jasypt-spring-boot 相關的設定值。
    // 關於 jasypt 的設定值 (from application-*.properties)
    @Autowired
    private Singleton<JasyptEncryptorConfigurationProperties> configPropsSingleton;

    @Bean(name="encryptablePropertyResolver")
    public EncryptablePropertyResolver encryptablePropertyResolver() {
        // 建構時，傳入預設的 StringEncryptor
        return new CustomEncryptablePropertyResolver(defaultStringEncryptor,configPropsSingleton);
    }
}
