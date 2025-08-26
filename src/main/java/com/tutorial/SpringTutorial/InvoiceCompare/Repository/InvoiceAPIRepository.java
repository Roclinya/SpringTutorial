package com.tutorial.SpringTutorial.InvoiceCompare.Repository;

import com.tutorial.SpringTutorial.InvoiceCompare.vo.InvoiceAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceAPIRepository extends JpaRepository<InvoiceAPI,Integer> {
}

