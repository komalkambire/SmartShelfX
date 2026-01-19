package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockLevelRepository;
import com.example.demo.repository.StockTransactionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StockService {

    private final ProductRepository productRepository;
    private final StockLevelRepository stockLevelRepository;
    private final StockTransactionRepository transactionRepository;

    public StockService(ProductRepository productRepository,
                        StockLevelRepository stockLevelRepository,
                        StockTransactionRepository transactionRepository) {
        this.productRepository = productRepository;
        this.stockLevelRepository = stockLevelRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public StockLevel adjustStock(
            Long productId,
            String warehouse,
            Integer delta,
            StockTransaction.TransactionType type,
            Long userId,
            String notes) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        StockLevel stock = stockLevelRepository
                .findByProductAndWarehouse(product, warehouse)
                .orElseGet(() -> {
                    StockLevel s = new StockLevel();
                    s.setProduct(product);
                    s.setWarehouse(warehouse);
                    s.setQuantity(0);
                    return s;
                });

        if (type == StockTransaction.TransactionType.STOCK_OUT
                && stock.getQuantity() < delta) {
            throw new RuntimeException("Insufficient stock");
        }

        int newQty = (type == StockTransaction.TransactionType.STOCK_IN)
                ? stock.getQuantity() + delta
                : stock.getQuantity() - delta;

        stock.setQuantity(newQty);
        stockLevelRepository.save(stock);

        StockTransaction txn = new StockTransaction();
        txn.setProduct(product);
        txn.setWarehouse(warehouse);
        txn.setQuantity(delta);
        txn.setType(type);
        txn.setHandler("USER_" + userId);
        txn.setNotes(notes);

        transactionRepository.save(txn);

        return stock;
    }

    public List<String> importStockCsv(MultipartFile file, Long userId) {
        return List.of("CSV import not implemented yet");
    }
}
