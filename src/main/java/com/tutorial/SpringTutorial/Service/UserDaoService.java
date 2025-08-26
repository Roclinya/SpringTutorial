package com.tutorial.SpringTutorial.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tutorial.SpringTutorial.entity.QUserEmployee;
import com.tutorial.SpringTutorial.entity.UserEmployee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    @Autowired
    private JPAQueryFactory queryFactory;

    private UserEmployee findBy(String name, String password) {
        return queryFactory
                .selectFrom(QUserEmployee.userEmployee)
                .where(
                        QUserEmployee.userEmployee.name.eq(name).and(
                                QUserEmployee.userEmployee.password.eq(password))
                ).fetchFirst();
    }
}
