package com.tutorial.SpringTutorial.ntbInvoice.repo;

import com.tutorial.SpringTutorial.ntbInvoice.vo.ApinvLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApinvLineRepository extends JpaRepository<ApinvLine, Long> {
}