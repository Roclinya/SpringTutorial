package com.tutorial.SpringTutorial.ntbInvoice.repo;

import com.tutorial.SpringTutorial.ntbInvoice.vo.ApinvHeader;
import com.tutorial.SpringTutorial.ntbInvoice.vo.HeaderContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApinvHeaderRepository extends JpaRepository<ApinvHeader, HeaderContentId> {
}