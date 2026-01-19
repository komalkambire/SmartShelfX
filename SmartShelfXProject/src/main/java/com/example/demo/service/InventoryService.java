package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final ProductRepository productRepo;
    private final StockLevelRepository stockRepo;
    private final StockTransactionRepository txnRepo;

    public InventoryService(ProductRepository productRepo,
                            StockLevelRepository stockRepo,
                            StockTransactionRepository txnRepo) {
        this.productRepo = productRepo;
        this.stockRepo = stockRepo;
        this.txnRepo = txnRepo;
    }

    @Transactional
    public void stockIn(Long productId,
                        int qty,
                        String notes,
                        String warehouse,
                        Long userId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        StockLevel stock = stockRepo
                .findByProductAndWarehouse(product, warehouse)
                .orElseGet(() -> {
                    StockLevel s = new StockLevel();
                    s.setProduct(product);
                    s.setWarehouse(warehouse);
                    s.setQuantity(0);
                    s.setReorderLevel(10);
                    return s;
                });

        stock.setQuantity(stock.getQuantity() + qty);
        stockRepo.save(stock);

        saveTransaction(product,warehouse, qty,
                StockTransaction.TransactionType.STOCK_IN,
                userId, notes);
    }

    @Transactional
    public void stockOut(Long productId,
                         int qty,
                         String notes,
                         String warehouse,
                         Long userId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        StockLevel stock = stockRepo
                .findByProductAndWarehouse(product, warehouse)
                .orElseThrow(() -> new RuntimeException("No stock found"));

        if (stock.getQuantity() < qty)
            throw new RuntimeException("Insufficient stock");

        stock.setQuantity(stock.getQuantity() - qty);
        stockRepo.save(stock);

        saveTransaction(product, warehouse, qty,
                StockTransaction.TransactionType.STOCK_OUT,
                userId, notes);

        if (stock.getQuantity() < stock.getReorderLevel()) {
            System.out.println("⚠ REORDER ALERT for product: " + product.getName());
        }
    }

    private void saveTransaction(Product product,
                                 String warehouse,
                                 int qty,
                                 StockTransaction.TransactionType type,
                                 Long userId,
                                 String notes) {

        StockTransaction tx = new StockTransaction();
        tx.setProduct(product);
        tx.setWarehouse(warehouse);
        tx.setQuantity(qty);
        tx.setType(type);
        tx.setPerformedBy(userId);
        tx.setNotes(notes);

        txnRepo.save(tx);
    }
}
