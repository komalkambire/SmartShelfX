package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.StockLevel;
import com.example.demo.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockLevelRepository extends JpaRepository<StockLevel, Long> {

    Optional<StockLevel> findByProductAndWarehouse(Product product, String warehouse);
}
