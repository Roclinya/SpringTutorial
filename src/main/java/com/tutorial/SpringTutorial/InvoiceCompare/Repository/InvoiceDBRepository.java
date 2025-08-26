package com.tutorial.SpringTutorial.InvoiceCompare.Repository;

import com.tutorial.SpringTutorial.InvoiceCompare.vo.InvoiceAPI;
import com.tutorial.SpringTutorial.InvoiceCompare.vo.InvoiceDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDBRepository extends JpaRepository<InvoiceDB,Integer> {
}

