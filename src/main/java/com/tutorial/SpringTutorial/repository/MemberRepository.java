package com.tutorial.SpringTutorial.repository;

import com.tutorial.SpringTutorial.entity.Member;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsrName(String usrName);
    boolean existsByUsrName(String usrName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM Member m WHERE m.id = :id")
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "1000")
    })
    Optional<Member> findByIdForUpdate(@Param("id") Long id);

    List<String> findAllEMail();
}
