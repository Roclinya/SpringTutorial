package com.tutorial.SpringTutorial.repository;

import com.tutorial.SpringTutorial.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MemberRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    public List<String> findAllEMail(){

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT m.eMail ");
        sb.append(" FROM Member m ");
//        sb.append(" WHERE 1 = 1  ");
//        sb.append("   AND ( 1 = 2 ");
//        sb.append("   OR Exists (");
//        sb.append("       SELECT tsmpClient");
//        sb.append("       FROM TsmpClient tsmpClient");
//        sb.append("       WHERE A.clientId = tsmpClient.clientId");
//        sb.append("       AND ( tsmpClient.clientStatus = '2' OR tsmpClient.clientStatus = '3' )");
//        sb.append("  )");
//        sb.append(" )");

        TypedQuery<String> query = entityManager.createQuery(sb.toString(), String.class);
        return query.getResultList();
    }



}
