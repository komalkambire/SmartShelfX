package com.example.demo.repository;

import com.example.demo.entity.StockTransaction;
import com.example.demo.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

	Transaction save(Transaction txn);
}
