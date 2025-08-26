package com.tutorial.SpringTutorial.repository;

import com.tutorial.SpringTutorial.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query("SELECT s FROM Stock s WHERE s.productId = :productId")
    Optional<Stock> findByProductId(int productId);

}