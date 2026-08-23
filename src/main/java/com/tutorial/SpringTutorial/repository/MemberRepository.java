package com.tutorial.SpringTutorial.repository;

import com.tutorial.SpringTutorial.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsrName(String usrName);
    boolean existsByUsrName(String usrName);

    List<String> findAllEMail();
}
