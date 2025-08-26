package com.tutorial.SpringTutorial.Config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tutorial.SpringTutorial.Service.HelloMessageGenerator;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class Config {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    // @RequestScope 與以上寫法同樣效果(acts as a shortcut for the above definition)
    public HelloMessageGenerator requestScopedBean() {
        return new HelloMessageGenerator();
    }

    @Bean
    @Autowired
    public JPAQueryFactory jpaQuery(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }

}
