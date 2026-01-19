package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ReorderRequest;
import com.example.demo.entity.Warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReorderRequestRepository extends JpaRepository<ReorderRequest, Long> {

	

	boolean existsByProductAndWarehouse(Product product, Warehouse warehouse);
}
